package polis;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import other.Viewport;
import polis.components.playingfield.Manager;
import polis.components.playingfield.buildings.BuildingTileManagerView;
import polis.components.playingfield.cursor.CursorManagerView;

public class MainCompanion {

    public StackPane viewportStackPane;
    public Button roadButton;
    public Button shoppingButton;
    public Button factoryButton;
    public Button residenceButton;
    public Button nukeButton;
    public Button bulldozerButton;


    private final static int CELL_SIZE = 64;
    private final static int GRID_SIZE = 32;

    public void initialize(){

        Manager manager = new Manager(GRID_SIZE, CELL_SIZE);
        CursorManagerView cursorView = new CursorManagerView(manager);
        BuildingTileManagerView buildingView = new BuildingTileManagerView(manager.getBuildingField());

        Polygon poly = new Polygon(
                0, 0,
                CELL_SIZE * GRID_SIZE, 0.5 * CELL_SIZE * GRID_SIZE,
                0, CELL_SIZE * GRID_SIZE,
                -CELL_SIZE * GRID_SIZE, 0.5 * CELL_SIZE * GRID_SIZE);
        poly.setFill(Color.web("#58A67A"));


        manager.setView(cursorView);

        StackPane stackPane = new StackPane(
                poly,
                buildingView,
                cursorView
        );

        Viewport view = new Viewport(stackPane,0.5);
        viewportStackPane.getChildren().add(view);

        roadButton.setOnAction(actionEvent -> manager.setActiveManager(1));

        shoppingButton.setOnAction(actionEvent -> manager.setActiveManager(0));
        residenceButton.setOnAction(actionEvent -> manager.setActiveManager(0));
        factoryButton.setOnAction(actionEvent -> manager.setActiveManager(0));

    }

}
