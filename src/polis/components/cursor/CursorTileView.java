package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class CursorTileView extends Polygon implements InvalidationListener {

    private final CursorTileModel model;

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

    // Update the image for the view
    @Override
    public void invalidated(Observable o) {
        this.setFill(Color.web(model.getColor()));
    }

}
