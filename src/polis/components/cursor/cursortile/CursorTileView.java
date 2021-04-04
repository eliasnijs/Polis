package polis.components.cursor.cursortile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CursorTileView extends Polygon implements InvalidationListener {

    private final CursorTileModel model;

    public CursorTileView(CursorTileModel model){
        super(0, 0, model.getCellSize(), 0.5 * model.getCellSize(), 0,
                model.getCellSize(), -model.getCellSize(), 0.5 * model.getCellSize());
        this.model = model;
        model.addListener(this);
        int[] c = model.gridToCoordinates();
        this.setTranslateX(c[0]);
        this.setTranslateY(c[1]);
        setCursorStyle();
    }

    public CursorTileModel getModel() {
        return model;
    }

    public void setCursorStyle(){
        setFill(Color.web(model.getColor()));
        setStroke(Color.web(model.getStrokeColor()));
        setStrokeWidth(model.getStrokeWidth());
    }

    @Override
    public void invalidated(Observable o) {
        setCursorStyle();
    }

}
