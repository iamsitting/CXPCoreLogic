#import bluetooth
import serial
btserial = serial.Serial("/dev/rfcomm0", baudrate=9600)

#bd_addr = "BC:F5:AC:50:AF:4E"

#port = 1

#sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
#sock.connect((bd_addr, port))

#sock.send("s.20")
#sock.close

count = None
while count == None:
	try:
		data = raw_input("Enter data"+'/n')
		btserial.write(data)
	except KeyboardInterrupt:
		print "Quit"
		break

#btserial.write(data)

