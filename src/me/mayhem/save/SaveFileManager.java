package me.mayhem.save;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveFileManager {

    public static final File SAVE_DIR = Paths.get("/saves/").toFile();
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

    public static void save(SaveData saveData) {
        //TODO: write to file
    }
}
