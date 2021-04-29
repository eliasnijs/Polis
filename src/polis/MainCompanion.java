package polis;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import polis.components.Manager;
import polis.datakeepers.FieldData;
import polis.uicomponents.Background;
import polis.other.MusicPlayer;
import polis.uicomponents.statistics.StatsPanel;
import prog2.util.Viewport;

import java.util.Map;

import static javafx.scene.input.KeyCode.*;

/**
 *  Dit is de companion klasse voor main.fxml
 *  Hier worden de non-visuele aspecten opgestart en de visuele componenten geconfigureerd indien nodig.
 * **/

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
    public ToggleButton playButton;
    public ToggleButton treeButton;
    public Slider timeSlider;
    public StackPane main;
    public StatsPanel statsPanel;

    private Viewport viewport;
    private Manager manager;
    private MusicPlayer musicPlayer;
    public Button activeButton;

    private final Map<KeyCode,Runnable> events = Map.of (
            R, () -> changeCursor(residenceButton, 0, "residence"),
            I, () -> changeCursor(factoryButton, 0, "industry"),
            C, () -> changeCursor(shoppingButton, 0, "commerce"),
            S, () -> changeCursor(roadButton, 1, "road"),
            B, () -> changeCursor(bulldozerButton, 2, "bulldoze"),
            N, () -> {manager.getPlayingField().setStartupTiles(treeButton.isSelected());viewport.requestFocus();},
            M, () -> {musicPlayer.switchMute(); viewport.requestFocus();},
            SPACE, () -> {
                playButton.setSelected(!playButton.isSelected());
                manager.getFrameLine().play(playButton.isSelected());
                viewport.requestFocus(); },
            ESCAPE, () -> changeCursor(selectButton, 2, "select")
    );

    public void initialize() {
        manager = new Manager();
        manager.getPlayingField().setStartupTiles(treeButton.isSelected());

        initialiseActions();
        activeButton = selectButton;
        setActiveButton(selectButton);

        statsPanel.setModel(manager.getStatsModel());

        musicPlayer = new MusicPlayer();

        StackPane field = new StackPane(manager.getPlayingFieldView(), manager.getCursorView());
        StackPane mainStack = new StackPane(configureBackground(field), field);
        Viewport view = new Viewport(mainStack, 0.5);
        viewport = view;
        viewportStackPane.getChildren().add(view);

        view.setFocusTraversable(true);
        viewport.requestFocus();
    }

    public Node configureBackground(StackPane field){
        // Kleine aanpassing aan de locatie van het speelveld zodat alles gecentreerd is
        field.setTranslateX(4.0 * FieldData.getCellSize());
        field.setTranslateY(4.5 * FieldData.getCellSize());
        return new Background(3);
    }

    public void initialiseActions() {
        shoppingButton.setOnAction(o -> events.get(C).run());
        residenceButton.setOnAction(o -> events.get(R).run());
        factoryButton.setOnAction(o -> events.get(I).run());
        roadButton.setOnAction(o -> events.get(S).run());
        bulldozerButton.setOnAction(o -> events.get(B).run());
        selectButton.setOnAction(o -> events.get(ESCAPE).run());
        nukeButton.setOnAction(o -> events.get(N).run());
        soundButton.setOnAction(o -> events.get(M).run());
        playButton.setOnAction(o -> {playButton.setSelected(!playButton.isSelected()); events.get(SPACE).run();});
        main.setOnKeyPressed(o -> {
            if (events.containsKey(o.getCode())) {
                events.get(o.getCode()).run();} });
        timeSlider.valueProperty().addListener((observableValue, oldVal, newVal) ->
                manager.getFrameLine().setSpeed(newVal.intValue()) );
    }

    public void changeCursor(Button button, int mode, String tool) {
        manager.setActiveManager(mode, tool);
        setActiveButton(button);
        viewport.requestFocus();
    }

    public void setActiveButton(Button a) {
        activeButton.setStyle("-fx-background-color: #00000000");
        this.activeButton = a;
        activeButton.setStyle("-fx-background-color: #5DC9D4");
    }

}
