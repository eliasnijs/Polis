package polis.components.playingfield.cursor;

import javafx.scene.layout.Pane;
import polis.components.playingfield.Manager;
import polis.components.playingfield.cursor.cursortile.CursorTileView;


public class CursorManagerView extends Pane {

    private final Manager manager;
    private CursorManager model;

    public CursorManagerView(Manager manager){
        this.manager = manager;
        this.model = manager.getActiveManager();
        this.setTranslateX((double)(model.getGridSize()-1)*model.getCellSize());

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
