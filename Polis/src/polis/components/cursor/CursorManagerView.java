package polis.components.cursor;

import javafx.scene.layout.Pane;
import polis.components.Manager;
import polis.components.cursor.cursortile.CursorTileView;

public class CursorManagerView extends Pane {

    private CursorManager model;

    public CursorManagerView(Manager manager){
        this.model = manager.getActiveManager();

        this.setTranslateX((double)(model.getGridSize()-1) * model.getCellSize());

        for(CursorTileView[] row : model.getTiles()){
            this.getChildren().addAll(row);
        }

        this.setOnMouseMoved(e -> model.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> model.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> model.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> model.place());
    }

    public void setModel(CursorManager model) {
        this.model = model;
    }

}