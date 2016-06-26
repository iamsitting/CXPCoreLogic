#import bluetooth
import serial
from time import sleep
import numpy as np
btserial = serial.Serial("/dev/rfcomm0", baudrate=9600, timeout=2)

#bd_addr = "BC:F5:AC:50:AF:4E"

#port = 1

#sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
#sock.connect((bd_addr, port))

#sock.send("s.20")
#sock.close

step = 0.1
x = np.arange(-1,1,step)
y = 2*np.sin(2*np.pi*x)+2
ylist = y.tolist()
print ylist
stream = True
main = True
def serialRead(serial):
	line = serial.readline()
	if len(line) > 0:
		return ''
	else:
		return line
def makePacket(data):
	packets = []
	for el in data:
		p = 's'+'%.5f' %el
		packets.append(p)
	return packets
try:
	while main:
		print 'Listening .. '
		sleep(2)
		line = serialRead(btserial)
		print line
		if line == "E":
			stream = True
		while stream:
			packets = makePacket(ylist)
			for packet in packets:
				btserial.write(packet)
			line = serialRead(btserial)
			print line
			if line == "Q":
				stream = False

except KeyboardInterrupt:
	print "Quit"

#btserial.write(data)

