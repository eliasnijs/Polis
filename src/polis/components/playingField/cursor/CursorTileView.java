package polis.components.playingField.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CursorTileView extends Polygon implements InvalidationListener {

    private CursorTileModel model;

    public CursorTileView(CursorTileModel model){
        super(0, 0,
                model.getCellSize() * model.getSize(), 0.5 * model.getCellSize() * model.getSize(),
                0, model.getCellSize() * model.getSize(),
                -model.getCellSize() * model.getSize(), 0.5 * model.getCellSize() * model.getSize());
        this.model = model;
        model.addListener(this);
        int[] coords = model.gridToCoordinates();
        this.setTranslateX(coords[0]);
        this.setTranslateY(coords[1]);
        this.setFill(Color.web(model.getColor()));
    }

    // Getters
    public CursorTileModel getModel() {
        return model;
    }

    // Setters
    public void setModel(CursorTileModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    // Update the image for the view
    @Override
    public void invalidated(Observable o) {
        this.setFill(Color.web(model.getColor()));
    }

}
