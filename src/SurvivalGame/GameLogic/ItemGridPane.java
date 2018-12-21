package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.Items.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.List;

public class ItemGridPane {
    GridPane gridPane;
    ImageView border;
    HashMap<Class<? extends FieldObject>, Image> images;
    List<ItemDisplay> itemDisplays;


    private class ItemDisplay{
        Label quantityLabel;
        ImageView itemImage;
        ImageView border;
        StackPane stackPane;
        public ItemDisplay(Item i){
            border =
        }
    }

    public ItemGridPane(HashMap<Class<? extends FieldObject>, Image> images){
        this.images = images;
    }

    public void showList(ItemsList list){

    }

    public ItemDisplay itemToDisplay(Item item){

    }

}
