EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title "Schematisch Ontwep"
Date "1-5-221"
Rev "1."
Comp "FakeATM"
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L pinautomaatElekSchema-v1-rescue:4x4_Keypad-4x4_keypad 96BB2-006-F?
U 1 1 60C20CDE
P 5450 1250
F 0 "96BB2-006-F?" H 6228 1436 60  0000 L CNN
F 1 "4x4_Keypad-4x4_keypad" H 6228 1330 60  0000 L CNN
F 2 "" H 5450 1250 60  0000 C CNN
F 3 "" H 5450 1250 60  0000 C CNN
	1    5450 1250
	-1   0    0    1   
$EndComp
$Comp
L pinautomaatElekSchema-v1-rescue:Arduino_Mega2560_Shield-arduino XA?
U 1 1 60C2E990
P 2950 4500
F 0 "XA?" V 2897 2173 60  0000 R CNN
F 1 "Arduino_Mega2560_Shield-arduino" V 3003 2173 60  0000 R CNN
F 2 "" H 3650 7250 60  0001 C CNN
F 3 "" H 3650 7250 60  0001 C CNN
	1    2950 4500
	0    1    -1   0   
$EndComp
$Comp
L Motor:Stepper_Motor_unipolar_5pin M?
U 1 1 60C345A5
P 8100 1300
F 0 "M?" H 8288 1424 50  0000 L CNN
F 1 "Stepper_Motor_unipolar_5pin" H 8288 1333 50  0000 L CNN
F 2 "" H 8110 1290 50  0001 C CNN
F 3 "http://www.infineon.com/dgdl/Application-Note-TLE8110EE_driving_UniPolarStepperMotor_V1.1.pdf?fileId=db3a30431be39b97011be5d0aa0a00b0" H 8110 1290 50  0001 C CNN
	1    8100 1300
	0    -1   -1   0   
$EndComp
$Comp
L Transistor_Array:ULN2003A U?
U 1 1 60C39FB1
P 7200 2450
F 0 "U?" H 7200 3117 50  0000 C CNN
F 1 "ULN2003A" H 7200 3026 50  0000 C CNN
F 2 "" H 7250 1900 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/uln2003a.pdf" H 7300 2250 50  0001 C CNN
	1    7200 2450
	1    0    0    -1  
$EndComp
$Comp
L Transistor_Array:ULN2003A U?
U 1 1 60C55E0A
P 8550 2450
F 0 "U?" H 8550 3117 50  0000 C CNN
F 1 "ULN2003A" H 8550 3026 50  0000 C CNN
F 2 "" H 8600 1900 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/uln2003a.pdf" H 8650 2250 50  0001 C CNN
	1    8550 2450
	1    0    0    -1  
$EndComp
$Comp
L pinautomaatElekSchema-v1-rescue:thermalPrinterBoard-board-2753-thermalPrinterGuts-v1 B2753?
U 1 1 60C59266
P 9950 5650
F 0 "B2753?" H 10378 5721 50  0000 L CNN
F 1 "thermalPrinterBoard-board-2753-thermalPrinterGuts-v1" H 10378 5630 50  0000 L CNN
F 2 "" H 9950 5750 50  0001 C CNN
F 3 "" H 9950 5750 50  0001 C CNN
	1    9950 5650
	1    0    0    -1  
$EndComp
$Comp
L pinautomaatElekSchema-v1-rescue:RFID-RC522-MODULE-rfid-rc522-module P?
U 1 1 60C5ABC1
P 6100 7000
F 0 "P?" H 5422 6814 60  0000 R CNN
F 1 "RFID-RC522-MODULE-rfid-rc522-module" H 5422 6920 60  0000 R CNN
F 2 "" H 6100 7000 60  0000 C CNN
F 3 "" H 6100 7000 60  0000 C CNN
	1    6100 7000
	-1   0    0    1   
$EndComp
Wire Wire Line
	5600 6450 5600 6200
Wire Wire Line
	5600 6200 1200 6200
Wire Wire Line
	1200 6200 1200 5800
Wire Wire Line
	5900 6450 5900 6100
Wire Wire Line
	5900 6100 1300 6100
Wire Wire Line
	1300 6100 1300 5800
Wire Wire Line
	1400 6050 1400 5800
Wire Wire Line
	1500 6000 1500 5800
