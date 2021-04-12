package polis.components.cursor.cursortile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.datakeepers.FieldData;

public class CursorTileView extends Polygon implements InvalidationListener {

    private final CursorTileModel model;

    public CursorTileView(CursorTileModel model) {
        super(0, 0, FieldData.getCellSize(), 0.5 * FieldData.getCellSize(), 0,
                FieldData.getCellSize(), -FieldData.getCellSize(), 0.5 * FieldData.getCellSize());
        this.model = model;
        model.addListener(this);
        int[] c = model.gridToCoordinates();
        this.setTranslateX(c[0]);
        this.setTranslateY(c[1]);
        setCursorStyle();
        this.setMouseTransparent(true);
    }

    public void setCursorStyle() {
        setFill(Color.web(model.getColor()));
        setStroke(Color.web(model.getStrokeColor()));
        setStrokeWidth(model.getStrokeWidth());
    }

    @Override
    public void invalidated(Observable o) {
        setCursorStyle();
    }

}
