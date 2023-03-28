package polis.components.cursor.cursortile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.datakeepers.FieldData;

/**
 * Visuele representatie van een cursor-tile
 * **/
public class CursorTileView extends Polygon implements InvalidationListener {

    private final CursorTileModel model;

    public CursorTileView(CursorTileModel model) {
        super(0, 0, FieldData.getCellSize(), 0.5 * FieldData.getCellSize(), 0,
                FieldData.getCellSize(), -FieldData.getCellSize(), 0.5 * FieldData.getCellSize());
        this.model = model;
        model.addListener(this);
        invalidated(model);
        setTranslateX(model.gridToCoordinates()[0]);
        setTranslateY(model.gridToCoordinates()[1]);
        setMouseTransparent(true);
    }

    @Override
    public void invalidated(Observable o) {
        setFill(Color.web(model.getColor()));
        setStroke(Color.web(model.getStrokeColor()));
        setStrokeWidth(model.getStrokeWidth());
    }

}
