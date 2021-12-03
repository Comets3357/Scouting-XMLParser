package main;

import java.io.*;
import java.net.URL;

public class main {

    public static void main(String[] args){
        //finding local path of file
        URL url = main.class.getResource("strings.xml");
        //reading file
        String fileCont = read_files(url);
        //String fileCont now contains all the values in strings.xml
        //below you can test parsing through this string to get the values you need

        write_files(url, fileCont);
        System.out.println(fileCont);
    }

    //This method is weird, but it works
    public static String read_files(URL fileName){
        String data = "";
        String output = "";
        //File file = new File(fileName);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileName.openStream()));
            while ((data = br.readLine()) != null)
                output = output + data + "\n";
        } catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public static void write_files(URL filename, String data){
        try {
            FileWriter filewriter = new FileWriter("testing123.xml");
            filewriter.write(data);
            filewriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
