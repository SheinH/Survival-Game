package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Rabbit;

public class FieldObjectFactory {
    public FieldObject makeObject(char c){
        switch (c){
            case 'A':
                return new Agent();
            case 'R':
                return new Rabbit();

        }
    }
}
