package me.mayhem.save;

import me.mayhem.game.level.difficulty.Difficulty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * a class used to store data about the current data of the gamemanger
 */
public class SaveData {

    private final File file;
    private final String name;
    private final Difficulty difficulty;

    private int id;

    /**
     *
     * @param file the file to be saveed in
     * @param name the name of the player
     * @param id the id of the level
     * @param difficulty the difficulty level of the game
     */
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

    public void setId(int id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     *
     * @param file the file to be written from
     * @return the data object that has been written
     */
    public static SaveData from(File file) {
        String name = null;
        Difficulty difficulty = null;
        int id = -1;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                if (line.startsWith("name:")) {
                    name = line.replace("name:", "");
                } else if (line.startsWith("difficulty:")) {
                    difficulty = Difficulty.valueOf(line.replace("difficulty:", ""));
                } else if (line.startsWith("id:")) {
                    id = Integer.parseInt(line.replace("id:", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SaveData(file, name, id, difficulty);
    }

    /**
     *
     * @param name the name of the player
     * @param id the id of the level
     * @param difficulty  the difficulty of the level
     * @return a new savedata object containing the data of the level
     */
    public static SaveData of(String name, int id, Difficulty difficulty) {
        return new SaveData(new File(SaveFileManager.SAVE_DIR + File.separator + name + ".txt"), name, id, difficulty);
    }
}
