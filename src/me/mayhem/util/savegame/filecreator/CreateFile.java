package me.mayhem.util.savegame.filecreator;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
    File save;
    public CreateFile(String filename) {
        save = create(filename);
    }
    private File create(String filename){
        try {
            File myObj = new File("resources/savefiles",filename + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            return myObj;

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

            return null;
        }

    }

    public File getSave() {
        return save;
    }
}