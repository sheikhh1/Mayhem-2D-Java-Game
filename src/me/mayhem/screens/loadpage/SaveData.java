package me.mayhem.screens.loadpage;

import me.mayhem.game.level.difficulty.Difficulty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SaveData {

    private final File file;
    private final String name;
    private final int id;
    private final Difficulty difficulty;

    private SaveData(File file, String name, int id, Difficulty difficulty) {
        this.file = file;
        this.name = name;
        this.id = id;
        this.difficulty = difficulty;
    }

    public File getFile() {
        return this.file;
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

    public static SaveData from(File file) {
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

        return new SaveData(file, name, id, difficulty);
    }
}
