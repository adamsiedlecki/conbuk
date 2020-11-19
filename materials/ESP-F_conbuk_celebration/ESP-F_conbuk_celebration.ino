#include <ESP8266WiFi.h>

WiFiServer server(80);
char* wifi_name = "Click_Here_For_Viruses";
char* wifi_pass = "wifi-password"; /// !!! change to your own name and pass

IPAddress ip(10, 0, 0, 90);
IPAddress gateway(10, 0, 0, 1);
IPAddress subnet(255, 255, 255, 0);

int ledPin = 16; // D0

void setup() {
  ESP.eraseConfig();
  Serial.begin(9600);
  Serial.println("SETUP STARTED");

  // prepare led pin
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);

  WiFi.disconnect();
  delay(100); // time to shut down old networks

  WiFi.setSleepMode(WIFI_NONE_SLEEP);
  WiFi.mode(WIFI_STA);
  WiFi.begin(wifi_name, wifi_pass);
  WiFi.config(ip, gateway, subnet);
  server.begin();

}

void loop() {
    // Check if a client has connected
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // Wait until the client sends some data
  Serial.println("new client");
  int timewate = 0;
  while (!client.available()) {
    delay(1);
    timewate = timewate + 1;
    if (timewate > 1800)
    {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }

  // response
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); //  do not forget this one
  client.println("");
  client.println("");
  client.println("Yeah, celebration!");

  // Read the first line of the request
  String request = client.readStringUntil('\r');
  Serial.println(request);
  if(request.indexOf("/celebration")!=-1){
    digitalWrite(ledPin, HIGH);
    delay(1000);
    digitalWrite(ledPin, LOW);
    
  }
  client.flush();


}
