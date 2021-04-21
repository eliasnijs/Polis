package polis;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import polis.components.Manager;
import polis.datakeepers.FieldData;
import polis.helpers.HelperPoly;
import polis.other.Background;
import polis.other.MusicPlayer;
import prog2.util.Viewport;

public class MainCompanion {

    public StackPane viewportStackPane;
    public Button roadButton;
    public Button shoppingButton;
    public Button factoryButton;
    public Button residenceButton;
    public Button nukeButton;
    public Button bulldozerButton;
    public Button selectButton;
    public Button soundButton;
    public Button playButton;
    public ToggleButton treeButton;
    public StackPane main;

    public Button activeButton;
    private Manager manager;
    private MusicPlayer musicPlayer;
    private Viewport viewport;

    public void initialize() {

        musicPlayer = new MusicPlayer();

        this.manager = new Manager();
        manager.getPlayingField().setStartupTiles(treeButton.isSelected());
        setActiveButton(selectButton);

        StackPane field = new StackPane(
                manager.getPlayingFieldView(),
                manager.getCursorView()
        );
        Node background = background(true, field);
        StackPane mainStack = new StackPane(background, field);
        Viewport view = new Viewport(mainStack, 0.5);
        viewport = view;
        viewportStackPane.getChildren().add(view);

        initialiseButtonActions();

        view.setFocusTraversable(true);
        viewport.requestFocus();

        musicPlayer.switchMute();
    }

    public Node background(boolean imageBackground, StackPane field) {
        Node node;
        if (imageBackground) {
            node = new Background(3);
            // Kleine aanpassing aan de locatie van het speelveld zodat alles gecentreerd is
            field.setTranslateX(4.0 * FieldData.getCellSize());
            field.setTranslateY(4.5 * FieldData.getCellSize());
        } else {
            HelperPoly poly = new HelperPoly();
            poly.setFill(Color.web("#88A129"));
            node = poly;
        }
        return node;
    }

    public void initialiseButtonActions() {
        shoppingButton.setOnAction(e -> changeCursor(shoppingButton, 0, "commerce"));
        residenceButton.setOnAction(e -> changeCursor(residenceButton, 0, "residence"));
        factoryButton.setOnAction(e -> changeCursor(factoryButton, 0, "industry"));
        roadButton.setOnAction(e -> changeCursor(roadButton, 1, "road"));
        bulldozerButton.setOnAction(e -> changeCursor(bulldozerButton, 2, "bulldoze"));
        selectButton.setOnAction(e -> changeCursor(selectButton, 2, "select"));
        nukeButton.setOnAction(e -> handleButtonEvent("reset"));
        soundButton.setOnAction(e -> handleButtonEvent("mute"));
        playButton.setOnAction(e -> handleButtonEvent("play"));
        main.setOnKeyPressed(this::handleKeyEvent);
    }

    private void handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:
                changeCursor(residenceButton, 0, "residence");
                break;
            case I:
                changeCursor(factoryButton, 0, "industry");
                break;
            case C:
                changeCursor(shoppingButton, 0, "commerce");
                break;
            case S:
                changeCursor(roadButton, 1, "road");
                break;
            case B:
                changeCursor(bulldozerButton, 2, "bulldoze");
                break;
            case N:
                handleButtonEvent("reset");
                break;
            case M:
                handleButtonEvent("mute");
                break;
            case SPACE:
                handleButtonEvent("play");
                break;
            case ESCAPE:
                changeCursor(selectButton, 2, "select");
                break;
            case A:
                manager.getPlayingField().getActorField().newActor(0, 15);
        }
    }

    public void changeCursor(Button button, int mode, String tool) {
        manager.setActiveManager(mode, tool);
        setActiveButton(button);
        viewport.requestFocus();
    }

    private void handleButtonEvent(String tool) {
        switch (tool) {
            case "reset":
                manager.getPlayingField().setStartupTiles(treeButton.isSelected());
                break;
            case "mute":
                muteMusicPlayer();
                break;
        }
        viewport.requestFocus();
    }

    public void muteMusicPlayer() {
        musicPlayer.switchMute();
    }

    public void setActiveButton(Button a) {
        if (activeButton != null) {
            activeButton.setStyle("-fx-background-color: #00000000");
        }
        this.activeButton = a;
        activeButton.setStyle("-fx-background-color: #5DC9D4");
    }

}
