package polis.components.playingfield.cursor;

import javafx.scene.layout.Pane;
import polis.components.playingfield.cursor.cursortile.CursorTileView;


public class CursorManagerView extends Pane {

    private final CursorManager manager;
    private CursorTileManager model;

    public CursorManagerView(CursorManager manager){
        this.manager = manager;
        this.model = manager.getActiveManager();
        this.setTranslateY((double)(-model.getGridSize())/2* model.getCellSize());

        for(CursorTileView[] row : model.getTiles()){
            this.getChildren().addAll(row);
        }

        this.setOnMouseMoved(e -> model.hoover(e.getX(),e.getY()));

        this.setOnMousePressed(e -> model.setStartDrag(e.getX(), e.getY()));

        this.setOnMouseDragged(e -> model.drag(e.getX(), e.getY()));

        this.setOnMouseReleased(e -> manager.place());

    }

    public void setModel(CursorTileManager model) {
        this.model = model;
    }

}
