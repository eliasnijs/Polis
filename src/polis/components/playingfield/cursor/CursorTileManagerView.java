package polis.components.playingfield.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;


public class CursorTileManagerView extends Pane {

    private final CursorTileManagerModel model;

    public CursorTileManagerView(CursorTileManagerModel tileManagerModel){
        this.model = tileManagerModel;
        this.setTranslateY((double)(-model.getGridSize())/2* model.getCellSize());

        for(CursorTileView[] row : model.getTiles()){
            this.getChildren().addAll(row);
        }

        this.setOnMouseMoved(e -> model.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> model.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> model.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> model.mousePressed());

    }

}
