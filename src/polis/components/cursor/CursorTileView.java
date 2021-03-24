package polis.components.cursor;

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
                -model.getCellSize() * model.getSize(), 0.5 * model.getCellSize() * model.getSize()
        );
        this.model = model;
        model.addListener(this);
        // Adjusts the view to an isometric grid based on it's row and column
        int x = model.getCellSize() * (model.getSize() - model.getRow()  + model.getColumn() );
        int y = model.getCellSize() * (model.getRow() + model.getColumn()) / 2;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setFill(Color.web(model.getColor()));
    }

    // return the model
    public CursorTileModel getModel(){
        return model;
    }

    // Change the corresponding model
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
