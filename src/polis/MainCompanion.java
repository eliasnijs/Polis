package polis;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import other.Viewport;
import polis.components.Manager;
import polis.components.buildings.BuildingTileManagerView;
import polis.components.cursor.CursorManagerView;
import polis.other.MusicPlayer;

public class MainCompanion {

    public StackPane viewportStackPane;
    public Button roadButton;
    public Button shoppingButton;
    public Button factoryButton;
    public Button residenceButton;
    public Button nukeButton;
    public Button bulldozerButton;
    public Button selectButton;

    private final static int CELL_SIZE = 64;
    private final static int GRID_SIZE = 32;

    public void initialize(){

        Manager manager = new Manager(GRID_SIZE, CELL_SIZE);
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

        Viewport view = new Viewport(stackPane,0.5);
        view.setCursor(Cursor.NONE);

        viewportStackPane.getChildren().add(view);

        shoppingButton.setOnAction(e -> manager.setActiveManager(0,"commerce"));
        residenceButton.setOnAction(e -> manager.setActiveManager(0,"residence"));
        factoryButton.setOnAction(e -> manager.setActiveManager(0,"industry"));
        roadButton.setOnAction(e -> manager.setActiveManager(1));
        bulldozerButton.setOnAction(e -> manager.setActiveManager(2,"bulldoze"));
        selectButton.setOnAction(e -> manager.setActiveManager(2,"select"));
        nukeButton.setOnAction(e -> manager.reset());

    }

}
