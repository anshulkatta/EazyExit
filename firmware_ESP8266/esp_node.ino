

#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include "credentials.h"

#define RELAY D0
#define SERIAL_DEBUG 1 //Enable this for optional serial debugging



WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  
  #if SERIAL_DEBUG
  Serial.begin(115200);
  #endif
  
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
  
}

void setup_wifi() {

  delay(10);
  pinMode(RELAY,OUTPUT); //Configure Pin as Output

  #if SERIAL_DEBUG
  Serial.println("Connecting to ");
  Serial.println(ssid);
  #endif

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }

  #if SERIAL_DEBUG
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  #endif
}



void callback(char* topic, byte* payload, unsigned int length) {
  
  char p[length + 1];
  memcpy(p, payload, length);
  p[length] = NULL;
  String message(p);

  #if SERIAL_DEBUG
  Serial.println("Message arrived");
  for(int i=0; i<=length+1; i++)
  Serial.println(message[i]); 
  #endif 

  if(message == "off")
  digitalWrite(RELAY,HIGH);
  

  if(message == "onn")
  digitalWrite(RELAY,LOW);
  }


void reconnect() {
  
  // Loop until we're reconnected
  while (!client.connected()) {

    #if SERIAL_DEBUG
    Serial.print("Attempting MQTT connection...");
    #endif
    
    // Attempt to connect
    if (client.connect("ESP8266Client")) {
      #if SERIAL_DEBUG
      Serial.println("connected");
      #endif
      client.subscribe(topic);

    }
    
    else {
      #if SERIAL_DEBUG
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      #endif
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}


void loop() {

  if (!client.connected()) {
    reconnect();
  }
  client.loop();
}
