package polis;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import polis.datakeepers.FieldData;
import polis.helpers.HelperPoly;
import polis.other.Background;
import prog2.util.Viewport;
import polis.components.Manager;
import polis.components.playingfield.PlayingFieldView;
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

    public StackPane main;

    private Manager manager;
    private MusicPlayer musicPlayer;
    private Viewport viewport;

    public void initialize(){

        musicPlayer = new MusicPlayer();

        this.manager = new Manager();
        CursorFieldView cursorView = new CursorFieldView(manager);
        PlayingFieldView buildingView = new PlayingFieldView(manager);

        manager.setView(cursorView);
        manager.getActiveManager().setTool("select");
        setActiveButton(selectButton);
        manager.getBuildingField().setStartupTiles(treeButton.isSelected());

        StackPane field = new StackPane(buildingView, cursorView);
        Node background = background(false, field);
        StackPane mainStack = new StackPane(background, field);
        Viewport view = new Viewport(mainStack, 0.5);
        viewport = view;
        viewportStackPane.getChildren().add(view);

        setButtonActions();

        view.setFocusTraversable(true);
        viewport.requestFocus();
    }

    public Node background(boolean imageBackground, StackPane field){
        Node node;
        if (imageBackground) {
            node = new Background();
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

    public void setButtonActions(){
        shoppingButton.setOnAction(e -> {handleButtonEvent(0, "commerce"); setActiveButton(shoppingButton);});
        residenceButton.setOnAction(e -> {handleButtonEvent(0, "residence"); setActiveButton(residenceButton);});
        factoryButton.setOnAction(e -> {handleButtonEvent(0, "industry"); setActiveButton(factoryButton);});
        roadButton.setOnAction(e -> {handleButtonEvent(1,"road"); setActiveButton(roadButton);});
        bulldozerButton.setOnAction(e -> {handleButtonEvent(2, "bulldoze"); setActiveButton(bulldozerButton);});
        selectButton.setOnAction(e -> {handleButtonEvent(2, "select"); setActiveButton(selectButton);});
        nukeButton.setOnAction(e -> handleButtonEvent("reset"));
        soundButton.setOnAction(e -> handleButtonEvent("mute"));
        playButton.setOnAction(e -> handleButtonEvent("play"));
        main.setOnKeyPressed(this::handleKeyEvent);
    }

    private void handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:changeCursor(roadButton,0,"residence"); break;
            case I: changeCursor(roadButton,0,"industry"); break;
            case C: changeCursor(roadButton,0,"commerce"); break;
            case S: changeCursor(roadButton,1,"road"); break;
            case B: changeCursor(bulldozerButton,2,"bulldoze"); break;
            case ESCAPE: changeCursor(selectButton,2,"select"); break;
        }
    }

    public void changeCursor(Button button, int mode, String tool){
        manager.setActiveManager(2, "bulldoze");
        setActiveButton(button);
    }

    private void handleButtonEvent(int mode, String tool){
        manager.setActiveManager(mode, tool);
        viewport.requestFocus();
    }

    private void handleButtonEvent(String tool){
        switch (tool) {
            case "reset": manager.getBuildingField().setStartupTiles(treeButton.isSelected()); break;
            case "mute": muteMusicPlayer(); break;
        } viewport.requestFocus();
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
