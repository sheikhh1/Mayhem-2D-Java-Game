package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class SaveInstanceButton extends ButtonInteractable {

    private final File loadFile;

    public SaveInstanceButton(Shape shape, File loadFile) {
        super(shape, "fonts/FreeSans.ttf", "filebutton");

        this.loadFile = loadFile;
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            SavedData data = from(this.loadFile);

            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new GameScreenManager(window, data.getDifficulty(), data.getId(), data.getName()));
        }
    }

    public static SavedData from(File file) {
        String name = null;
        Difficulty difficulty = null;
        int id = -1;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line = fileReader.readLine();

            if (line.startsWith("name:")) {
                name = line.replace("name:", "");
            } else if (line.startsWith("difficulty:")) {
                difficulty = Difficulty.valueOf(line.replace("difficulty:", ""));
            } else if (line.startsWith("id:")) {
                id = Integer.parseInt(line.replace("id:", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SavedData(name, id, difficulty);
    }

    private static final class SavedData {

        private final String name;
        private final int id;
        private final Difficulty difficulty;

        private SavedData(String name, int id, Difficulty difficulty) {
            this.name = name;
            this.id = id;
            this.difficulty = difficulty;
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.id;
        }

        public Difficulty getDifficulty() {
            return this.difficulty;
        }
    }
}
