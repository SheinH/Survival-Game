package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Terrain;

public class Lion extends Carnivore{

    public Lion(){
        super(40,20,3);
        setMoveSpeed(Terrain.DESERT, 5);
        setMoveSpeed(Terrain.GRASS, 3);
        setMoveSpeed(Terrain.WATER, 0);
        setMoveableTerrain(Terrain.DESERT,true);
        setMoveableTerrain(Terrain.GRASS,true);
    }

    @Override
    public char getChar() {
        return 'L';
    }
}
