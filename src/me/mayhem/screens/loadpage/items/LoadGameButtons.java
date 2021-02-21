package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.Vector;
import me.mayhem.util.loadgame.LoadManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class LoadGameButtons {
    private File[] gamefiles;
    private ArrayList<ButtonInteractable> saveGames = new ArrayList<>();
    private boolean pageChange = true;

    public LoadGameButtons(){
        gamefiles = findFiles();
        createButtons(gamefiles);
    }
    private File[] findFiles() {
        File[] files = new File[0];

        try {
            File parent = new File("resources/savefiles");

            files = parent.listFiles();
            if (files == null){
                return null;
            }
            // Get the names of the files by using the .getName() method
            for (File file : files) {
                System.out.println(file.getName());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return files;
    }
    private void createButtons(File[] files){
        if (files.length != 0){

            int fileHeight = (Mayhem.SCREEN_HEIGHT-100) / files.length, filenumber = 1, i= 1;
            SaveInstanceButton save;

            for(File file: files){
                if (i == 1){
                    Vector vec = new Vector(400, 100);
                    save = new SaveInstanceButton(createSaveInstanceRect(fileHeight, files.length,vec), file );
                    save.setText(save.getFilename());
                }
                else if(i == 2){
                    Vector vec = new Vector(400, 300);
                    save = new SaveInstanceButton(createSaveInstanceRect(fileHeight, files.length,vec), file );
                    save.setText(save.getFilename());
                }
                else{
                    Vector vec = new Vector(400, 500);
                    save = new SaveInstanceButton(createSaveInstanceRect(fileHeight, files.length,vec), file );
                    save.setText(save.getFilename());
                }

                saveGames.add(save);

                filenumber++;
                if(i != 3){
                    i++;
                }
                else{
                    i = 1;
                }
            }
        }
    }
    public Shape createSaveInstanceRect(int fileHeight, int numSaves, Vector position){
        RectangleShape rect = new RectangleShape();

        rect.setPosition(position.toVector());
        rect.setSize(new Vector(200,100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return rect;
    }
    public void draw(RenderWindow window, int savepage){

        if (pageChange){
            for (ButtonInteractable button : saveGames){
                button.unregister();
            }

            for (int i = 3 * savepage; i < (savepage * 3) + 3; i ++){
                if (i < saveGames.size() ){

                    ButtonInteractable button  = saveGames.get(i);
                    button.register();
                    button.draw(window);
                }
            }
            pageChange = false;
        }
        else{
            for (int i = 3 * savepage; i < (savepage * 3) + 3; i ++){
                if (i < saveGames.size() ){

                    ButtonInteractable button  = saveGames.get(i);
                    button.draw(window);
                }
            }
        }
    }
    public float getNumberOfSave(){
        System.out.println(gamefiles.length);
        return gamefiles.length;
    }

    public void setPageChange(boolean pageChange) {
        this.pageChange = pageChange;
    }

    public boolean isPageChange() {
        return pageChange;
    }
}
