package Controller;


import Animation.MainMenuTimeline;
import View.MainView;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

public class MainMenuController implements EventHandler<KeyEvent> {
    private final Group root;
    private final MainView mainView;
    private final MainMenuTimeline mmt;
    private boolean animationStopped;

    public MainMenuController(MainView mainView, Group root) {
        this.mainView = mainView;
        this.root = root;
        mmt = new MainMenuTimeline(this);
        mmt.start();
        animationStopped = false;
    }

    public void majAnimation() {
        if (mainView.majAnimation()) {
            mmt.stop();
        }
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
