package polis.components.tiles;

import javafx.scene.image.Image;
import polis.components.tile.TileModel;

public class Grass extends TileModel {

    private final static String IMG_LOCATION = "images/grass.png";
    private Image image;

    public Grass(){
        this.setName("grass");
        image = new Image(IMG_LOCATION);
        this.setImage(image);
    }


}
