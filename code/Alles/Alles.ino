#include <SPI.h>
#include <MFRC522.h>
#include <Keypad.h>
#include "Adafruit_Thermal.h"
#include "SoftwareSerial.h"
#include <Stepper.h>
#define TX_PIN 37 // BLAUWE WIRE   RX op printer
#define RX_PIN 39 // GROENE WIRE   TX op printer
#define SS_PIN 53 // RFID
#define RST_PIN 5 // RFID
#define ROW_NUM 4
#define COLUMN_NUM 4

//Keypad
char keys[ROW_NUM][COLUMN_NUM] = {
  {'1','2','3', 'A'},
  {'4','5','6', 'B'},
  {'7','8','9', 'C'},
  {'*','0','#', 'D'}
};
byte pin_rows[ROW_NUM] = {42, 44, 46 , 48};
byte pin_column[COLUMN_NUM] = {43, 45, 47, 49};

// RFID
byte nuidPICC[4];
int count = 3;
bool newCard = true;

MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

Keypad keypad = Keypad(makeKeymap(keys), pin_rows, pin_column, ROW_NUM, COLUMN_NUM);

// Motoren
const int stepsPerRevolution = 2048;
Stepper myStepper1 = Stepper(stepsPerRevolution, 26, 30, 28, 32); // Maak stepper opject aan | Pin 26 = IN1 | Pin 30 = IN3 | Pin 28 = IN2 | Pin 32 = IN4 |  -------50 eu
Stepper myStepper2 = Stepper(stepsPerRevolution, 34, 38, 36, 40); // Maak stepper opject aan | Pin 34 = IN1 | Pin 38 = IN3 | Pin 36 = IN2 | Pin 40 = IN4 |  -------20 eu
Stepper myStepper3 = Stepper(stepsPerRevolution, 23, 27, 25, 29); // Maak stepper opject aan | Pin 42 = IN1 | Pin 46 = IN3 | Pin 44 = IN2 | Pin 48 = IN4 |  -------10 eu

//Themal Pinter
SoftwareSerial mySerial(RX_PIN, TX_PIN); // Declare SoftwareSerial obj first
Adafruit_Thermal printer(&mySerial);     // Pass addr to printer constructor


void setup(){
  Serial.begin(9600);
  // Zet de snelheden voor de motoren in rpm
  myStepper1.setSpeed(15);
  myStepper2.setSpeed(15);
  myStepper3.setSpeed(15);
  SPI.begin();
  rfid.PCD_Init();
  Serial.println("done with boot");
}



void loop() {
  handleSerial();
  handleCard();
  handleKey();
}

