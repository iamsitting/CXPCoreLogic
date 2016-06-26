import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
test_out = 9
GPIO.setup(test_out, GPIO.OUT)

def Blink(numTimes, speed):
    for i in range(0, numTimes):
        print 'Iteration '+ str(i+1)
        GPIO.output(test_out, True)
        time.sleep(speed)
        GPIO.output(test_out, False)
        time.sleep(speed)
    print 'Done'
    GPIO.cleanup()

iterations = raw_input('Enter number of times to blink.')
speed = raw_input('Enter length of blink.')

Blink(int(iterations), float(speed))
