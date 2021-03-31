package polis.components.playingfield.cursor;

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

        int[] coords = model.gridToCoordinates();
        this.setTranslateX(coords[0]);
        this.setTranslateY(coords[1]);

        setColor();
    }

    public CursorTileModel getModel() {
        return model;
    }

    public void setColor(){
        this.setFill(Color.web(model.getColor()));
    }

    @Override
    public void invalidated(Observable o) {
        setColor();
    }

}
