# EazyExit is an open-source home automation and security solution which is focused to provide you with seamless connected home experience, while ensuring security in your absence.

## Changelog:

17/03/2017:
- Added WiFiManager(https://github.com/tzapu/WiFiManager.git) support to dynamically manage WiFi connections.

13/03/2017:
- Added PlatformIO(http://platformio.org) development and Build support

## Setting up MQTT Server/Broker (Raspberry Pi/Debian Equivalent)

	- ``` sudo apt-get update ```
	- ``` sudo apt-get install mosquito mosquito-clients ```

  - Mosquito broker will automatically start running on <your_pi_ip_address> port 1883

## Setting up Development and Build system

- Hardware Supported (ESP8266 Nodemcu modules).
- Platform independent, only dependency is Python 2.7, pip installed.
- Make sure SiLabs CP21xx USB to Serial drivers are installed.

- Install and setup PlatformIO environment and command line tools
	- ``` sudo pip install platformio ```

- Clone the repo into your local machine
	- ``` git clone https://github.com/Team-SDIoT/EazyExit.git ```
	- ``` cd EazyExit ```

- Fetch latest versions of submodules/dependent libraries using:
	- ``` git submodule init ```
	- ``` git submodule update ```

- In case above step doesn’t work clone libraries manually
	- ``` cd lib ```
	- ``` git clone https://github.com/knolleary/pubsubclient.git PubSubClient ```
	- ``` git clone https://github.com/ekstrand/ESP8266wifi ```
	- ``` git clone https://github.com/tzapu/WiFiManager.git ```

- Edit "/lib/credentials/credentials.h" and add your WiFi SSID, WiFi password and IP address of server(Raspberry Pi) running MQTT broker

- Edit GPIO pin number /src/ESP8266.cpp if required default is pin D0 on which on-board LED is connected

- Build project using following command:
	- ``` pio run ``` // Note: This will build and generate binaries for all supportive hardware
	- ``` pio run -r nodemcu ``` // Note: This will build and generate binary only for nodemcu hardware

- Build and flash directly to the board using following command:
	- ``` pio run -e nodemcu -t upload ``` // Note: This will flash binary to board connected via USB, Serial port will be auto detected

- To manually flash the binary you will need to install esptool:
	- ``` sudo pip install esptool ```

- Identify COMM port of board attached to your machine using Device Manager on windows and following command on Linux or MAC OSX
	- ```ls /dev/tty*, on mac and linux it is /dev/tty.SLAB_USBtoUART```

- cd to directory holding binary:
	- ``` EazyExit/.pioenvs/<Targer_Board> binary —> firmware.bin```

- Flash using esp_tool:
	- ``` esptool.py -p <COMM_PORT> —baud 460800 write_flash —flash_size detect 0 firmware.bin ```
## TEST

- ESP node listens to MQTT messages<onn/off> on topic myHome
- On raspberry pi run ``` mosquitto_pub -t myHome -m off ``` LED should be off and ``` mosquitto_pub -t myHome -m one ``` LED should turn on.

## Development Environment setup:

- Download PlatformIO official IDE "http://platformio.org/platformio-ide" or PlatformIO Plugin for ATOM IDE (http://www.atom.io)

## TODO

- Setup continuous integration with TRAVIS CI and continuous deployment using ESP8266 OTA firmware updates.
