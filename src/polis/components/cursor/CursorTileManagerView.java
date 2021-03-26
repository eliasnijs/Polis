package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.plane.BuildingTileManagerModel;

public class CursorTileManagerView extends Pane implements InvalidationListener {

    private final CursorTileManagerModel model;

    public CursorTileManagerView(CursorTileManagerModel tileManagerModel){
        this.model = tileManagerModel;
        this.setTranslateY((double)(-BuildingTileManagerModel.getGridSize())/2* BuildingTileManagerModel.getCellSize());
        for(CursorTileModel[] row : model.getTiles()){
            for(CursorTileModel cell : row){
                CursorTileView cursorTileView = new CursorTileView(cell);
                this.getChildren().add(cursorTileView);
            }
        }
        this.setOnMouseMoved(e -> model.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> model.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> model.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> model.mousePressed());
    }

    // Event from model
    @Override
    public void invalidated(Observable observable) {
        System.out.println("CursorPlain event to be executed");
    }

}
