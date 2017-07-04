package Model;


import javafx.scene.image.ImageView;

public class Target {
    public final static String WINDOWS = "Ressources/Images/px_windows.png";
    public final static String APPLE = "Ressources/Images/px_apple.png";
    public final static String UBUNTU = "Ressources/Images/px_ubuntu.png";

    private int pv;
    private ImageView skin;
    private boolean isDead;
    private boolean hasWeapon;

    public Target(String skin, boolean hasWeapon, int velocity) {
        this.pv = 1;
        this.skin = new ImageView(skin);
        this.isDead = false;
        this.hasWeapon = hasWeapon;
    }


    public void loosePv() {
        pv -= 1;
    }

    public ImageView getSkin() {
        return skin;
    }

    public void setSkin(ImageView skin) {
        this.skin = skin;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead() {
        isDead = true;
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }
}
