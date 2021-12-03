package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //initializing the strings
        String format1 = "";
        String format2 = "";
        String format3 = "";
        String test = "";
        boolean breakPls = false;

        //loop to get all 20 inputs
        while (!test.equals("stop")) {
            //getting user input
            System.out.println("Enter the data:");
            test = keyboard.nextLine();
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
            while (numOfCommas != 2) {
                System.out.println("Invalid data. Must have at least 2 commas:");
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

            //deleting whitespace (except for last space)
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
                format2 = format2 + "\n        <string name=\"" + test.substring(0, firstCommaLoc) + "\">" + (test.substring(secondCommaLoc + 1, secondCommaLoc + 2)).toUpperCase() + test.substring(secondCommaLoc + 2, spaceLoc) + test.substring(spaceLoc, spaceLoc + 2).toUpperCase() + test.substring(spaceLoc + 2) + "</string>";
            } else {
                format2 = format2 + "\n        <string name=\"" + test.substring(0, firstCommaLoc) + "\">" + (test.substring(secondCommaLoc + 1, secondCommaLoc + 2)) + test.substring(secondCommaLoc + 2) + "</string>";
            }
            format3 = format3 + "\n        <item>@string/" + test.substring(0, firstCommaLoc) + "</item>";
        }

        System.out.println("Scouter Name:");
        String scouterName = keyboard.nextLine();
        System.out.println("Match Number:");
        String matchNumber = keyboard.nextLine();
        System.out.println("Team Number:");
        String teamNumber = keyboard.nextLine();
        System.out.println("Climb:");
        String climb = keyboard.nextLine();

        format2 = "<resources>\n" +
                "    <string name=\"app_name\">ScoutingApp</string>\n" +
                "    <string name=\"textbox_default\">0</string>\n" +
                "    <!--These first two strings are universal for all apps -->\n" + format2 + "\n" +
                "\n" +
                "    <!--\n" +
                "    <string name=\"RadioGroup1\">value6</string>\n" +
                "    <string name=\"Radio1\">level1</string>\n" +
                "    <string name=\"Radio2\">level2</string>\n" +
                "    <string name=\"Radio3\">level3</string>-->\n" +
                "    <!--Value1 must be match number, Value 2 must be Scouter name, name of teamNumbers cannot be altered-->\n" +
                "    <!--Radios need 1 item in the array below for the radio group, and it needs 3 values above for each radio button-->\n" +
                "    <!--Radios are special, each radio group needs 1 entry, and the item in the array below must equal the id of the radio group-->\n" +
                "    <string-array name=\"datapoints\">" + format3 + "\n" +
                "        <!--<item>@string/RadioGroup1</item>-->\n" +
                "    </string-array>\n" +
                "    <string-array name=\"reqData\">\n" +
                "        <item>@string/scouterName</item>\n" +
                "        <item>@string/matchNumber</item>\n" +
                "        <item>@string/teamNumber</item>\n" +
                "        <item>@string/climb</item>\n" +
                "    </string-array>\n" +
                "\n" +
                "\n" +
                "</resources>";

        format1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "\n" +
                "    <!-- format: <item>SQL Stuff<item/>\n" +
                "    COPY AND PASTE FROM STRINGS!!!\n" +
                "        Use normal SQL pieces to create columns -->\n" +
                "\n" +
                "    <!-- ScoutingData Database -->\n" +
                "    <string-array name=\"scoutingDataKeys\">\n" + format1 + "\n    </string-array>\n" +
                "</resources>";

        System.out.println(format1);
        System.out.println(format2);

        //printing those strings to the files
        FileWriter format1Write = new FileWriter("SQLiteInfo1.xml");
        format1Write.write(format1);
        format1Write.close();
        FileWriter format2Write = new FileWriter("strings1.xml");
        format2Write.write(format2);
        format2Write.close();
    }
}

