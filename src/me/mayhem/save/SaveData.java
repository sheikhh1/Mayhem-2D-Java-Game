package me.mayhem.save;

import me.mayhem.game.level.difficulty.Difficulty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * A Data Transfer Object used for storing the relevant data about a save, and it's file.
 * Majority immutable to improve memory performance but the id must allow to change as for level completion
 *
 */
public class SaveData {

    private final File file;
    private final String name;
    private final Difficulty difficulty;

    private int id;

    /**
     *
     * Default constructor for making the DTO. Private as to ensure all instances are created using
     * {@link SaveData#from(File)} so it can load directly from a given file
     *
     * @param file the file the data is loaded from and saved in
     * @param name the name of the player and the file
     * @param id the id of the level the player is currently at
     * @param difficulty the difficulty level of the game
     */
    private SaveData(File file, String name, int id, Difficulty difficulty) {
        this.file = file;
        this.name = name;
        this.id = id;
        this.difficulty = difficulty;
    }

    /**
     *
     * Gets the file the level is stored in
     *
     * @return The storage
     */
    public File getFile() {
        return this.file;
    }

    /**
     *
     * The name of the file and the player (without the .txt suffix)
     *
     * @return The user's chosen name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * The id of the current level the player is playing
     *
     * @return {@link me.mayhem.game.level.Level} id
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * Updates the id of the current level
     *
     * @param id {@link me.mayhem.game.level.Level} id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     *
     * Gets the difficulty of the game the user selected
     *
     * @return The difficulty selected at the start of the creation of this save
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     *
     * Loads and instance of this DTO from the given file
     *
     * @param file the file to be read from
     * @return the data object that has been read
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
     * Creates an instance of the DTO for a level that has yet to have a file created for it
     *
     * @param name the name of the player and the file
     * @param id the id of the level
     * @param difficulty  the difficulty of the level
     * @return a new instance containing the game's save data
     */
    public static SaveData of(String name, int id, Difficulty difficulty) {
        return new SaveData(new File(SaveFileManager.SAVE_DIR + File.separator + name + ".txt"), name, id, difficulty);
    }
}
