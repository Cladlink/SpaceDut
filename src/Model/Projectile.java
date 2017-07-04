package Model;


import javafx.scene.image.ImageView;

public class Projectile {

    private static final String smallShoot = "Ressources/Images/small_shoot.png";
    private static final String mediumShoot = "Ressources/Images/medium_shoot.png";
    private static final String bigShoot = "Ressources/Images/big_shoot.png";

    private final ImageView shoot;

    public Projectile(int mark, double x, double y) {
        if (mark == 1) {
            shoot = new ImageView(smallShoot);
        } else if (mark == 2) {
            shoot = new ImageView(mediumShoot);
        } else {
            shoot = new ImageView(bigShoot);
        }
        shoot.setX(x);
        shoot.setY(y);
    }

    public ImageView getShoot() {
        return shoot;
    }

    public void moveOn() {
        shoot.setY(shoot.getY()-10);
    }

    public double getX() {
        return shoot.getX();
    }

    public double getY() {
        return shoot.getY();
    }

    @Override
    public String toString() {
        return shoot.getX() + " " + shoot.getY();
    }
}