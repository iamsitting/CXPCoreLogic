#import bluetooth
import serial
btserial = serial.Serial("/dev/rfcomm0", baudrate=9600, timeout=1)

count = None
while count == None:
	try:
		print 'reading...'
		line = btserial.readline()
		if len(line):
			print line
	except KeyboardInterrupt:
		print "Quit"
		break

#btserial.write(data)

