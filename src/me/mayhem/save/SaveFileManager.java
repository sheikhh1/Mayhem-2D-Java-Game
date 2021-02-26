package me.mayhem.save;

import me.mayhem.Mayhem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This is the static factory that stores the cache for all save files from the game
 * They are all cached to prevent lag when trying to load files mid-screen change.
 *
 */
public class SaveFileManager {

    /**
     * The directory where the save files are stored
     */
    public static final File SAVE_DIR = Paths.get("saves/").toFile();
    private static final List<SaveData> SAVE_FILES = new ArrayList<>();

    /**
     *
     * Called asynchronously on startup. This firstly creates the save directory, and if it was already present it
     * loads and caches all the files found in the save directory
     *
     */
    public static void init() {
        if (!SAVE_DIR.exists()) {
            SAVE_DIR.mkdir();
            return;
        }

        File[] files = SAVE_DIR.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            SAVE_FILES.add(SaveData.from(file));
        }
    }

    /**
     *
     * Gets a {@link List} of all the cached {@link SaveData} loaded at startup
     *
     * @return The list of save files
     */
    public static List<SaveData> getSaveFiles() {
        return SAVE_FILES;
    }

    /**
     *
     * Adds a new save file to the cache (when a new game is started)
     *
     * @param saveData The new save data
     */
    public static void addSaveFile(SaveData saveData) {
        SAVE_FILES.add(saveData);
    }

    /**
     *
     * This method is used to asynchronously write the data from the {@link SaveData} to the file that belongs to it
     * This is asynchronous as not to freeze the main thread when writing to the file
     *
     * @param saveData the data that is to be written
     */
    public static void save(SaveData saveData) {
        Mayhem.THREAD_POOL.submit(() -> {
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(saveData.getFile()))) {
                fileWriter.append("name:").append(saveData.getName()).append(System.lineSeparator())
                        .append("difficulty:").append(saveData.getDifficulty().name()).append(System.lineSeparator())
                        .append("id:").append(String.valueOf(saveData.getId()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     *
     * Checks if a {@link SaveData} already exists with the specified name
     *
     * @param name the name to be checked
     * @return a boolean for it the name given is a file
     */
    public static boolean doesNameExist(String name) {
        for (SaveData saveFile : SAVE_FILES) {
            if (saveFile.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }
}
