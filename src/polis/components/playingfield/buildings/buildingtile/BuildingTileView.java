package polis.components.playingfield.buildings.buildingtile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;

public class BuildingTileView extends ImageView implements InvalidationListener {

    private BuildingTileModel model;

    public BuildingTileView(BuildingTileModel model) {
        this.model = model;
        model.addListener(this);

        this.setImage(model.getImage());
        this.setX(-0.5 * model.getImage().getWidth());
        this.setY(0.5 * model.getImage().getWidth() - model.getImage().getHeight());

        int[] coords = model.gridToCoordinates();
        this.setTranslateX(coords[0]);
        this.setTranslateY(coords[1]);
    }

    public BuildingTileModel getModel(){
        return model;
    }

    public void setModel(BuildingTileModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    @Override
    public void invalidated(Observable o) {
        this.setImage(model.getImage());
    }

}
