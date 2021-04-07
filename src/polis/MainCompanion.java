package polis;

import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import prog2.util.Viewport;
import polis.components.Manager;
import polis.components.buildings.BuildingTileManagerView;
import polis.components.cursor.CursorManagerView;
import polis.other.MusicPlayer;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

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

    private final static int CELL_SIZE = 64;
    private final static int GRID_SIZE = 32;
    public StackPane main;

    private Manager manager;
    private MusicPlayer musicPlayer;
    private Viewport viewport;

    public void initialize() throws FileNotFoundException, URISyntaxException {

        musicPlayer = new MusicPlayer();

        this.manager = new Manager(GRID_SIZE, CELL_SIZE);
        CursorManagerView cursorView = new CursorManagerView(manager);
        BuildingTileManagerView buildingView = new BuildingTileManagerView(manager);

        int length = CELL_SIZE * GRID_SIZE;
        Polygon poly = new Polygon(0, 0, length, 0.5 * length, 0, length, -length, 0.5 * length);
        poly.setFill(Color.web("#88A129"));

        manager.setView(cursorView);
        manager.getActiveManager().setTool("select");

        StackPane stackPane = new StackPane(
                poly,
                buildingView,
                cursorView
        );

        Viewport view = new Viewport(stackPane, 0.5);
        viewport= view;

        viewportStackPane.getChildren().add(view);

        shoppingButton.setOnAction(e -> handleButtonEvent(0, "commerce"));
        residenceButton.setOnAction(e -> handleButtonEvent(0, "residence"));
        factoryButton.setOnAction(e ->handleButtonEvent(0, "industry"));
        roadButton.setOnAction(e -> handleButtonEvent(1,"road"));
        bulldozerButton.setOnAction(e -> handleButtonEvent(2, "bulldoze"));
        selectButton.setOnAction(e -> handleButtonEvent(2, "select"));
        nukeButton.setOnAction(e -> handleButtonEvent("reset"));
        soundButton.setOnAction(e -> handleButtonEvent("mute"));

        main.setOnKeyPressed(this::handleKeyEvent);

        viewport.requestFocus();
    }

    private void handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:
                manager.setActiveManager(0, "residence");
                break;
            case I:
                manager.setActiveManager(0, "industry");
                break;
            case C:
                manager.setActiveManager(0, "commerce");
                break;
            case S:
                manager.setActiveManager(1);
                break;
            case B:
                manager.setActiveManager(2, "bulldoze");
                break;
            case ESCAPE:
                manager.setActiveManager(2, "select");
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


}
