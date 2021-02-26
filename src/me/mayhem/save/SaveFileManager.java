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
 * this is the managaer to saved data to file
 */
public class SaveFileManager {

    public static final File SAVE_DIR = Paths.get("saves/").toFile();
    private static final List<SaveData> SAVE_FILES = new ArrayList<>();

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

    public static List<SaveData> getSaveFiles() {
        return SAVE_FILES;
    }

    public static void addSaveFile(SaveData saveData) {
        SAVE_FILES.add(saveData);
    }

    /**
     * method to write the contents of the savedata to file
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
