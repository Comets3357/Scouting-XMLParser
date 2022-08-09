package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /* This file reads from the file named "Read", where the input text goes.
        It will then put that file, line by line, into the format of "strings.xml" and "SQLiteInfo.xml"
        so those files aren't needed anymore, and we can just input things automatically.

        If there is a line that the program doesn't like, it will ask you to reenter it, and then move on
        to the next line afterwards. */

        /*runs line by line and when it hits stop, a mode will cause a shift and start shift into a different mode
        it should run in mode that spits out both auton and tele, then just tele, then just auton
         */

        //initializing the strings
        int mode = 0;
        String format1 = "";
        String format2 = "";
        String format3 = "";
        String input = "";
        boolean breakPls = false;
        boolean autonMode = true;
        boolean teleMode = true;


        //reading the file
        URL url = Path.of("Read").toUri().toURL(); //file path
        String str = read_files(url); //doing the actual reading
        //variables for getting the read file in the correct line-by-line format
        int enterLoc = 0;
        int previousEnterLoc = -1;
        boolean firstLoop = true;

        //loop to get all the inputs
        while (teleMode || autonMode) {
            //getting input
            if (!firstLoop) {
                previousEnterLoc = enterLoc;
            }
            //grabs the length of a line of input
            enterLoc = str.indexOf("\n", enterLoc + 1);
            if (enterLoc == -1) {
                break;
            }
            //takes a line of input
            input = str.substring(previousEnterLoc + 1, enterLoc);
            input = input.trim();
            String moddedInput = input;
            if (moddedInput.equalsIgnoreCase("STOP")) {
                if (mode == 0) {
                    autonMode = false;
                    mode++;
                } else if (mode == 1) {
                    autonMode = true;
                    teleMode = false;
                    mode++;
                } else if (mode == 2) {
                    break;
                }

            }

            int numOfCommas = 0;
            int commaLoc = 0;
            while (moddedInput.contains(",")){
                commaLoc = moddedInput.indexOf(",");
                moddedInput = moddedInput.substring(0, commaLoc) + moddedInput.substring(commaLoc + 1);
                numOfCommas++;
            }

            //in case the program doesn't like a certain line, it will ask you to reenter it here
            while (numOfCommas != 2) {
                if (input.equalsIgnoreCase("stop"))
                    break;
                System.out.println(input);
                System.out.println("Invalid data. Input stop to end data input. Input the data to continue.");
                input = keyboard.nextLine();
                input = input.trim();
                moddedInput = input;
                if (moddedInput.equalsIgnoreCase("STOP")) {
                    breakPls = true;
                    break;
                }
                numOfCommas = 0;
                commaLoc = 0;
                while (moddedInput.contains(",")) {
                    commaLoc = moddedInput.indexOf(",");
                    moddedInput = moddedInput.substring(0, commaLoc) + moddedInput.substring(commaLoc + 1);
                    numOfCommas++;
                }
            }
            if (breakPls){
                break;
            }

            //deleting whitespace (except for last space if not camelCase)
            while (input.contains(", ")) {
                int spaceLoc = input.indexOf(" ");
                if (input.charAt(spaceLoc - 1) == ',') {
                    input = input.substring(0, spaceLoc) + input.substring(spaceLoc + 1);
                }
            }

            //first comma location
            int firstCommaLoc = input.indexOf(",");
            int secondCommaLoc = input.indexOf(",", firstCommaLoc + 1);
            int spaceLoc = secondCommaLoc;

            if (input.indexOf(' ', secondCommaLoc + 2)!=-1) {
                spaceLoc = input.indexOf(" ", secondCommaLoc + 2);
            }
            String tele;
            String auton;

            String form2SecHalf;
            if (!input.equalsIgnoreCase("stop")) {
                tele = "tele" + input.substring(0, firstCommaLoc);
                auton = "auto" + input.substring(0, firstCommaLoc);
            }
            else{
                tele = "";
                auton = "";
            }
            //Format 1 outputs into Strings.XML and Format 2 outputs into SQLiteInfo.XML
            if (teleMode && !input.equalsIgnoreCase("stop"))
                format1 =  format1 + "\n        <item>" + tele + " " + (input.substring(firstCommaLoc + 1, secondCommaLoc)).toUpperCase() +"</item>";
            if (autonMode && !input.equalsIgnoreCase("stop"))
                format1 = format1 + "\n        <item>" + auton + " " +(input.substring(firstCommaLoc + 1, secondCommaLoc)).toUpperCase() + "</item>";
            //putting the information in string format

            if (spaceLoc!=secondCommaLoc) {
                form2SecHalf = (input.substring(secondCommaLoc + 1, secondCommaLoc + 2)).toUpperCase() + input.substring(secondCommaLoc + 2, spaceLoc) + input.substring(spaceLoc, spaceLoc + 2).toUpperCase() + input.substring(spaceLoc + 2) + "</string>";
                if(teleMode)
                    format2 = format2 + "\n    <string name=\"" + tele + "\">" + form2SecHalf;
                if(autonMode)
                    format2 = format2 + "\n    <string name=\"" + auton + "\">" + form2SecHalf;
            } else if(!input.equalsIgnoreCase("stop")) {
                form2SecHalf = (input.substring(secondCommaLoc + 1, secondCommaLoc + 2)) + input.substring(secondCommaLoc + 2) + "</string>";
                if(teleMode)
                    format2 = format2 + "\n    <string name=\"" + tele + "\">" + form2SecHalf;
                if(autonMode)
                    format2 = format2 + "\n    <string name=\"" + auton + "\">" + form2SecHalf;
            }
            if(!input.equalsIgnoreCase("stop")){
                if(teleMode)
                    format3 = format3 + "\n        <item>@string/tele" + input.substring(0, firstCommaLoc) +  "</item>";
                if (autonMode)
                    format3 = format3 + "\n        <item>@string/auto"+ input.substring(0,firstCommaLoc) + "</item>";
            }
            firstLoop = false;
        }
