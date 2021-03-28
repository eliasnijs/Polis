package polis.components.playingField.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;


public class CursorTileManagerView extends Pane implements InvalidationListener {

    private CursorTileManagerModel model;

    public CursorTileManagerView(CursorTileManagerModel tileManagerModel){
        this.model = tileManagerModel;
        this.setTranslateY((double)(-CursorTileManagerModel.getGridSize())/2* CursorTileManagerModel.getCellSize());

        for(CursorTileView[] row : model.getTiles()){
            this.getChildren().addAll(row);
        }

        this.setOnMouseMoved(e -> model.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> model.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> model.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> model.mousePressed());
    }

    // Getters
    public CursorTileManagerModel getModel() {
        return model;
    }

    // Setters
    public void setModel(CursorTileManagerModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    // Event from model
    @Override
    public void invalidated(Observable observable) {
        System.out.println("CursorPlain executed");
    }

}
