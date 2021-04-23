#include <SPI.h>
#include <MFRC522.h>
#include <Keypad.h>

#define SS_PIN 53
#define RST_PIN 5

MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

// Init array that will store new NUID 
byte nuidPICC[4];
int count = 3;
bool newCard = true;

#define ROW_NUM 4
#define COLUMN_NUM 4

char keys[ROW_NUM][COLUMN_NUM] = {
  {'1','2','3', 'A'},
  {'4','5','6', 'B'},
  {'7','8','9', 'C'},
  {'*','0','#', 'D'}
};

byte pin_rows[ROW_NUM] = {42, 44, 46 , 48};
byte pin_column[COLUMN_NUM] = {43, 45, 47, 49};

Keypad keypad = Keypad(makeKeymap(keys), pin_rows, pin_column, ROW_NUM, COLUMN_NUM);

void setup() {
  Serial.begin(9600);
  SPI.begin();
  rfid.PCD_Init();
  pinMode(2, OUTPUT);
  digitalWrite(2, LOW);
  //werpGeldUit(2,3,4);
}

void loop() {
  handleSerial();
  handleCard();
  handleKey();
}

void handleSerial() {
  while(Serial.available() > 0) {
    digitalWrite(2, HIGH);
    delay(1000);
    digitalWrite(2, LOW);
    String recieved = Serial.readStringUntil(':');
    if(recieved == "dispense") {
      int aantal50 = Serial.readStringUntil(',').toInt();
      int aantal20 = Serial.readStringUntil(',').toInt();
      int aantal10 = Serial.readStringUntil(',').toInt();
      werpGeldUit(aantal50, aantal20, aantal10);
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
    digitalWrite(2, HIGH);
    delay(200);
    digitalWrite(2, LOW);
    delay(200);

    //TO-DO

  }
  delay(500);
  for(int i = 0; i < aantal20; i++) {
    Serial.println("briefje van 20 aan het printen.....");
    digitalWrite(2, HIGH);
    delay(200);
    digitalWrite(2, LOW);
    delay(200);

    //TO-DO

  }
  delay(500);
  for(int i = 0; i < aantal10; i++) {
    Serial.println("briefje van 10 aan het printen.....");
    digitalWrite(2, HIGH);
    delay(200);
    digitalWrite(2, LOW);
    delay(200);

    //TO-DO

  }

  Serial.println("klaar met het printen van geld:");
  Serial.print("50: ");
  Serial.println(aantal50);
  Serial.print("20: ");
  Serial.println(aantal20);
  Serial.print("10: ");
  Serial.println(aantal10);
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
