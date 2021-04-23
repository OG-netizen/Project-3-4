#include "Adafruit_Thermal.h"
#include "SoftwareSerial.h"
#include <Stepper.h>
#define TX_PIN 53 // BLAUWE WIRE   RX op printer
#define RX_PIN 51 // GROENE WIRE   TX op printer

// Motoren
const int stepsPerRevolution = 2048;
Stepper myStepper1 = Stepper(stepsPerRevolution, 26, 30, 28, 32); // Maak stepper opject aan | Pin 26 = IN1 | Pin 30 = IN3 | Pin 28 = IN2 | Pin 32 = IN4 |
Stepper myStepper2 = Stepper(stepsPerRevolution, 34, 38, 36, 40); // Maak stepper opject aan | Pin 26 = IN1 | Pin 30 = IN3 | Pin 28 = IN2 | Pin 32 = IN4 |
//Stepper myStepper3 = Stepper(stepsPerRevolution, ?, ?, ?, ?); // Maak stepper opject aan | Pin 26 = IN1 | Pin 30 = IN3 | Pin 28 = IN2 | Pin 32 = IN4 |

//Themal Pinter
SoftwareSerial mySerial(RX_PIN, TX_PIN); // Declare SoftwareSerial obj first
Adafruit_Thermal printer(&mySerial);     // Pass addr to printer constructor


void setup(){
  Serial.begin(9600);
  myStepper1.setSpeed(15);     // Zet snelheid in RPM
  myStepper2.setSpeed(15);
  //myStepper3.setSpeed(15);
  //myStepper.step(stepsPerRevolution);
  //bonprint(20, 10);
  // motordraai(2);
}



void loop() {


  int i = Serial.parseInt();
  motordraai(i);
  Serial.println(i);
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
void motordraai(int motornr){
  if (motornr == 1) {
    myStepper1.step(stepsPerRevolution);
  }
  else if (motornr == 2) {
    myStepper2.step(stepsPerRevolution);
  }
  else {
    //myStepper3.step(stepsPerRevolution);
  }
}