void bonprint(int pinaantal, int donatieaantal) {
  int totaalpin = pinaantal+donatieaantal;

  mySerial.begin(9600);  // Begin resial voor printer
  printer.begin();        // Begin printer


  // Print logo bovenaan
  printer.justify('C');
  printer.boldOn();
  printer.setSize('L');
  printer.print(F("Fake"));
  printer.boldOff();

  printer.boldOn();
  printer.inverseOn();
  printer.setSize('L');
  printer.println(F("ATM"));
  printer.boldOff();
  printer.inverseOff();
  // ----------------------------------

  // Print pin amount
  printer.setLineHeight(64);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Aantal gepint               "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(pinaantal);
  printer.boldOff();
  // ----------------------------------

  // Print donatie aantal
  printer.setLineHeight(32);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Donatie                     "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(donatieaantal);
  printer.boldOff();
  // ----------------------------------

  // Print totaal aantal
  printer.setLineHeight(64);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Totaal                      "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(totaalpin);
  printer.boldOff();
  // ----------------------------------

  /* 
  Tijd printen
  printer.setLineHeight(256);
  printer.justify('C');
  printer.setSize('S');
  printer.print(day());
  printer.print("|");
  printer.print(month());
  printer.print("|");
  printer.println(year());
  */
  // ----------------------------------

  // Locatie printen
  printer.setLineHeight(48);
  printer.justify('C');
  printer.setSize('S');
  printer.println("fakeATM");
  // ----------------------------------

  // laat wat plek onderaan zodat de bon makkelijk uitneembaar is zonder dat er worden vergaan
   printer.setLineHeight(256);
   printer.setLineHeight(50);
   printer.println(F(""));
   printer.println(F(""));
   printer.println(F(""));
  // ----------------------------------

  // Reset de printer
  printer.sleep();      
  //  delay(5);         
  printer.wake();       
  printer.setDefault(); 
  // ----------------------------------

  Serial.print("bonGeprint: ");
  Serial.print(pinaantal);
  Serial.print(", ");
  Serial.print(donatieaantal);
  Serial.println(" ");
}
void motordraai(int motornr){
  if (motornr == 1) {
    myStepper1.step(stepsPerRevolution);
    digitalWrite(26, LOW);
    digitalWrite(28, LOW);
    digitalWrite(30, LOW);
    digitalWrite(32, LOW);
  }
  else if (motornr == 2) {
    myStepper2.step(stepsPerRevolution);
    digitalWrite(34, LOW);
    digitalWrite(36, LOW);
    digitalWrite(38, LOW);
    digitalWrite(40, LOW);
  }
  else {
    myStepper3.step(stepsPerRevolution);
    digitalWrite(23, LOW);
    digitalWrite(25, LOW);
    digitalWrite(27, LOW);
    digitalWrite(29, LOW);
  }
}
void handleSerial() {
  while(Serial.available() > 0) {
    String recieved = Serial.readStringUntil(':');
    if(recieved == "dispense") {
      int aantal50 = Serial.readStringUntil(',').toInt();
      int aantal20 = Serial.readStringUntil(',').toInt();
      int aantal10 = Serial.readStringUntil(',').toInt();
      werpGeldUit(aantal50, aantal20, aantal10);
    } else if(recieved = "printBon") {
      int geldAantal = Serial.readStringUntil(',').toInt();
      int donatieAantal = Serial.readStringUntil(',').toInt();
      Serial.print("aantal gepind: ");
      Serial.println(geldAantal);
      Serial.print("aantal gedoneerd: ");
      Serial.println(donatieAantal);
      bonprint(geldAantal, donatieAantal);
    }
  }
}

void handleCard() {
  bool readCard = rfid.PICC_ReadCardSerial();
  bool trash = rfid.PICC_IsNewCardPresent();
  
  if(readCard) {
    if(newCard) {
      printUid();
      newCard = false;
      delay(500);
    }
    count = 0;
  } else {
    count++;
    if(count == 2) {
      newCard = true;
      Serial.println("removed_card ");
    } else if(count > 2) {
      count = 3;
    }
  }
}

void handleKey() {
  char key = keypad.getKey();
  if(key) {
    Serial.print("key: ");
    Serial.print(key);
    Serial.println(" ");
  }
}

void werpGeldUit(int aantal50, int aantal20, int aantal10) {
  for(int i = 0; i < aantal50; i++) {
    Serial.println("briefje van 50 aan het printen.....");
    motordraai(1);
  }
  delay(500);
  for(int i = 0; i < aantal20; i++) {
    Serial.println("briefje van 20 aan het printen.....");
    motordraai(2);
  }
  delay(500);
  for(int i = 0; i < aantal10; i++) {
    Serial.println("briefje van 10 aan het printen.....");
    motordraai(3);
  }

  Serial.print("geldUitgeworpen: 50,");
  Serial.print(aantal50);
  Serial.print("20,");
  Serial.print(aantal20);
  Serial.print("10,");
  Serial.print(aantal10);
  Serial.println(" ");
}

void printUid() {
  //  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
  //  if (piccType != MFRC522::PICC_TYPE_MIFARE_MINI &&  
  //    piccType != MFRC522::PICC_TYPE_MIFARE_1K &&
  //    piccType != MFRC522::PICC_TYPE_MIFARE_4K) {
  //    //Serial.println(F("Your tag is not of type MIFARE Classic."));
  //    return;
  //  }

  // Store NUID into nuidPICC array
  for (byte i = 0; i < 4; i++) {
    nuidPICC[i] = rfid.uid.uidByte[i];
  }

  Serial.print("uid:");
  for (byte i = 0; i < rfid.uid.size; i++) {
    Serial.print(rfid.uid.uidByte[i] < 0x10 ? " 0" : " ");
    Serial.print(rfid.uid.uidByte[i], HEX);
  }
  Serial.println(" ");
  
  // Halt PICC
  rfid.PICC_HaltA();

  // Stop encryption on PCD
  rfid.PCD_StopCrypto1();
}
