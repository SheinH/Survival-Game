package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.Items.Item;
import SurvivalGame.GameLogic.Items.Spear;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ItemGridPane {
    int highlightIndex;
    GridPane gridPane;
    ImageView border;
    HashMap<Class<? extends Item>, Image> images;
    List<ItemDisplay> itemDisplays;
    Consumer<Integer> onClickConsumer;
    static Image itemBorderImage = new Image("file:res" +File.separator + "New_Tool_Pictures" + File.separator + "itemborder.png",50,50,true,false);
    static Image highlightBorderImage = new Image("file:res" + File.separator + "New_Tool_Pictures" + File.separator + "itemborderselected.png",50,50,true,false);


    private class ItemDisplay{
        Label quantityLabel;
        ImageView itemImage;
        ImageView border;
        StackPane stackPane;
        boolean highlighted;
        public ItemDisplay(Item i){
            stackPane = new StackPane();
            stackPane.setPrefSize(50,50);
            border = new ImageView(itemBorderImage);
            stackPane.getChildren().add(border);
            itemImage = new ImageView(images.get(i.getClass()));
            stackPane.getChildren().add(itemImage);
            Label label = new Label("" + i.getQuantity());
            label.setPadding(new Insets(5));
            label.setTextFill(Color.BLACK);
            label.setStyle("-fx-font-weight:bold;");
            stackPane.getChildren().add(label);
            StackPane.setAlignment(label, Pos.BOTTOM_RIGHT);
        }


        public ItemDisplay(){
            stackPane = new StackPane();
            stackPane.setPrefSize(50,50);
            border = new ImageView(itemBorderImage);
            stackPane.getChildren().add(border);
            Label label = new Label("");
            label.setPadding(new Insets(5));
            label.setTextFill(Color.BLACK);
            label.setStyle("-fx-font-weight:bold;");
            stackPane.getChildren().add(label);
            StackPane.setAlignment(label, Pos.BOTTOM_RIGHT);
        }

        public boolean isHighlighted() {
            return highlighted;
        }

        public void setHighlight(boolean b){
            if(b)
                border.setImage(highlightBorderImage);
            else
                border.setImage(itemBorderImage);
            highlighted = b;
        }

        public void setItem(Item i){
            stackPane.getChildren().remove(itemImage);
            itemImage = new ImageView(images.get(i.getClass()));
            stackPane.getChildren().add(itemImage);
            quantityLabel.setText("" + i.getQuantity());
        }

        public StackPane getStackPane() {
            return stackPane;
        }
    }

    public ItemGridPane(HashMap<Class<? extends Item>, Image> images){
        this.images = images;
        itemDisplays = new ArrayList<>();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setHighlightIndex(int highlightIndex) {
        ItemDisplay before = itemDisplays.get(this.highlightIndex);
        ItemDisplay after = itemDisplays.get(highlightIndex);
        if(before != after)
            before.setHighlight(false);
        boolean hl = after.isHighlighted();
        after.setHighlight(!hl);
        this.highlightIndex = highlightIndex;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void showList(ItemsList list){
        clearList();
        IntStream.range(0,list.size()).forEachOrdered(n -> {
            ItemDisplay display = new ItemDisplay(list.get(n));
            itemDisplays.add(display);
            gridPane.add(display.getStackPane(),n,0);
            StackPane sp = display.stackPane;
            sp.setOnMouseClicked((e) -> onClickConsumer.accept(n));
        });
    }

    public void setOnClickConsumer(Consumer<Integer> onClickConsumer) {
        this.onClickConsumer = onClickConsumer;
    }

    public void clearList(){
        gridPane.getChildren().clear();
        itemDisplays.clear();
    }

}
