package polis.components.playingfield.buildings.tiles;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Klasse verantwoordelijk voor de visuele representatie van de een gebouw, straat of decoratie.
 * **/
public class BuildingTileView extends ImageView implements InvalidationListener {

    private BuildingTileModel model;

    public BuildingTileView(BuildingTileModel m) {
        setModel(m);
        loadImage();
    }

    public BuildingTileModel getModel() {
        return model;
    }

    private void setModel(BuildingTileModel model) {
        if (model != this.model) {
            model.removeListener(this);
        }
        this.model = model;
        if (model != null) {
            model.addListener(this);
            int[] coords = model.gridToCoordinates();
            this.setTranslateX(coords[0]);
            this.setTranslateY(coords[1]);
        }
    }

    private void loadImage() {
        Image image = model.getImage();
        setImage(image);
        setX(-0.5 * image.getWidth());
        setY(0.5 * image.getWidth() - image.getHeight());
    }

    @Override
    public void invalidated(Observable o) {
        loadImage();
    }

}
