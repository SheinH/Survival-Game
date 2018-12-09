package SurvivalGame.GameLogic;

public enum Terrain {
    GRASS('G'),DESERT('D'), WATER('W');

    private char character;
    Terrain(char c){
        character = c;
    }

    public char getCharacter() {
        return character;
    }
}
