package Model;


import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Target> appleList, windowsList, ubuntuList;
    private int nbApple, nbWindows, nbUbuntu;
    private int velocity;
    private String title;

    // velocity = 1 to 10, 10 very fast, 1 very slow
    // level = 0 no weapon for targets
    //         1 - 2 a few weapon
    //         3 - 4 half a weapon
    //         5 every enemies has a weapon

    Level(int nbApple, int nbWindows, int nbUbuntu, int velocity, int level) {
        this.nbApple = nbApple;
        this.nbWindows = nbWindows;
        this.nbUbuntu = nbUbuntu;
        this.velocity = velocity;
        String[] LEVELS = {
                "First Step",
                "Precision",
                "Hold your fire",
                "Save your ass",
                "Miss everything !",
                "What a mess !"

        };
        title = LEVELS[level];
        System.out.println(title);

        int i;
        appleList = new ArrayList<>();
        windowsList = new ArrayList<>();
        ubuntuList = new ArrayList<>();
        for (i = 0; i < nbApple; i++)
            appleList.add(new Target(Target.APPLE, level >= 1, velocity));
        for (i = 0; i < nbWindows; i++)
            windowsList.add(new Target(Target.WINDOWS, level >= 3, velocity));
        for (i = 0; i < nbUbuntu; i++)
            ubuntuList.add(new Target(Target.UBUNTU, level == 5, velocity));
    }

    public List<Target> getAppleList() {
        return appleList;
    }

    public List<Target> getWindowsList() {
        return windowsList;
    }

    public List<Target> getUbuntuList() {
        return ubuntuList;
    }

    public int getNbApple() {
        return nbApple;
    }

    public int getNbWindows() {
        return nbWindows;
    }

    public int getNbUbuntu() {
        return nbUbuntu;
    }

    public int getVelocity() {
        return velocity;
    }

    public String getTitle() {
        return title;
    }
}
