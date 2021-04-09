package polis;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.other.Background;
import polis.other.clouds.CloudView;
import prog2.util.Viewport;
import polis.components.Manager;
import polis.components.buildings.BuildingFieldView;
import polis.components.cursor.CursorFieldView;
import polis.other.MusicPlayer;

public class MainCompanion  {

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

    public Button activeButton;

    private final static int CELL_SIZE = 64;
    private final static int GRID_SIZE = 32;
    public StackPane main;

    private Manager manager;
    private MusicPlayer musicPlayer;
    private Viewport viewport;

    public void initialize(){

        musicPlayer = new MusicPlayer();

        this.manager = new Manager(GRID_SIZE, CELL_SIZE);
        CursorFieldView cursorView = new CursorFieldView(manager);
        BuildingFieldView buildingView = new BuildingFieldView(manager);

        int length = CELL_SIZE * GRID_SIZE;
        Polygon poly = new Polygon(0, 0, length, 0.5 * length, 0, length, -length, 0.5 * length);
        poly.setFill(Color.web("#88A129"));

        manager.setView(cursorView);
        manager.getActiveManager().setTool("select");
        setActiveButton(selectButton);

        Background background = new Background();
        CloudView cloudView = new CloudView();

        StackPane field = new StackPane(
                buildingView,
                cursorView
        );

        field.setTranslateX( 4.0 * CELL_SIZE);
        field.setTranslateY(4.5 * CELL_SIZE);


        StackPane centerStack = new StackPane(
                background,
                field
        );

        Viewport view = new Viewport(centerStack, 0.5);
        viewport = view;

        viewportStackPane.getChildren().add(view);

        treeButton.setSelected(true);
        treeButton.setOnAction(e -> manager.switchTree());
        shoppingButton.setOnAction(e -> {handleButtonEvent(0, "commerce"); setActiveButton(shoppingButton);});
        residenceButton.setOnAction(e -> {handleButtonEvent(0, "residence"); setActiveButton(residenceButton);});
        factoryButton.setOnAction(e -> {handleButtonEvent(0, "industry"); setActiveButton(factoryButton);});
        roadButton.setOnAction(e -> {handleButtonEvent(1,"road"); setActiveButton(roadButton);});
        bulldozerButton.setOnAction(e -> {handleButtonEvent(2, "bulldoze"); setActiveButton(bulldozerButton);});
        selectButton.setOnAction(e -> {handleButtonEvent(2, "select"); setActiveButton(selectButton);});
        nukeButton.setOnAction(e -> {handleButtonEvent("reset");});
        soundButton.setOnAction(e -> {handleButtonEvent("mute");});
        playButton.setOnAction(e -> {handleButtonEvent("play");});

        main.setOnKeyPressed(this::handleKeyEvent);

        view.setFocusTraversable(true);
        viewport.requestFocus();
    }

    private void handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:
                manager.setActiveManager(0, "residence");
                setActiveButton(residenceButton);
                break;
            case I:
                manager.setActiveManager(0, "industry");
                setActiveButton(factoryButton);
                break;
            case C:
                manager.setActiveManager(0, "commerce");
                setActiveButton(shoppingButton);
                break;
            case S:
                manager.setActiveManager(1);
                setActiveButton(roadButton);
                break;
            case B:
                manager.setActiveManager(2, "bulldoze");
                setActiveButton(bulldozerButton);
                break;
            case ESCAPE:
                manager.setActiveManager(2, "select");
                setActiveButton(selectButton);
                break;
        }
    }

    private void handleButtonEvent(int mode, String tool){
        manager.setActiveManager(mode, tool);
        viewport.requestFocus();
    }

    private void handleButtonEvent(String tool){
        switch (tool) {
            case "reset":
                manager.reset();
                break;
            case "mute":
                muteMusicPlayer();
                break;
        }
        viewport.requestFocus();
    }

    public void muteMusicPlayer(){
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
