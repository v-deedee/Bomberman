package uet.oop.bomberman.countdown;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.util.Duration;

public class CountDown {
    private final ReadOnlyIntegerWrapper timeLeft;
    private final ReadOnlyDoubleWrapper timeLeftDouble;
    private final Timeline timeline;

    public ReadOnlyIntegerProperty timeLeftProperty() {
        return timeLeft.getReadOnlyProperty();
    }

    public int timeLeftValue() {
        return timeLeft.getReadOnlyProperty().getValue();
    }

    public CountDown(final int time) {
        timeLeft = new ReadOnlyIntegerWrapper(time);
        timeLeftDouble = new ReadOnlyDoubleWrapper(time);

        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(timeLeftDouble, time)
                ),
                new KeyFrame(
                        Duration.seconds(time),
                        new KeyValue(timeLeftDouble, 0)
                )
        );

        timeLeftDouble.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                timeLeft.set((int) Math.ceil(timeLeftDouble.get()));
            }
        });
    }

    public void Restart() {
        timeline.playFromStart();
    }

    public void pause() {
        timeline.pause();
    }

    public void Continue() {
        timeline.play();
    }
}