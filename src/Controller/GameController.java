package Controller;

import Animation.GameTimeline;
import Model.Game;
import Model.Level;
import View.MainView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent> {

    private MainView mainView;
    private Game game;
    private Level currentLevel;

    private BooleanProperty leftKeyTyped, rightKeyTyped, spaceBarKeyTyped;

    public GameController(MainView mainView, Game game) {
        leftKeyTyped = new SimpleBooleanProperty(false);
        rightKeyTyped = new SimpleBooleanProperty(false);
        spaceBarKeyTyped = new SimpleBooleanProperty(false);
        this.mainView = mainView;
        this.game = game;
        currentLevel = game.currentLevel();
        GameTimeline gameTimeline = new GameTimeline(this);
        gameTimeline.start();
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.LEFT) {
                leftKeyTyped.set(true);
            } else if (event.getCode() == KeyCode.RIGHT) {
                rightKeyTyped.set(true);
            } else if (event.getCode() == KeyCode.SPACE) {
                spaceBarKeyTyped.set(true);
            }
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            if (event.getCode() == KeyCode.LEFT) {
                leftKeyTyped.set(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                rightKeyTyped.set(false);
            } else if (event.getCode() == KeyCode.SPACE) {
                spaceBarKeyTyped.set(false);
            }
        }
    }

    public synchronized void moveLeft() {
        mainView.moveLeft();
    }

    public synchronized void moveRight() {
        mainView.moveRight();
    }

    public synchronized void shoot(int mark) {
        mainView.shoot(mark);
    }

    public synchronized boolean isLeftKeyTyped() {
        return leftKeyTyped.get();
    }

    public synchronized boolean isRightKeyTyped() {
        return rightKeyTyped.get();
    }

    public synchronized boolean isSpaceBarKeyTyped() {
        return spaceBarKeyTyped.get();
    }

    public synchronized void majProjectiles() {
        mainView.majProjectiles();
        if (mainView.getAppleList().isEmpty() && mainView.getUbuntuList().isEmpty() && mainView.getWindowsList().isEmpty()) {
            currentLevel = game.nextLevel();
            spaceBarKeyTyped.set(false);
            leftKeyTyped.set(false);
            rightKeyTyped.set(false);
            mainView.displayGame();
        }
    }

    public void setBeginText(String text) {
        mainView.majBeginningAnimation(text);
    }
}
