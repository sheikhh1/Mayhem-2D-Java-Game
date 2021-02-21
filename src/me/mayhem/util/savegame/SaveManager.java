package me.mayhem.util.savegame;

import me.mayhem.game.GameManager;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.savescreen.SaveScreenManager;
import me.mayhem.util.savegame.filecreator.CreateFile;


import java.io.File;
import java.util.PrimitiveIterator;


public class SaveManager {
    private Object[] data;
    private File filetoWrite;

    public SaveManager(SaveScreenManager saveScreen, GameScreenManager currentGame){
        findData(saveScreen, currentGame);

        this.filetoWrite = makeFile();

        if (filetoWrite != null ){
            save();
        }
        else {
            System.out.println("the file cannot be created for some reason");
        }
    }

    private boolean save(){
        Writefile writer = new Writefile();
        writer.write(filetoWrite, data);
        return false;
    }

    public void findData(SaveScreenManager saveScreen, GameScreenManager current){
        String playerName= saveScreen.getBox().getWritten();// get file name
        String level = current.getGame().getCurrentLevel();

        String levelsCompleted = current.getGame().getLevelsCompleted();

        String time= "13";  //TODO get the current game time

        data = new Object[]{playerName, level, levelsCompleted, time};
    }

    public Object[] getData() {
        return data;
    }
    private File makeFile(){
        CreateFile fileCreator = new CreateFile((String) data[0]);

        return fileCreator.getSave();
    }

}