package View;

import Controller.GameController;
import Controller.MainMenuController;
import Model.Game;
import Model.Projectile;
import Model.Target;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainView extends Application {

    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    private final String tux = "Ressources/Images/px_tux.png";
    private final String tuxTir = "Ressources/Images/px_tux_tir.png";
    private final String background = "Ressources/Images/background_main_menu.png";

    private Text titleBeginGame, chronoToBegin, score;

    private Font buttonFont = Font.loadFont(getClass().getResource(
            "../Ressources/Font/space_invaders.ttf").toExternalForm(), 30);
    private Font titleFont = Font.loadFont(getClass().getResource(
            "../Ressources/Font/space_invaders.ttf").toExternalForm(), 80);
    private Font scoreFont = Font.loadFont(getClass().getResource(
            "../Ressources/Font/space_invaders.ttf").toExternalForm(), 10);
    private String styleBoutons = "-fx-cursor: hand; " +
            "-fx-text-fill: #DE1110;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(30, 10, 10, 0.9), 10, 0.9, 0, 0);";
    private String styleTitle = "-fx-text-color: #DEDEDE;";
    private Group root;
    private VBox texteBeginGame;

    private ImageView windowsImage, appleImage, ubuntuImage;
    private ImageView shootingTux;
    private List<Projectile> projectiles;
    private List<ImageView> appleList, windowsList, ubuntuList;
    private Image bckgrnd = new Image(background);
    private Scene scene;


    private Game game;

    private boolean isAnimationStopped;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        game = new Game();
        projectiles = new ArrayList<>();
        isAnimationStopped = false;
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);

        MainMenuController mmc = new MainMenuController(this, root);

        scene.setOnKeyTyped(mmc);

        displayMainMenu();

        primaryStage.setTitle("Space DUT");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    private void displayMainMenu() {

        root.getChildren().clear();
        windowsImage = new ImageView(Target.WINDOWS);
        ubuntuImage = new ImageView(Target.UBUNTU);
        appleImage = new ImageView(Target.APPLE);
        ImageView tuxLeft = new ImageView(tux);
        tuxLeft.setFitWidth(105);
        tuxLeft.setFitHeight(135);
        tuxLeft.setX(50);
        tuxLeft.setY(400);

        ImageView tuxRight = new ImageView(tuxTir);
        tuxRight.setX(650);
        tuxRight.setY(400);
        tuxRight.setFitWidth(105);
        tuxRight.setFitHeight(135);

        Text mainTitle;

        Button play, levels, options, quit;

        mainTitle = new Text("Space DUT");
        mainTitle.setFont(titleFont);
        mainTitle.setStyle(styleTitle);
        mainTitle.setFill(new Color(1, 1, 1, 1));

        play = new Button("PLAY");
        play.setBackground(null);
        play.setStyle(styleBoutons);
        play.setFont(buttonFont);
        play.setOnAction((event) -> {
            displayGame();
            System.out.println("play");
        });

        levels = new Button("Choose a level");
        levels.setBackground(null);
        levels.setStyle(styleBoutons);
        levels.setFont(buttonFont);
        levels.setOnAction((event) -> {
            displayLevels();
            System.out.println("Levels");
        });

        options = new Button("Options");
        options.setBackground(null);
        options.setStyle(styleBoutons);
        options.setFont(buttonFont);
        options.setOnAction((event) -> {
            displayOptions();
            System.out.println("Options");
        });

        quit = new Button("Quit");
        quit.setBackground(null);
        quit.setStyle(styleBoutons);
        quit.setFont(buttonFont);
        quit.setOnAction((event) -> System.exit(0));

        VBox listeBoutons = new VBox(mainTitle, new Text(), new Text(), new Text(), play, levels, options, quit);
        listeBoutons.setBackground(new Background(new BackgroundImage(bckgrnd,
                null, null, null, null)));
        listeBoutons.setAlignment(Pos.BOTTOM_CENTER);
        listeBoutons.setSpacing(20);
        listeBoutons.setFillWidth(true);
        listeBoutons.setMinHeight(HEIGHT);
        listeBoutons.setMinWidth(WIDTH);

        root.getChildren().addAll(listeBoutons, tuxLeft, tuxRight);

        windowsImage.setX(-50);
        windowsImage.setY(150);
        root.getChildren().add(windowsImage);

        appleImage.setX(-200);
        appleImage.setY(150);
        root.getChildren().add(appleImage);

        ubuntuImage.setX(-350);
        ubuntuImage.setY(150);
        root.getChildren().add(ubuntuImage);
    }

    public void displayGame() {
        root.getChildren().clear();

        score = new Text(game.getScore() + " pts");
        score.setFont(scoreFont);
        score.setFill(Color.WHITE);
        score.setX(WIDTH - 80);
        score.setY(HEIGHT - 10);
        shootingTux = new ImageView(tuxTir);
        shootingTux.setX(380);
        shootingTux.setY(500);
        shootingTux.setFitWidth(42);
        shootingTux.setFitHeight(54);

        Pane stackPane = new Pane();
        stackPane.setBackground(new Background(new BackgroundImage(bckgrnd,
                null, null, null, null)));

        stackPane.setMinHeight(HEIGHT);
        stackPane.setMinWidth(WIDTH);


        root.getChildren().add(stackPane);
        appleList = new ArrayList<>();
        windowsList = new ArrayList<>();
        ubuntuList = new ArrayList<>();

        for (int i = 0; i < game.currentLevel().getNbApple(); i++) {
            appleList.add(game.currentLevel().getAppleList().get(i).getSkin());
            appleList.get(i).setX(112 + i * 50);
            appleList.get(i).setY(50);
            appleList.get(i).setFitWidth(25);
            appleList.get(i).setFitHeight(25);
        }

        for (int i = 0; i < game.currentLevel().getNbWindows(); i++) {
            windowsList.add(game.currentLevel().getWindowsList().get(i).getSkin());
            windowsList.get(i).setX(112 + i * 50);
            windowsList.get(i).setY(120);
            windowsList.get(i).setFitWidth(25);
            windowsList.get(i).setFitHeight(25);
        }

        for (int i = 0; i < game.currentLevel().getNbUbuntu(); i++) {
            ubuntuList.add(game.currentLevel().getUbuntuList().get(i).getSkin());
            ubuntuList.get(i).setX(112 + i * 50);
            ubuntuList.get(i).setY(190);
            ubuntuList.get(i).setFitWidth(25);
            ubuntuList.get(i).setFitHeight(25);
        }

        root.getChildren().addAll(appleList);
        root.getChildren().addAll(windowsList);
        root.getChildren().addAll(ubuntuList);
        root.getChildren().addAll(shootingTux, score);

        List<ImageView> lifeList = new ArrayList<>();

        for (int i = 0; i < game.getLives(); i++) {
            lifeList.add(new ImageView(tux));
            lifeList.get(i).setX(-85 + i * 20);
            lifeList.get(i).setY(450);
            lifeList.get(i).setScaleX(0.1);
            lifeList.get(i).setScaleY(0.1);
            root.getChildren().add(lifeList.get(i));
        }

        GameController gc = new GameController(this, game);
        scene.setOnKeyPressed(gc);
        scene.setOnKeyReleased(gc);

        beginningAnimation();

    }

    private void beginningAnimation() {

        titleBeginGame = new Text(game.currentLevel().getTitle());
        titleBeginGame.setX(150);
        titleBeginGame.setY(250);
        titleBeginGame.setFont(titleFont);
        titleBeginGame.setStyle(styleTitle);
        titleBeginGame.setFill(new Color(1, 1, 1, 1));

        chronoToBegin = new Text("");
        chronoToBegin.setFont(titleFont);
        chronoToBegin.setStyle(styleBoutons);

        texteBeginGame = new VBox(titleBeginGame, chronoToBegin);
        texteBeginGame.setAlignment(Pos.CENTER);
        texteBeginGame.setSpacing(100);
        texteBeginGame.setFillWidth(true);
        texteBeginGame.setBackground(new Background(new BackgroundFill(Color.color(0.1, 0.1, 0.15), null, null)));
        texteBeginGame.setOpacity(0.9);
        texteBeginGame.setMinHeight(HEIGHT);
        texteBeginGame.setMinWidth(WIDTH);
        root.getChildren().add(texteBeginGame);
    }

    public void majBeginningAnimation(String text) {
        if (text == null) {
            root.getChildren().remove(texteBeginGame);
            return;
        }
        chronoToBegin.setText(text);
    }

    private void displayLevels() {
        root.getChildren().clear();
        root.getChildren().add(new ImageView(background));

        Text mainTitle;
        Button[] levels = new Button[game.getLevels().length];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new Button("level " + i);
            levels[i].setBackground(null);
            levels[i].setStyle(styleBoutons);
            levels[i].setFont(buttonFont);
            int finalI = i;
            levels[i].setOnAction((event) -> {
                game.setCurrentLevel(finalI);
                displayGame();
            });
        }

        mainTitle = new Text("Levels");
        mainTitle.setFont(titleFont);
        mainTitle.setStyle(styleTitle);
        mainTitle.setFill(new Color(1, 1, 1, 1));

        Text retour = new Text("Return");
        retour.setStyle(styleBoutons);
        retour.setFont(buttonFont);
        retour.setOnMouseClicked((event) -> displayMainMenu());

        HBox listeBoutons1 = new HBox(levels[0], levels[1], levels[2]);
        listeBoutons1.setAlignment(Pos.BOTTOM_CENTER);
        HBox listeBoutons2 = new HBox(levels[3], levels[4], levels[5]);
        listeBoutons2.setAlignment(Pos.BOTTOM_CENTER);
        VBox listeBoutons = new VBox(mainTitle, new Text(), listeBoutons1, listeBoutons2, new Text(), retour);
        listeBoutons.setAlignment(Pos.CENTER);
        listeBoutons.setSpacing(20);
        listeBoutons.setFillWidth(true);
        listeBoutons.setMinHeight(HEIGHT);
        listeBoutons.setMinWidth(WIDTH);
        root.getChildren().add(listeBoutons);
    }

    private void displayOptions() {
        root.getChildren().clear();
        root.getChildren().add(new ImageView(background));

        Text mainTitle;
        Text musicOnOff = new Text("Music On");
        musicOnOff.setStyle(styleBoutons);
        musicOnOff.setFont(buttonFont);
        musicOnOff.setOnMouseClicked((event) ->
                musicOnOff.setText(musicOnOff.getText().equals("Music On")?"Music Off":"Music On"));
        Text effectsOnOff = new Text("Effects On");
        effectsOnOff.setStyle(styleBoutons);
        effectsOnOff.setFont(buttonFont);
        effectsOnOff.setOnMouseClicked((event) ->
                effectsOnOff.setText(effectsOnOff.getText().equals("Effects On")?"Effects Off":"Effects On"));

        Text retour = new Text("Return");
        retour.setStyle(styleBoutons);
        retour.setFont(buttonFont);
        retour.setOnMouseClicked((event) -> displayMainMenu());

        mainTitle = new Text("Options");
        mainTitle.setFont(titleFont);
        mainTitle.setStyle(styleTitle);
        mainTitle.setFill(new Color(1, 1, 1, 1));

        VBox listeBoutons = new VBox(mainTitle, new Text(), new Text(), musicOnOff, effectsOnOff, new Text(), retour);
        listeBoutons.setAlignment(Pos.CENTER);
        listeBoutons.setSpacing(20);
        listeBoutons.setFillWidth(true);
        listeBoutons.setMinHeight(HEIGHT);
        listeBoutons.setMinWidth(WIDTH);
        root.getChildren().add(listeBoutons);
    }

    public boolean majAnimation() {
        if (isAnimationStopped) return true;
        windowsImage.setX(windowsImage.getX() > 900 ? -50 : windowsImage.getX() + 1);
        appleImage.setX(appleImage.getX() > 900 ? -50 : appleImage.getX() + 1);
        ubuntuImage.setX(ubuntuImage.getX() > 900 ? -50 : ubuntuImage.getX() + 1);
        return false;
    }

    public void moveLeft() {
        if (shootingTux.getX() > -20) {
            shootingTux.setX(shootingTux.getX() - 3);
        }
    }

    public void moveRight() {
        if (shootingTux.getX() < WIDTH - 23) {
            shootingTux.setX(shootingTux.getX() + 3);
        }
    }

    public void shoot(int mark) {
        Projectile projectile = new Projectile(mark, shootingTux.getX() + 20, shootingTux.getY());
        projectiles.add(projectile);
        root.getChildren().add(projectile.getShoot());
        shootingTux.setStyle(null);
    }

    public void majProjectiles() {
        projectiles = projectiles.stream()
                .peek(Projectile::moveOn)
                .filter(projectile -> !collision(projectile, appleList))
                .filter(projectile -> !collision(projectile, ubuntuList))
                .filter(projectile -> !collision(projectile, windowsList))
                .filter(projectile -> root.getChildren().contains(projectile.getShoot()))
                .collect(Collectors.toList());
    }

    private boolean collision(Projectile projectile, List<ImageView> targetList) {
        if (projectile.getY() < -2) {
            root.getChildren().remove(projectile.getShoot());
            majScore(-10);
            return true;
        }
        for (ImageView target : targetList) {
            if (projectile.getX() + 2 < target.getX() + 25 && projectile.getX() - 2 > target.getX()
                    && projectile.getY() + 2 < target.getY() + 25 && projectile.getY() - 2 > target.getY()) {
                targetList.remove(target);
                majScore(10);
                root.getChildren().removeAll(projectile.getShoot(), target);
                return true;
            }
        }
        return false;
    }

    private void majScore(int points) {

        game.majScore(points);
        score.setText(game.getScore() + " pts");
    }

    public List<ImageView> getAppleList() {
        return appleList;
    }

    public List<ImageView> getUbuntuList() {
        return ubuntuList;
    }

    public List<ImageView> getWindowsList() {
        return windowsList;
    }

    public void changeAura(int powerAura) {
        if (powerAura == 3) {
            shootingTux.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 0, 0, 0.9), 10, 0.9, 0, 0);");
        } else if (powerAura == 2) {
            shootingTux.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 100, 0, 0.9), 10, 0.9, 0, 0);");
        } else {
            shootingTux.setStyle(null);
        }
    }
}