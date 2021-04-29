long lastTime = 0;
int waitMillis = 1000;
String incomingString;

void setup() {
  Serial.begin(9600);
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
}

void loop() {
  if(millis() - lastTime >= waitMillis) {
    //Serial.print('1');
    lastTime = millis();
  }
  
  if (Serial.available() > 0) {
    // read the incoming byte:
    incomingString = Serial.readString();

    // say what you got:
    Serial.print("I received: ");
    Serial.println(incomingString);
    if(incomingString == "led aan") {
      digitalWrite(LED_BUILTIN, HIGH);
      delay(500);
      digitalWrite(LED_BUILTIN, LOW);
    }
  }
}
