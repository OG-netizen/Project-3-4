#include "Adafruit_Thermal.h"
#include "SoftwareSerial.h"
#define TX_PIN 53 // BLAUWE WIRE   RX op printer
#define RX_PIN 51 // GROENE WIRE   TX op printer

SoftwareSerial mySerial(RX_PIN, TX_PIN); // Declare SoftwareSerial obj first
Adafruit_Thermal printer(&mySerial);     // Pass addr to printer constructor
void setup(){
  bonprint(20, 10);
}



void loop() {
}

void bonprint(int pinaantal, int donatieaantal) {
  int totaalpin = pinaantal+donatieaantal;


  pinMode(7, OUTPUT);
  digitalWrite(7, LOW);

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
}
