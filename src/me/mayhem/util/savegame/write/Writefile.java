package me.mayhem.util.savegame.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writefile{
    public Writefile(){

    }

    // data [Player name, Current level, # levels beat, Gametime]

    public boolean write(File saveFile, Object[] data){
        boolean valid = true;
        try {
            FileWriter myWriter = new FileWriter(saveFile );
            for (Object thing : data){
                myWriter.write((String) thing);
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return valid;
    }
}
