package me.mayhem.util.savegame.filecreator;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    File save;
    public CreateFile(String filename) {
        save = create(filename);
    }
    private File create(String filename){
        File myObj = new File("resources/savefiles",filename + ".txt");
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myObj;
    }

    public File getSave() {
        return save;
    }
}