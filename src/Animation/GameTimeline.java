package Animation;


import Controller.GameController;
import javafx.animation.AnimationTimer;

public class GameTimeline extends AnimationTimer {

    private long lastUpdate = 0;
    private GameController gameController;
    private boolean isSpaceBarKeyTyped;
    private double timeSpaceBarPressed;
    private double initTimeSpaceBarPressed;
    private boolean isLevelStarted;
    private boolean firstTime = true;

    public GameTimeline(GameController gameController) {
        gameController.setPowerAura(0);
        this.gameController = gameController;
        isSpaceBarKeyTyped = false;
        timeSpaceBarPressed = 0;
        isLevelStarted = false;
        initTimeSpaceBarPressed = 0;
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
            if (gameController.isSpaceBarKeyTyped() && initTimeSpaceBarPressed == 0) {
                initTimeSpaceBarPressed = now;
                System.out.println("inittimespacebarpressed");
            } else if (gameController.isSpaceBarKeyTyped()) {
                System.out.println("time SpaceBar Pressed");
                timeSpaceBarPressed = now;
                gameController.setPowerAura((int) ((timeSpaceBarPressed - initTimeSpaceBarPressed) / 300_000_000));

                if (gameController.getPowerAura() >= 4) {
                    gameController.setPowerAura(3);
                } else if (gameController.getPowerAura() <= 1) {
                    gameController.setPowerAura(1);
                } else {
                    gameController.setPowerAura(2);
                }

                System.out.println(gameController.getPowerAura());
                timeSpaceBarPressed = now;
                isSpaceBarKeyTyped = true;
            } else if (!gameController.isSpaceBarKeyTyped() && isSpaceBarKeyTyped) {
                System.out.println("time space bar released");
                gameController.shoot();
                isSpaceBarKeyTyped = false;
                gameController.setPowerAura(0);
                timeSpaceBarPressed = initTimeSpaceBarPressed = 0;
            }
            lastUpdate = now;
        }
        gameController.majProjectiles();
    }
}