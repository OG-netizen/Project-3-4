#include <Stepper.h>
const int stepsPerRevolution = 2048;
Stepper myStepper = Stepper(stepsPerRevolution, 26, 30, 28, 32); // Maak stepper opject aan | Pin 26 = IN1 | Pin 30 = IN3 | Pin 28 = IN2 | Pin 32 = IN4 |

void setup() {
  myStepper.setSpeed(15);     // Zet snelheid in RPM
  myStepper.step(stepsPerRevolution);
}
void loop() {
}