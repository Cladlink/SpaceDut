package Model;


public class Game {

    private int lives;
    private int score;
    private int currentLevel;
    private final Level[] levels = {
            new Level(0, 0, 1, 1, 0),
            new Level(12, 12, 12, 3, 1),
            new Level(12, 12, 12, 5, 2),
            new Level(12, 12, 12, 7, 3),
            new Level(12, 12, 12, 9, 4),
            new Level(12, 12, 12, 10, 5)

    };

    public Game() {
        this.lives = 3;
        currentLevel = 0;
    }

    public void addLive() {
        lives++;
    }

    public void removeLive() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public Level nextLevel() {
        return levels[++currentLevel];
    }

    public Level currentLevel() {
        return levels[currentLevel];
    }

    public void majScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
