package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CursorPlainView extends Pane implements InvalidationListener {

    private final CursorPlainModel model;

    public CursorPlainView(CursorPlainModel tileManagerModel){
        this.model = tileManagerModel;
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
