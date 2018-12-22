package SurvivalGame.GameLogic.Items;

import SurvivalGame.GameLogic.Observable;
import SurvivalGame.GameLogic.ObservableWrapper;

import java.util.function.Consumer;

public abstract class Item implements Observable<Item> {

    private int quantity = 0;
    private double weight;
    private ObservableWrapper<Item> observers;

    public Item(double weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("weight must be >= 0");
        }
        this.weight = weight;
        observers = new ObservableWrapper<>(this);
    }

    public double getWeight() {return this.weight; }

    public abstract String getName();

    public int getQuantity(){return this.quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        observers.update();
    }

    public void changeQuantity(int change){
        if(this.quantity + change < 0){
            throw new IllegalArgumentException("Cannot use more than the availability");
        }

        setQuantity(quantity + change);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public void addListener(Consumer<Item> listener) {
        observers.addListener(listener);
    }

    @Override
    public void removeListener(Consumer<Item> listener) {
        observers.removeListener(listener);
    }
}
