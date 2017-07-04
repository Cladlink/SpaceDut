package Animation;


import Controller.GameController;
import javafx.animation.AnimationTimer;

public class GameTimeline extends AnimationTimer {

    private long lastUpdate = 0;
    private GameController gameController;
    private boolean isSpaceBarKeyTyped;
    private double timeSpaceBarPressed;
    private boolean isLevelStarted;
    private boolean firstTime = true;

    public GameTimeline(GameController gameController) {
        this.gameController = gameController;
        isSpaceBarKeyTyped = false;
        timeSpaceBarPressed = 0;
        isLevelStarted = false;
    }

    @Override
    public void handle(long now) {
        if (firstTime) {
            lastUpdate = now;
            firstTime = false;
            return;
        }
        if (!isLevelStarted) {
            if (now - lastUpdate >= 5_000_000_000L) {
                gameController.setBeginText(null);
                isLevelStarted = true;
                lastUpdate = now;
            } else if (now - lastUpdate >= 4_000_000_000L) {
                gameController.setBeginText("GO !");
            } else if (now - lastUpdate >= 3_000_000_000L) {
                gameController.setBeginText("1");
            } else if (now - lastUpdate >= 2_000_000_000L) {
                gameController.setBeginText("2");
            } else if (now - lastUpdate >= 1_000_000_000L) {
                gameController.setBeginText("3");
            }
            return;
        }
        if (now - lastUpdate >= 10_000) {

            if (gameController.isLeftKeyTyped()) {
                gameController.moveLeft();
            } else if (gameController.isRightKeyTyped()) {
                gameController.moveRight();
            }

            if (gameController.isSpaceBarKeyTyped() && !isSpaceBarKeyTyped) {
                isSpaceBarKeyTyped = true;
                timeSpaceBarPressed = now;
            } else if (!gameController.isSpaceBarKeyTyped() && isSpaceBarKeyTyped) {
                timeSpaceBarPressed = (int) ((now - timeSpaceBarPressed) / 300_000_000);
                if (timeSpaceBarPressed > 3) {
                    timeSpaceBarPressed = 3;
                } else if (timeSpaceBarPressed < 2) {
                    timeSpaceBarPressed = 1;
                }
                gameController.shoot((int) timeSpaceBarPressed);
                isSpaceBarKeyTyped = false;
                timeSpaceBarPressed = 0;
            }
            lastUpdate = now;
        }
        gameController.majProjectiles();
    }
}