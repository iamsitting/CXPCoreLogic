#import bluetooth
import serial
from threading import Thread
from time import sleep
import numpy as np
import random
import sys

btserial = serial.Serial("/dev/rfcomm0", baudrate=9600, timeout=1)
stream = False
RTS = False
sThread = True
main = True

def safePrint(s):
	sys.stdout.write(s + '\n')

def genData():
	step = 0.1
	x = np.arange(-1,1,step)
	y = 2*np.sin(2*np.pi*x)+2
	ylist = y.tolist()
	return ylist

def serialRead(ser):
	global stream #generate data when true
	global RTS #only send when true, ignore when false
	while sThread:
		try:
			safePrint('Listening...')
			msg = ser.readline()
			if len(msg):
				if 'E' in msg:
					stream = True
					RTS= True
				elif 'Q' in msg:
					stream = False
					RTS = False
				elif 'K' in msg:
					RTS = True
				else:
					continue
				safePrint(msg)
			else:
				continue

		except Exception as e:
			print e
			continue		
	
def formatData(data):
	packets = []
	for el in data:
		p = 's'+'%.1f' %el
		packets.append(p)
	return packets

def makePackets(dat0):
	packets = []
	dat1 = dat0; random.shuffle(dat1)
	dat2 = dat0; random.shuffle(dat2)
	dat3 = dat0; random.shuffle(dat3)
	
	for i in range(len(dat0)-1):
		pack = dat0[i] + ',' + \
			dat1[i] + ',' + \
			dat2[i] + ',' + \
			dat3[i] + '\n'
		packets.append(pack)
	return packets

if __name__ == "__main__":
	data = genData()
	fdata = formatData(data)
	print fdata

	try:
		myThread = Thread(target=serialRead, args=(btserial,))
		myThread.start()
		while main:
			while stream:
				safePrint('streaming...')
				packets = makePackets(fdata)
				for packet in packets:
					if len(packet):
						if RTS:
							btserial.write(packet)
							RTS = False
						sleep(1)
	except KeyboardInterrupt:
		sThread = False
		myThread.join(3)
		print "Quit"
	
	except serial.SerialException as e:
		sThread = False
		myThread.join(3)
		print e
