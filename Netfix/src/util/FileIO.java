package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    public ArrayList<String> readData(String path) {
        ArrayList<String> dataList = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            while (scan.hasNextLine()) {
                String line = scan.nextLine(); // Car specifics
                dataList.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return dataList;
    }

    public static void saveData(String header,ArrayList<String> dataList, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write("Motor, Make, Model, Registration number, Km/l, Max range, battery capacity, Has particle filter, Number of doors"+"\n"); //Giv csv filen en header
            for (String item : dataList) {
                writer.write(item + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File not saved");

        }

    }
}

