package de.TntTastisch.BlackNerux.utils;

public enum GameState {

    LOBBY,
    INGAME,
    END;

    public static GameState gameState;

    public static void setGameState(GameState gameState) {
        GameState.gameState = gameState;
    }

    public static GameState getGameState() {
        return gameState;
    }
}
