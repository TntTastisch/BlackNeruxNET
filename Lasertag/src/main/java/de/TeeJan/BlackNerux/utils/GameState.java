package de.TeeJan.BlackNerux.utils;

public enum GameState {

    LOBBY,
    INGAME,
    END;

    public static GameState gamestate;

    public static void setGamestate(GameState gamestate) {
        GameState.gamestate = gamestate;
    }

    public static GameState getGamestate() {
        return gamestate;
    }
}
