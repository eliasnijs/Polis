package polis.components.playingField.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;

public class BuildingTileView extends ImageView implements InvalidationListener {

    private BuildingTileModel model;

    public BuildingTileView(BuildingTileModel model) {
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

    // Getters
    public BuildingTileModel getModel(){
        return model;
    }

    // Setters
    public void setModel(BuildingTileModel model){
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
