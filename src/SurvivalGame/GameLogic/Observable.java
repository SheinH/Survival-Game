package SurvivalGame.GameLogic;

import java.util.function.Consumer;

public interface Observable<T> {
    void addListener(Consumer<T> listener);
    void removeListener(Consumer<T> listener);
}
