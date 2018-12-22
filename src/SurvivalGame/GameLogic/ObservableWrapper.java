package SurvivalGame.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObservableWrapper<T> implements Observable<T>{
    private List<Consumer<T>> listenerList;
    private final T object;

    public ObservableWrapper(T object) {
        this.object = object;
        listenerList = new ArrayList<>();
    }

    @Override
    public void addListener(Consumer<T> listener) {
        listenerList.add(listener);
        listener.accept(object);
    }

    @Override
    public void removeListener(Consumer<T> listener) {
        listenerList.remove(listener);
    }

    public void update(){
        listenerList.forEach(c -> c.accept(object));
    }
}
