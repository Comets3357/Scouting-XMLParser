# Scouting-XMLParser
Repo for the scouting app xml parser
This is a guide to the XML parser program and an explanation as to how everything works.
The "XML parser" isn't actually parsing XML, but the name caught on pretty quickly.
The objective of the XML parser is to generate game-specific information for the stands scouting app in
the form of two XML files.
The way that this XML parser works is that it takes data line by line from the "Read" file and converts the given inputs into XML code.
The parser works in three steps, Dual(covers both tele and auto), Tele, and Auto. Tele represents datapoints that will only be
recorded during the teleoperated portion of the match, and auto for autonomous.
You must give your input in this order: Name of reference in code, the type of variable, what will show up in the app
(ex. ver, INTEGER, version. create an integer named ver, but show up as version in the app)
each sort of line must have only two commas and must be used to separate from the name in reference, data type, and
what will show up in the app.

The dual mode will give two outputs. It will take the both the name for the reference in code and what will show
up in the app and add "tele" to the front for one output and put auto for the other output
ex: "UpperHub" will convert to "teleUpperHub" and "autoUpperHub"

Both the tele and auto stage are like the dual mode, but they are separate. These stages should be used when you want to make
a data point for either teleop or auton portions of the match for the app.
Note: Final order is Dual, Tele, Auto, and Scouter Names

To change stages, add a line that simply says "stop". This will tell the program that you do not want it
read in the mode you are currently in and cause a change in stage so if you are in dual, inputing "stop" will put
the program in the tele stage.

Once you tell the program to stop while it is in auto, it will start reading for names. You can input names in two formats:
line by line (each name is it's own line) OR as a list separated by commas.

Please note that the datapoints below are automatically added:
-MatchNumber
-ScouterName
-TeamNumber
-Climb
-Comments
-Fouls
-TechFouls
-YellowCards
-redCards
-teleDefense
-lostComms

Example Input:
```
topRocketCargo,INT,topRocketCargo
middleRocketCargo,INT,middleRocketCargo
bottomRocketCargo,INT,bottomRocketCargo
topRocketPanel,INT,topRocketPanel
middleRocketPanel,INT,middleRocketPanel
bottomRocketPanel,INT,bottomRocketPanel
frontShipCargo,INT,frontShipCargo
sideShipCargo,INT,sideShipCargo
frontShipPanel,INT,frontShipPanel
sideShipPanel,INT,sideShipPanel
stop
droppedOffLevelTwo,INT,droppedOffLevelTwo
leftStart,INT,leftStart
stop
stop
Jacob Beels
Zach Moore
Andrew Swanstorm
```
Example Output:




Made by the great Zach Moore and Andrew Swanstrom
