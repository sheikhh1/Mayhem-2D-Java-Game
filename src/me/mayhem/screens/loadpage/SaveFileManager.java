package me.mayhem.screens.loadpage;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveFileManager {

    private static final File SAVE_DIR = Paths.get("/saves/").toFile();
    private static final List<File> SAVE_FILES = new ArrayList<>();

    public static void init() {
        if (!SAVE_DIR.exists()) {
            SAVE_DIR.mkdir();
            return;
        }

        File[] files = SAVE_DIR.listFiles();

        if (files == null) {
            return;
        }

        SAVE_FILES.addAll(Arrays.asList(files));
    }

    public static List<File> getSaveFiles() {
        return SAVE_FILES;
    }
}
