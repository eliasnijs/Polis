package polis.components.tile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;

public class TileView extends ImageView implements InvalidationListener {

    private TileModel model;

    // Initialize a new tile-view
    public TileView(TileModel model) {
        // Setup the corresponding model for the view
        this.model = model;
        model.addListener(this);
        // Adjusts the view to an isometric grid based on it's row and column
        this.setImage(model.getImage());
        this.setX(-0.5 * model.getImage().getWidth());
        this.setY(0.5 * model.getImage().getWidth() - model.getImage().getHeight());
        int x = model.getCellSize() * (model.getSize() - model.getRow()  + model.getColumn() );
        int y = model.getCellSize() * (model.getRow() + model.getColumn()) / 2;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    // return the model
    public TileModel getModel(){
        return model;
    }

    // Change the corresponding model
    public void setModel(TileModel model){
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
        this.setImage(model.getImage());
    }

}
