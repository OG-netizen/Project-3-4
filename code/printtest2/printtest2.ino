/*------------------------------------------------------------------------
  Example sketch for Adafruit Thermal Printer library for Arduino.
  Demonstrates a few text styles & layouts, bitmap printing, etc.

  IMPORTANT: DECLARATIONS DIFFER FROM PRIOR VERSIONS OF THIS LIBRARY.
  This is to support newer & more board types, especially ones that don't
  support SoftwareSerial (e.g. Arduino Due).  You can pass any Stream
  (e.g. Serial1) to the printer constructor.  See notes below.

  You may need to edit the PRINTER_FIRMWARE value in Adafruit_Thermal.h
  to match your printer (hold feed button on powerup for test page).
  ------------------------------------------------------------------------*/

#include "Adafruit_Thermal.h"
#include "ding.h"
#include "adaqrcode.h"
#include <TimeLib.h>

// Here's the new syntax when using SoftwareSerial (e.g. Arduino Uno) ----
// If using hardware serial instead, comment out or remove these lines:

#include "SoftwareSerial.h"
#define TX_PIN 6 // Arduino transmit  BLAUW WIRE  labeled RX on printer
#define RX_PIN 5 // Arduino receive   GROEN WIRE   labeled TX on printer

SoftwareSerial mySerial(RX_PIN, TX_PIN); // Declare SoftwareSerial obj first
Adafruit_Thermal printer(&mySerial);     // Pass addr to printer constructor
// Then see setup() function regarding serial & printer begin() calls.

// Here's the syntax for hardware serial (e.g. Arduino Due) --------------
// Un-comment the following line if using hardware serial:

//Adafruit_Thermal printer(&Serial1);      // Or Serial2, Serial3, etc.

// ----------------------------------------------------------------------- 

void setup() {
  int pinaantal = 20;
  int donatieaantal = 10;
  int totaalpin = pinaantal+donatieaantal;


  // setTime(hr,min,sec,day,mnth,yr);
  setTime(15,44,0,8,4,2021);
  // This line is for compatibility with the Adafruit IotP project pack,
  // which uses pin 7 as a spare grounding point.  You only need this if
  // wired up the same way (w/3-pin header into pins 5/6/7):
  pinMode(7, OUTPUT); digitalWrite(7, LOW);

  // NOTE: SOME PRINTERS NEED 9600 BAUD instead of 19200, check test page.
  mySerial.begin(9600);  // Initialize SoftwareSerial
  //Serial1.begin(19200); // Use this instead if using hardware serial
  printer.begin();        // Init printer (same regardless of serial type)

  // The following calls are in setup(), but don't *need* to be.  Use them
  // anywhere!  They're just here so they run one time and are not printed
  // over and over (which would happen if they were in loop() instead).
  // Some functions will feed a line when called, this is normal.
//  printer.justify('C');
//for(int i = 0; i < 3; i++) {
  printer.justify('C');
  printer.boldOn();
  printer.setSize('L');
  printer.print(F("Fake"));
  printer.boldOff();
//
//////   Test inverse on & off
  printer.boldOn();
  printer.inverseOn();
  printer.setSize('L');
  printer.println(F("ATM"));
  printer.boldOff();
  printer.inverseOff();

  printer.setLineHeight(64);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Aantal gepint               "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(pinaantal);
  printer.boldOff();


  printer.setLineHeight(32);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Donatie                     "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(donatieaantal);
  printer.boldOff();

  printer.setLineHeight(64);
  printer.justify('L');
  printer.setSize('S');
  printer.print(F("Totaal                      "));
  printer.boldOn();
  printer.setSize('S');
  printer.println(totaalpin);
  printer.boldOff();


  printer.setLineHeight(256);
  printer.justify('C');
  printer.setSize('S');
  printer.print(day());
  printer.print("|");
  printer.print(month());
  printer.print("|");
  printer.println(year());

 printer.setLineHeight(48);
  printer.justify('C');
  printer.setSize('S');
  printer.println("fakeATM");

  printer.setLineHeight(256);
    printer.setLineHeight(50);
    printer.println(F(""));
    printer.println(F(""));
    printer.println(F(""));
    

////  // Test character double-height on & off
//  printer.doubleHeightOn();
//  printer.println(F("AYY LMAO"));
//  printer.doubleHeightOff();
//
//  // Set text justification (right, center, left) -- accepts 'L', 'C', 'R'
//  printer.justify('R');
//  printer.println(F("Right justified"));
//  printer.justify('C');
//  printer.println(F("Center justified"));
//  printer.justify('L');
//  printer.println(F("Left justified"));
//
//  // Test more styles
//  printer.boldOn();
//  printer.println(F("Bold text"));
//  printer.boldOff();
//
//  printer.underlineOn();
//  printer.println(F("Underlined text"));
//  printer.underlineOff();
//
//  printer.setSize('L');        // Set type size, accepts 'S', 'M', 'L'
//  printer.println(F("Large"));
//  printer.setSize('M');
//  printer.println(F("Medium"));
//  printer.setSize('S');
//  printer.println(F("Small"));
//
//  printer.justify('C');
//  printer.println(F("normal\nline\nspacing"));
//  printer.setLineHeight(50);
//  printer.println(F("Taller\nline\nspacing"));
//  printer.setLineHeight(); // Reset to default
//  printer.justify('L');
//
//  // Barcode examples:
  // CODE39 is the most common alphanumeric barcode:
//  printer.printBarcode("https://www.google.com", CODE39);
//  printer.setBarcodeHeight(100);
//   Print UPC line on product barcodes:
//  printer.printBarcode("https://www.google.com", UPC_A);

//  // Print the 75x75 pixel logo in adalogo.h:
//  printer.printBitmap(ding_width, ding_height, ding_data);

//  // Print the 135x135 pixel QR code in adaqrcode.h:
//  printer.printBitmap(adaqrcode_width, adaqrcode_height, adaqrcode_data);
//  printer.println(F("Adafruit!"));
//  printer.feed(2);

  printer.sleep();      // Tell printer to sleep
  delay(3000L);         // Sleep for 3 seconds
  printer.wake();       // MUST wake() before printing again, even if reset
  printer.setDefault(); // Restore printer to defaults
}

void loop() {
}
