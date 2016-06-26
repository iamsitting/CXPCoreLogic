#INTRO

These files are to test the Android app's Bluetooth usage. These testers are Python programs run on a RaspberryPi 2 with a USB Bluetooth Module.
---

##gpyo.py

This file is not used for actual testing. This is incase we need to use the GPIO pins to simulate data.
---

##pot.py

This file is not used for actual testing. This is file utilizes the an ADC chip to read analog data from a potentiometer.
---

##bloo.py

This a bluetooth client to test basic bluetooth communication to a bluetooth socket. Be aware that the devices must be paired and connected before this script is executed.
---

##bloopot.py

This is a combination of bloo.py and pot.py. This is a script that takes in analog data from potentiometer, passes it to an ADC chip, and reads it from the GPIO pins. It also has a bluetooth client to send data to the android app.
---

##blooread.py
This file waits tests being able to receive commands over bluetooth.
---

##bloo2.py

This is a bluetooth client that generates data and sends it to a bluetooth server.
---

##bloo3.py
This is the same as bloo2.py, but generates multiple sets of data and sends it over bluetooh in CSV format.