Wire Wire Line
	1600 5950 1600 5800
Wire Wire Line
	7600 2450 7650 2450
Wire Wire Line
	7650 2450 7650 1500
Wire Wire Line
	7650 1500 7800 1500
Wire Wire Line
	7800 1400 7700 1400
Wire Wire Line
	7700 1400 7700 2550
Wire Wire Line
	7700 2550 7600 2550
Wire Wire Line
	7600 2650 7750 2650
Wire Wire Line
	7750 2650 7750 1200
Wire Wire Line
	7750 1200 7800 1200
Wire Wire Line
	8000 1600 7800 1600
Wire Wire Line
	7800 1600 7800 2750
Wire Wire Line
	7800 2750 7600 2750
Wire Wire Line
	7600 2850 7850 2850
Wire Wire Line
	7850 2850 7850 1650
Wire Wire Line
	7850 1650 8200 1650
Wire Wire Line
	8200 1650 8200 1600
Wire Wire Line
	8150 2250 7900 2250
Wire Wire Line
	8150 2350 7950 2350
Wire Wire Line
	8150 2450 8000 2450
Wire Wire Line
	8150 2550 8050 2550
Wire Wire Line
	8150 2650 8100 2650
$Comp
L Motor:Stepper_Motor_unipolar_5pin M?
U 1 1 60C8E0C5
P 9450 1300
F 0 "M?" H 9638 1424 50  0000 L CNN
F 1 "Stepper_Motor_unipolar_5pin" H 9638 1333 50  0000 L CNN
F 2 "" H 9460 1290 50  0001 C CNN
F 3 "http://www.infineon.com/dgdl/Application-Note-TLE8110EE_driving_UniPolarStepperMotor_V1.1.pdf?fileId=db3a30431be39b97011be5d0aa0a00b0" H 9460 1290 50  0001 C CNN
	1    9450 1300
	0    -1   -1   0   
$EndComp
$Comp
L Transistor_Array:ULN2003A U?
U 1 1 60C8E0CB
P 9900 2450
F 0 "U?" H 9900 3117 50  0000 C CNN
F 1 "ULN2003A" H 9900 3026 50  0000 C CNN
F 2 "" H 9950 1900 50  0001 L CNN
F 3 "http://www.ti.com/lit/ds/symlink/uln2003a.pdf" H 10000 2250 50  0001 C CNN
	1    9900 2450
	1    0    0    -1  
$EndComp
Wire Wire Line
	8950 2450 9000 2450
Wire Wire Line
	9000 2450 9000 1500
Wire Wire Line
	9000 1500 9150 1500
Wire Wire Line
	9150 1400 9050 1400
Wire Wire Line
	9050 1400 9050 2550
Wire Wire Line
	9050 2550 8950 2550
Wire Wire Line
	8950 2650 9100 2650
Wire Wire Line
	9100 2650 9100 1200
Wire Wire Line
	9100 1200 9150 1200
Wire Wire Line
	9350 1600 9150 1600
Wire Wire Line
	9150 1600 9150 2750
Wire Wire Line
	9150 2750 8950 2750
Wire Wire Line
	8950 2850 9200 2850
Wire Wire Line
	9200 2850 9200 1650
Wire Wire Line
	9200 1650 9550 1650
Wire Wire Line
	9550 1650 9550 1600
Wire Wire Line
	9500 2250 9250 2250
Wire Wire Line
	9500 2350 9300 2350
Wire Wire Line
	9500 2450 9350 2450
Wire Wire Line
	9500 2550 9400 2550
Wire Wire Line
	9500 2650 9450 2650
$Comp
L Motor:Stepper_Motor_unipolar_5pin M?
U 1 1 60C9A6C4
P 10800 1300
F 0 "M?" H 10988 1424 50  0000 L CNN
F 1 "Stepper_Motor_unipolar_5pin" H 10988 1333 50  0000 L CNN
F 2 "" H 10810 1290 50  0001 C CNN
F 3 "http://www.infineon.com/dgdl/Application-Note-TLE8110EE_driving_UniPolarStepperMotor_V1.1.pdf?fileId=db3a30431be39b97011be5d0aa0a00b0" H 10810 1290 50  0001 C CNN
	1    10800 1300
	0    -1   -1   0   
$EndComp
Wire Wire Line
	10300 2450 10350 2450
