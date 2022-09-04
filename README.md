# ParkingLot
The parking lot should allow the user to park the car. This is implemented using the park command. After the user has entered this command, the registration number and the color of the car should be specified. For example, park KA-01-HH-1234 Blue. The registration number should not contain spaces. The color can be written in either uppercase and lowercase letters.

To pick up the car, the user should print the command leave and then the number of the parking spot, for example, leave 1. If there is no car in the given spot, the program should print an error: There is no car in spot 1. Otherwise, it should notify the user that the spot is now available: Spot 1 is free.

create command that allows the user to specify the number of spots. For example, the command create 3 makes a parking lot with three spots. The number of spots should be positive. The program output should be the following: Created a parking lot with 3 spots.

Other commands like park or leave should return an error Sorry, a parking lot has not been created. until the user enters the create command. If the user calls create again, the previous parking state should be reset.

status command that prints all occupied spots in ascending order. For each spot, it should print the spot number, the car’s plate registration number, and the color of the car, all separated by spaces like the example below. If there are no occupied spots, the program should print: Parking lot is empty.

The command reg_by_color prints all registration numbers of cars of a particular color, taking color as a parameter. The color may be written in uppercase or lowercase letters. For example, reg_by_color BLACK. The answer should contain registration numbers separated by commas. The order should be the same as in the status command. If there are no cars of this color, the output should be like this: No cars with color BLACK were found..

The command spot_by_color is similar to the previous one, but it prints the parking space numbers of all the cars of a particular color.

The command spot_by_reg returns you the number of the spot where a car is located based on its registration number, for example, spot_by_reg KA-01. This command will also return an error message if your car was not found: No cars with registration number KA-01 were found.
