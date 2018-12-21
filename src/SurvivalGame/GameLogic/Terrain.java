package SurvivalGame.GameLogic;

public enum Terrain {
    GRASS('G'),DESERT('D'), WATER('W');

    private char character;

    Terrain(char character){
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

}

