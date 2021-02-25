package me.mayhem.game.level.except;

public class LevelLoadingException extends RuntimeException {

    public LevelLoadingException(String levelId, String reason) {
        super ("Failed to load " + levelId + " for reason: " + reason);
    }
}
