package me.mayhem.util.loadgame.reader;

import java.io.File;
import java.util.Scanner;

public class ReadFile {


    public ReadFile(){

    }
    public Object read(Object[] data, String fileName){
        try {
            File myObj = new File("resources/savefiles/" + fileName + ".txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                data[i] = myReader.nextLine();
                i++;
            }

            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}