Wire Wire Line
	10350 2450 10350 1500
Wire Wire Line
	10350 1500 10500 1500
Wire Wire Line
	10500 1400 10400 1400
Wire Wire Line
	10400 1400 10400 2550
Wire Wire Line
	10400 2550 10300 2550
Wire Wire Line
	10300 2650 10450 2650
Wire Wire Line
	10450 2650 10450 1200
Wire Wire Line
	10450 1200 10500 1200
Wire Wire Line
	10700 1600 10500 1600
Wire Wire Line
	10500 1600 10500 2750
Wire Wire Line
	10500 2750 10300 2750
Wire Wire Line
	10300 2850 10550 2850
Wire Wire Line
	10550 2850 10550 1650
Wire Wire Line
	10550 1650 10900 1650
Wire Wire Line
	6800 2250 6550 2250
Wire Wire Line
	6800 2350 6600 2350
Wire Wire Line
	6800 2450 6650 2450
Wire Wire Line
	6800 2550 6700 2550
Wire Wire Line
	6800 2650 6750 2650
Wire Wire Line
	7200 3050 7200 6050
Wire Wire Line
	7200 6050 1400 6050
Wire Wire Line
	8550 3050 8550 6000
Wire Wire Line
	8550 6000 1500 6000
Wire Wire Line
	9900 3050 9900 3850
Wire Wire Line
	9900 3850 8600 3850
Wire Wire Line
	8600 3850 8600 5950
Wire Wire Line
	8600 5950 1600 5950
Wire Wire Line
	9350 5650 9350 5900
Wire Wire Line
	9350 5900 1700 5900
Wire Wire Line
	1700 5900 1700 5800
Wire Wire Line
	5200 2100 5200 2150
Wire Wire Line
	5200 2150 1000 2150
Wire Wire Line
	5300 2100 5300 2200
Wire Wire Line
	5300 2200 1100 2200
Wire Wire Line
	5400 2100 5400 2250
Wire Wire Line
	5400 2250 1200 2250
Wire Wire Line
	5500 2100 5500 2300
Wire Wire Line
	5500 2300 1300 2300
Wire Wire Line
	5600 2100 5600 2350
Wire Wire Line
	5600 2350 1400 2350
Wire Wire Line
	5700 2100 5700 2400
Wire Wire Line
	5700 2400 1500 2400
Wire Wire Line
	5800 2100 5800 2450
Wire Wire Line
	5800 2450 1600 2450
Wire Wire Line
	1600 2450 1600 3200
Wire Wire Line
	6550 2250 6550 2500
Wire Wire Line
	6550 2500 1700 2500
Wire Wire Line
	1700 2500 1700 3200
Wire Wire Line
	6600 2350 6600 2550
Wire Wire Line
	6600 2550 1800 2550
Wire Wire Line
	1800 2550 1800 3200
Wire Wire Line
	6650 2450 6650 2600
Wire Wire Line
	6650 2600 1900 2600
Wire Wire Line
	1900 2600 1900 3200
Wire Wire Line
	6700 2550 6700 2650
Wire Wire Line
	6700 2650 2000 2650
Wire Wire Line
	2000 2650 2000 3200
Wire Wire Line
	6750 2650 6750 2700
Wire Wire Line
	6750 2700 2100 2700
Wire Wire Line
	2100 2700 2100 3200
Wire Wire Line
	7900 2250 7900 3050
Wire Wire Line
	7900 3050 7200 3050
Connection ~ 7200 3050
Wire Wire Line
	7200 3050 6650 3050
Wire Wire Line
	7950 2350 7950 3100
Wire Wire Line
	8000 2450 8000 3150
Wire Wire Line
	8050 2550 8050 3200
Wire Wire Line
	8100 2650 8100 3250
Wire Wire Line
	6650 3050 6650 2750
Wire Wire Line
	6650 2750 2200 2750
Wire Wire Line
	2200 2750 2200 3200
Wire Wire Line
	6600 3100 6600 2800
Wire Wire Line
	6600 2800 2300 2800
Wire Wire Line
	2300 2800 2300 3200
Wire Wire Line
	6600 3100 7950 3100
Wire Wire Line
	6550 3150 6550 2850
Wire Wire Line
	6550 2850 2400 2850
Wire Wire Line
	2400 2850 2400 3200
