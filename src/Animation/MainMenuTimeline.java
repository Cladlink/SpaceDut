package Animation;

import Controller.MainMenuController;
import javafx.animation.AnimationTimer;

public class MainMenuTimeline extends AnimationTimer{

    private long lastUpdate = 0;
    private MainMenuController mainMenuController;

    public MainMenuTimeline(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 15_000_000) {
                mainMenuController.majAnimation();
                lastUpdate = now;
        }
    }
}
