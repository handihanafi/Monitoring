#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>

#define FIREBASE_HOST "https://coba-3beac-default-rtdb.firebaseio.com/"
#define FIREBASE_AUTH "QAhZjSAZ6vQsz9z0xBfUGIuddnKT3vq0EkVGiU1o"
#define WIFI_SSID "WAP IoT"
#define WIFI_PASSWORD "Wahana106"

int led = D0;
int gasA0 = A0;

// Your threshold value. You might need to change it.
int sensorThres = 400;

FirebaseData firebaseData;

void setup() {
 pinMode(led, OUTPUT);
 pinMode(gasA0, INPUT);
 Serial.begin(115200);

 WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
}

void sensorUpdate(){

  int analogSensor = analogRead(gasA0);
  boolean led = false;

 Serial.print("sensor Value: ");
 Serial.println(analogSensor);
 // Checks if it has reached the threshold value
 if (analogSensor > sensorThres)
 {
   digitalWrite(led, HIGH);
   Serial.println("LED ON");
   led = true;
 }
 else
 {
   digitalWrite(led, LOW);
   Serial.println("LED OFF");
   led = false;
 }

  if (Firebase.setInt(firebaseData, "/Gas Sensor/sensorValue", analogSensor))
  {
    Serial.println("PASSED");
    Serial.println("PATH: " + firebaseData.dataPath());
    Serial.println("TYPE: " + firebaseData.dataType());
    Serial.println("ETag: " + firebaseData.ETag());
    Serial.println("------------------------------------");
    Serial.println();
  }
  else
  {
    Serial.println("FAILED");
    Serial.println("REASON: " + firebaseData.errorReason());
    Serial.println("------------------------------------");
    Serial.println();
  }

  if (Firebase.setBool(firebaseData, "/Gas Sensor/ledStatus", led)) {
      Serial.println("PASSED");
      Serial.println("PATH: " + firebaseData.dataPath());
      Serial.println("TYPE: " + firebaseData.dataType());
      Serial.println("ETag: " + firebaseData.ETag());
      Serial.println("------------------------------------");
      Serial.println();
    } else {
      Serial.println("FAILED");
      Serial.println("REASON: " + firebaseData.errorReason());
      Serial.println("------------------------------------");
      Serial.println();
    }
  
}

void loop() {
  
 int analogSensor = analogRead(gasA0);
 if (analogSensor > sensorThres)
 {
   digitalWrite(led, HIGH);
   Serial.println("LED ON");
 }
 else
 {
   digitalWrite(led, LOW);
   Serial.println("LED OFF");
 }
  
 sensorUpdate();
 
 delay(100);
}
