# Scouting-XMLParser
This is a guide to the XML parser program and an explanation as to how everything works.
The "XML parser" isn't actually parsing XML, but the name caught on pretty quickly.
The objective of the XML parser is to generate game-specific information for the stands scouting app in
the form of two XML files.
## Using the Read file
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

Please note that the datapoints below are automatically added:<br>
-MatchNumber<br>
-ScouterName<br>
-TeamNumber<br>
-Climb<br>
-Comments<br>
-Fouls<br>
-TechFouls<br>
-YellowCards<br>
-redCards<br>
-teleDefense<br>
-lostComms<br>

After making all your changes to the `Read` file, run `scouting-xmlParser-master.jar` to generate `strings.xml` and `SQLiteInfo.xml`

**Example Input:**
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
**Example Output:**

`strings.xml`:
```
<resources>
    <string name="app_name">ScoutingApp</string>
    <string name="textbox_default">0</string>
    <!--These first two strings are universal for all apps -->
    <string name="matchNumber">matchNumber</string>
    <string name="teamNumber">teamNumber</string>
    <string name="scouterName">scouterName</string>
    <string name="climb">climb</string>
    <string name="fouls">fouls</string>
    <string name="techFouls">techFouls</string>
    <string name="yellowCards">yellowCards</string>
    <string name="redCards">redCards</string>
    <string name="teleDefense">teleDefense</string>
    <string name="lostComms">lostComms</string>
    <string name="teletopRocketCargo">topRocketCargo</string>
    <string name="autotopRocketCargo">topRocketCargo</string>
    <string name="telemiddleRocketCargo">middleRocketCargo</string>
    <string name="automiddleRocketCargo">middleRocketCargo</string>
    <string name="telebottomRocketCargo">bottomRocketCargo</string>
    <string name="autobottomRocketCargo">bottomRocketCargo</string>
    <string name="teletopRocketPanel">topRocketPanel</string>
    <string name="autotopRocketPanel">topRocketPanel</string>
    <string name="telemiddleRocketPanel">middleRocketPanel</string>
    <string name="automiddleRocketPanel">middleRocketPanel</string>
    <string name="telebottomRocketPanel">bottomRocketPanel</string>
    <string name="autobottomRocketPanel">bottomRocketPanel</string>
    <string name="telefrontShipCargo">frontShipCargo</string>
    <string name="autofrontShipCargo">frontShipCargo</string>
    <string name="telesideShipCargo">sideShipCargo</string>
    <string name="autosideShipCargo">sideShipCargo</string>
    <string name="telefrontShipPanel">frontShipPanel</string>
    <string name="autofrontShipPanel">frontShipPanel</string>
    <string name="telesideShipPanel">sideShipPanel</string>
    <string name="autosideShipPanel">sideShipPanel</string>
    <string name="teledroppedOffLevelTwo">droppedOffLevelTwo</string>
    <string name="teleleftStart">leftStart</string>


    <!--
    <string name="RadioGroup1">value6</string>
    <string name="Radio1">level1</string>
    <string name="Radio2">level2</string>
    <string name="Radio3">level3</string>-->
    <!--Value1 must be match number, Value 2 must be Scouter name, name of teamNumbers cannot be altered-->
    <!--Radios need 1 item in the array below for the radio group, and it needs 3 values above for each radio button-->
    <!--Radios are special, each radio group needs 1 entry, and the item in the array below must equal the id of the radio group-->
    <string-array name="datapoints">
        <item>@string/matchNumber</item>
        <item>@string/teamNumber</item>
        <item>@string/scouterName</item>
        <item>@string/fouls</item>
        <item>@string/techFouls</item>
        <item>@string/yellowCards</item>
        <item>@string/redCards</item>
        <item>@string/teleDefense</item>
        <item>@string/lostComms</item>
        <item>@string/climb</item>
        <item>@string/topRocketCargo</item>
        <item>@string/middleRocketCargo</item>
        <item>@string/bottomRocketCargo</item>
        <item>@string/topRocketPanel</item>
        <item>@string/middleRocketPanel</item>
        <item>@string/bottomRocketPanel</item>
        <item>@string/frontShipCargo</item>
        <item>@string/sideShipCargo</item>
        <item>@string/frontShipPanel</item>
        <item>@string/sideShipPanel</item>
        <item>@string/droppedOffLevelTwo</item>
        <item>@string/leftStart</item>

        <!--<item>@string/RadioGroup1</item>-->
    </string-array>
<!-- Data required to be input before pressing the submit button in the app -->
    <string-array name="reqData">
        <item>@string/matchNumber</item>
        <item>@string/teamNumber</item>
        <item>@string/climb</item>
        <item>@string/scouterName</item>
   </string-array>
   <string-array name="teamNames"> 
        <item>Jacob Beels</item>
        <item>Zach Moore</item>
        <item>Andrew Swanstorm</item>
   </string-array> 
</resources>
```
`SQLiteInfo.xml`:
```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!-- format: <item>SQL Stuff<item/>
    COPY AND PASTE FROM STRINGS!!!
        Use normal SQL pieces to create columns -->

    <!-- ScoutingData Database -->
    <string-array name="scoutingDataKeys">
        <item>matchNumber INTEGER</item>
        <item>teamNumber INTEGER</item>
        <item>scouterName TEXT</item>
        <item>fouls INTEGER</item>
        <item>techFouls INTEGER</item>
        <item>yellowCards INTEGER</item>
        <item>redCards INTEGER</item>
        <item>teleDefense TEXT</item>
        <item>lostComms INTEGER</item>
        <item>climb TEXT</item>
        <item>teletopRocketCargo INT</item>
        <item>autotopRocketCargo INT</item>
        <item>telemiddleRocketCargo INT</item>
        <item>automiddleRocketCargo INT</item>
        <item>telebottomRocketCargo INT</item>
        <item>autobottomRocketCargo INT</item>
        <item>teletopRocketPanel INT</item>
        <item>autotopRocketPanel INT</item>
        <item>telemiddleRocketPanel INT</item>
        <item>automiddleRocketPanel INT</item>
        <item>telebottomRocketPanel INT</item>
        <item>autobottomRocketPanel INT</item>
        <item>telefrontShipCargo INT</item>
        <item>autofrontShipCargo INT</item>
        <item>telesideShipCargo INT</item>
        <item>autosideShipCargo INT</item>
        <item>telefrontShipPanel INT</item>
        <item>autofrontShipPanel INT</item>
        <item>telesideShipPanel INT</item>
        <item>autosideShipPanel INT</item>
        <item>teledroppedOffLevelTwo INT</item>
        <item>teleleftStart INT</item>
        <item>comments TEXT</item>
    </string-array>
</resources>
```

# How to build a new jar file
This part is actually super easy, assuming you are using intellij (idk how other IDEs handle this).
Every time you build the project, a file called `scouting-xmlParser-master.jar` will be written with the path `/out/artifacts/scouting-xmlParser-master.jar`.
Another way to build this artifact is to select `Build -> Build Artifacts... -> scouting-xmlParser-master:jar -> Build`.


Made by the great Zach Moore and Andrew Swanstrom
