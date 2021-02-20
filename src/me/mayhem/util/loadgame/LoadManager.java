package me.mayhem.util.loadgame;

import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.loadgame.reader.ReadFile;

import java.io.ObjectStreamException;
import java.io.StringReader;

public class LoadManager {
    String filename;
    private Object[] data = new Object[4];


    public LoadManager(String filename){
        this.filename = filename;
        ReadFile reader = new ReadFile();
        reader.read(data, filename);
    }
    public String getCharName(){
        return (String) data[0];
    }

    public Integer getCurrentLevel(){
        return (Integer) data[1];
    }

    public Integer getCurrentLayout(){
        return (Integer) data[2];
    }

    public String getTime(){
        return (String) data[3];
    }

    public void dataToString(){
        for (Object thing: data){
            System.out.println(thing);
        }
    }

}