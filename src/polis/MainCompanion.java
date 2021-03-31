package polis;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import other.Viewport;
import polis.components.playingfield.cursor.CursorManager;
import polis.components.playingfield.cursor.CursorManagerView;
import polis.components.playingfield.buildings.BuildingTileManagerView;

public class MainCompanion {

    public StackPane viewportStackPane;
    public Button roadButton;
    public Button shoppingButton;

    public void initialize(){


        CursorManager manager = new CursorManager(32,64);
        CursorManagerView cursorView = new CursorManagerView(manager);
        BuildingTileManagerView buildingView = new BuildingTileManagerView(manager.getBuildingField());

        manager.setView(cursorView);

        StackPane stackPane = new StackPane(
                buildingView,
                cursorView
        );

        Viewport view = new Viewport(stackPane,0.5);
        viewportStackPane.getChildren().add(view);

        roadButton.setOnAction(actionEvent -> manager.setActiveManager(0));
        shoppingButton.setOnAction(actionEvent -> manager.setActiveManager(1));

    }

}