Wire Wire Line
	6550 3150 8000 3150
Wire Wire Line
	6500 3200 6500 2900
Wire Wire Line
	6500 2900 2500 2900
Wire Wire Line
	2500 2900 2500 3200
Wire Wire Line
	6500 3200 8050 3200
Wire Wire Line
	6450 3250 6450 2950
Wire Wire Line
	6450 2950 2600 2950
Wire Wire Line
	2600 2950 2600 3200
Wire Wire Line
	6450 3250 8100 3250
Wire Wire Line
	2700 3200 2700 3000
Wire Wire Line
	2800 3200 2800 3050
Wire Wire Line
	2900 3200 2900 3100
Wire Wire Line
	3000 3200 3000 3150
Wire Wire Line
	9250 2250 9250 3300
Wire Wire Line
	9250 3300 6400 3300
Wire Wire Line
	6400 3300 6400 3000
Wire Wire Line
	9300 2350 9300 3400
Wire Wire Line
	9300 3400 6350 3400
Wire Wire Line
	6350 3400 6350 3050
Wire Wire Line
	2800 3050 6350 3050
Wire Wire Line
	9350 2450 9350 3450
Wire Wire Line
	9350 3450 6300 3450
Wire Wire Line
	6300 3450 6300 3100
Wire Wire Line
	2900 3100 6300 3100
Wire Wire Line
	9400 2550 9400 3500
Wire Wire Line
	9400 3500 6250 3500
Wire Wire Line
	3000 3150 6250 3150
Wire Wire Line
	6250 3150 6250 3500
Wire Wire Line
	2700 3000 6400 3000
Wire Wire Line
	5100 2100 900  2100
Wire Wire Line
	8150 2850 8150 3600
Wire Wire Line
	8150 3600 6200 3600
Wire Wire Line
	6200 3600 6200 2200
Wire Wire Line
	6200 2200 6750 2200
Wire Wire Line
	6750 2200 6750 500 
Wire Wire Line
	6750 500  3100 500 
Wire Wire Line
	3100 500  3100 3200
Wire Wire Line
	9950 5200 9950 3650
Wire Wire Line
	9950 3650 6150 3650
Wire Wire Line
	6150 3650 6150 2150
Wire Wire Line
	6150 2150 6700 2150
Wire Wire Line
	6700 2150 6700 550 
Wire Wire Line
	6700 550  3200 550 
Wire Wire Line
	3200 550  3200 3200
Wire Wire Line
	9800 5200 9800 3700
Wire Wire Line
	9800 3700 6100 3700
Wire Wire Line
	6100 3700 6100 2100
Wire Wire Line
	6100 2100 6650 2100
Wire Wire Line
	6650 2100 6650 600 
Wire Wire Line
	6650 600  3300 600 
Wire Wire Line
	3300 600  3300 3200
Wire Wire Line
	1500 2400 1500 3200
Wire Wire Line
	1400 2350 1400 3200
Wire Wire Line
	1300 2300 1300 3200
Wire Wire Line
	1200 2250 1200 3200
Wire Wire Line
	1100 2200 1100 3200
Wire Wire Line
	1000 2150 1000 3200
Wire Wire Line
	900  2100 900  3200
Wire Wire Line
	6650 6450 6650 3750
Wire Wire Line
	6650 3750 6050 3750
Wire Wire Line
	6050 3750 6050 700 
Wire Wire Line
	6050 700  3400 700 
Wire Wire Line
	3400 700  3400 3200
Wire Wire Line
	6500 6450 6500 3800
Wire Wire Line
	6500 3800 6000 3800
Wire Wire Line
	6000 3800 6000 750 
Wire Wire Line
	6000 750  3500 750 
Wire Wire Line
	3500 750  3500 3200
Wire Wire Line
	6350 6450 6350 3850
Wire Wire Line
	6350 3850 5950 3850
Wire Wire Line
	5950 3850 5950 800 
Wire Wire Line
	5950 800  3600 800 
Wire Wire Line
	3600 800  3600 3200
Wire Wire Line
	6200 6450 6200 3900
Wire Wire Line
	6200 3900 5900 3900
Wire Wire Line
	5900 3900 5900 850 
Wire Wire Line
	5900 850  3700 850 
Wire Wire Line
	3700 850  3700 3200
$EndSCHEMATC
