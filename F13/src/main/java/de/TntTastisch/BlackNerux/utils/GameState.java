package de.TntTastisch.BlackNerux.utils;

public enum GameState {

    LOBBYPHASE,
    INGAME,
    ENDPHASE;

    public static GameState gameState;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GameState.gameState = gameState;
    }
}