//ref in code then name displayed for the string name format
        format2 = "<resources>\n" +
                "    <string name=\"app_name\">ScoutingApp</string>\n" +
                "    <string name=\"textbox_default\">0</string>\n" +
                "    <!--These first two strings are universal for all apps -->\n" +
                "    <string name=\"matchNumber\">matchNumber</string>\n" +
                "    <string name=\"teamNumber\">teamNumber</string>\n" +
                "    <string name=\"scouterName\">scouterName</string>\n" +
                "    <string name=\"fouls\">fouls</string>\n"+
                "    <string name=\"techFouls\">techFouls</string>\n"+
                "    <string name=\"yellowCards\">yellowCards</string>\n"+
                "    <string name=\"redCards\">redCards</string>\n" +
                "    <string name=\"teleDefense\">teleDefense</string>\n" +
                "    <string name=\"lostComms\">lostComms</string>\n" +
                "    <string name=\"climb\">climb</string>\n" +
                "    <string name=\"comments\">comments</string>" + format2 +
                "\n\n\n" +
                "    <!--\n" +
                "    <string name=\"RadioGroup1\">value6</string>\n" +
                "    <string name=\"Radio1\">level1</string>\n" +
                "    <string name=\"Radio2\">level2</string>\n" +
                "    <string name=\"Radio3\">level3</string>-->\n" +
                "    <!--Value1 must be match number, Value 2 must be Scouter name, name of teamNumbers cannot be altered-->\n" +
                "    <!--Radios need 1 item in the array below for the radio group, and it needs 3 values above for each radio button-->\n" +
                "    <!--Radios are special, each radio group needs 1 entry, and the item in the array below must equal the id of the radio group-->\n" +
                "    <string-array name=\"datapoints\">\n" +
                "        <item>@string/matchNumber</item>\n" +
                "        <item>@string/teamNumber</item>\n" +
                "        <item>@string/scouterName</item>\n" +
                "        <item>@string/fouls</item>\n"+
                "        <item>@string/techFouls</item>\n"+
                "        <item>@string/yellowCards</item>\n"+
                "        <item>@string/redCards</item>\n"+
                "        <item>@string/teleDefense</item>\n"+
                "        <item>@string/lostComms</item>\n"+
                "        <item>@string/climb</item>\n" +
                "        <item>@string/comments</item>" + format3 + "\n" +
                "        <!--<item>@string/RadioGroup1</item>-->\n" +
                "    </string-array>\n" +
                "<!-- Data required to be input before pressing the submit button in the app -->\n" +
                "    <string-array name=\"reqData\">\n" +
                "        <item>@string/matchNumber</item>\n" +
                "        <item>@string/teamNumber</item>\n" +
                "        <item>@string/climb</item>\n" +
                "        <item>@string/scouterName</item>\n"+
                "   </string-array>";

        format1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "\n" +
                "    <!-- format: <item>SQL Stuff<item/>\n" +
                "    COPY AND PASTE FROM STRINGS!!!\n" +
                "        Use normal SQL pieces to create columns -->\n" +
                "\n" +
                "    <!-- ScoutingData Database -->\n" +
                "    <string-array name=\"scoutingDataKeys\">\n" +
                "        <item>matchNumber INTEGER</item>\n" +
                "        <item>teamNumber INTEGER</item>\n" +
                "        <item>scouterName TEXT</item>\n" +
                "        <item>fouls INTEGER</item>\n" +
                "        <item>techFouls INTEGER</item>\n" +
                "        <item>yellowCards INTEGER</item>\n" +
                "        <item>redCards INTEGER</item>\n" +
                "        <item>teleDefense TEXT</item>\n" +
                "        <item>lostComms INTEGER</item>\n" +
                "        <item>climb TEXT</item>\n" +
                "        <item>comments TEXT</item>" + format1 + "    </string-array>\n" +
                "</resources>";

        //printing those strings to the files (new files outside the "main" folder)
        FileWriter format1Write = new FileWriter("SQLiteInfo.xml");
        format1Write.write(format1);
        format1Write.close();
        FileWriter format2Write = new FileWriter("strings.xml");
        format2Write.write(format2);
        format2Write.append("\n   <string-array name=\"teamNames\"> \n");
        String name = "";
        while (!name.equalsIgnoreCase("stop")){
            if (!firstLoop) {
                previousEnterLoc = enterLoc;
            }
            //grabs the length of a line of input
            enterLoc = str.indexOf("\n", enterLoc + 1);
            if (enterLoc == -1) {
                break;
            }
            //takes a line of input
            input = str.substring(previousEnterLoc + 1, enterLoc);
            input = input.trim();
            name = input;
            if (name.contains(",")){
                if (name.length() - 1 == name.lastIndexOf(",")){
                name = name.substring(0,name.lastIndexOf(","));
            }
                ArrayList<String> names = new ArrayList<>();
                while (name.contains(",")){

                    names.add(name.substring(0, name.indexOf(",")));

                    name = name.substring(name.indexOf(",") + 2);

                }
                names.add(name);
                for (int i = 0; i < names.size(); i++){
                    name = names.get(i);
                    format2Write.append("        <item>" + name + "</item>\n");
                }
            } else {
                if (name.equalsIgnoreCase("stop")) {
                    break;
                }
                format2Write.append("        <item>" + name + "</item>\n");
            }
        }
        format2Write.append( "   </string-array> \n" + "</resources>");
        format2Write.close();
    }

    //This method is weird, but it works (Jacob's words)
    public static String read_files(URL fileName){
        String data = "";
        String output = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileName.openStream()));
            while ((data = br.readLine()) != null)
                output = output + data + "\n";
        } catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }
}

//everything about this file is janky but it all works