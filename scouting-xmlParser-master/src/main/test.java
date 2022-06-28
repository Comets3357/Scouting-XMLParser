package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /* This file reads from the file named "Read", where the input text goes.
        It will then put that file, line by line, into the format of "strings.xml" and "SQLiteInfo.xml"
        so those files aren't needed anymore, and we can just input things automatically.

        If there is a line that the program doesn't like, it will ask you to reenter it, and then move on
        to the next line afterwards. */


        //initializing the strings
        String format1 = "";
        String format2 = "";
        String format3 = "";
        String test = "";
        boolean breakPls = false;

        //reading the file
        URL url = main.class.getResource("Read"); //file path
        String str = read_files(url); //doing the actual reading

        //variables for getting the read file in the correct line-by-line format
        int enterLoc = 0;
        int previousEnterLoc = -1;
        boolean firstLoop = true;

        //loop to get all the inputs
        while (!test.equals("stop")) {
            //getting input
            if (!firstLoop) {
                previousEnterLoc = enterLoc;
            }
            enterLoc = str.indexOf("\n", enterLoc + 1);
            if (enterLoc==-1){
                break;
            }
            test = str.substring(previousEnterLoc + 1, enterLoc);
            test = test.trim();
            String test1 = test;
            if (test1.toUpperCase().equals("STOP")){
                break;
            }
            int numOfCommas = 0;
            int commaLoc = 0;
            while (test1.contains(",")){
                commaLoc = test1.indexOf(",");
                test1 = test1.substring(0, commaLoc) + test1.substring(commaLoc + 1);
                numOfCommas++;
            }

            //in case the program doesn't like a certain line, it will ask you to reenter it here
            while (numOfCommas != 2) {
                System.out.println(test);
                System.out.println("Invalid data. Input stop to end data input. Input the data to continue.");
                test = keyboard.nextLine();
                test = test.trim();
                test1 = test;
                if (test1.toUpperCase().equals("STOP")) {
                    breakPls = true;
                    break;
                }
                numOfCommas = 0;
                commaLoc = 0;
                while (test1.contains(",")) {
                    commaLoc = test1.indexOf(",");
                    test1 = test1.substring(0, commaLoc) + test1.substring(commaLoc + 1);
                    numOfCommas++;
                }
            }
            if (breakPls){
                break;
            }

            //deleting whitespace (except for last space if not camelCase)
            while (test.contains(", ")) {
                int spaceLoc = test.indexOf(" ");
                if (test.charAt(spaceLoc - 1) == ',') {
                    test = test.substring(0, spaceLoc) + test.substring(spaceLoc + 1);
                }
            }

            //first comma location
            int firstCommaLoc = test.indexOf(",");
            int secondCommaLoc = test.indexOf(",", firstCommaLoc + 1);
            int spaceLoc = secondCommaLoc;

            if (test.indexOf(' ', secondCommaLoc + 2)!=-1) {
                spaceLoc = test.indexOf(" ", secondCommaLoc + 2);
            }

            //putting the information in string format
            format1 = format1 + "\n        <item>" + test.substring(0, firstCommaLoc) + " " + (test.substring(firstCommaLoc + 1, secondCommaLoc)).toUpperCase() + "</item>";
            if (spaceLoc!=secondCommaLoc) {
                format2 = format2 + "\n    <string name=\"" + test.substring(0, firstCommaLoc) + "\">" + (test.substring(secondCommaLoc + 1, secondCommaLoc + 2)).toUpperCase() + test.substring(secondCommaLoc + 2, spaceLoc) + test.substring(spaceLoc, spaceLoc + 2).toUpperCase() + test.substring(spaceLoc + 2) + "</string>";
            } else {
                format2 = format2 + "\n    <string name=\"" + test.substring(0, firstCommaLoc) + "\">" + (test.substring(secondCommaLoc + 1, secondCommaLoc + 2)) + test.substring(secondCommaLoc + 2) + "</string>";
            }
            format3 = format3 + "\n        <item>@string/" + test.substring(0, firstCommaLoc) + "</item>";

            firstLoop = false;
        }

        format2 = "<resources>\n" +
                "    <string name=\"app_name\">ScoutingApp</string>\n" +
                "    <string name=\"textbox_default\">0</string>\n" +
                "    <!--These first two strings are universal for all apps -->\n" +
                "    <string name=\"matchNumber\">matchNumber</string>\n" +
                "    <string name=\"scouterName\">scouterName</string>\n" +
                "    <string name=\"teamNumber\">teamNumber</string>\n" +
                "    <string name=\"climb\">climb</string>" + format2 +
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
                "        <item>@string/scouterName</item>\n" +
                "        <item>@string/matchNumber</item>\n" +
                "        <item>@string/teamNumber</item>\n" +
                "        <item>@string/climb</item>" + format3 + "\n\n" +
                "        <!--<item>@string/RadioGroup1</item>-->\n" +
                "    </string-array>\n" +
                "    <string-array name=\"reqData\">\n" +
                "        <item>@string/matchNumber</item>\n" +
                "        <item>@string/teamNumber</item>\n" +
                "        <item>@string/climb</item>\n" +
                "   </string-array>"  ;

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
                "        <item>scouterName TEXT</item>\n" +
                "        <item>teamNumber INTEGER</item>\n" +
                "        <item>climb TEXT</item>" + format1 + "\n    </string-array>\n" +
                "</resources>";

        //printing those strings to the files (new files outside the "main" folder)
        FileWriter format1Write = new FileWriter("SQLiteInfo.xml");
        format1Write.write(format1);
        format1Write.close();
        FileWriter format2Write = new FileWriter("strings.xml");
        format2Write.write(format2);
        format2Write.append("   <string-array name=\"teamNames\"> \n");
        String name = "";
        System.out.println("enter a list of scouter.");
        while (!name.equalsIgnoreCase("stop")){
            name = keyboard.nextLine();
            if (name.contains(",")){
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
            }
            else {
                if (name.equalsIgnoreCase("stop"))
                    break;
                format2Write.append("        <item>" + name + "</item>\n");
            }
        }
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