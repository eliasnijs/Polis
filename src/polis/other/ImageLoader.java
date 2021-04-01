package polis.other;

import javafx.scene.image.Image;

import java.util.Map;

public class ImageLoader {

    private static final Map<String,Image> images = Map.of(
            "grass", new Image("polis/tiles/grass.png"),

            "road-0", new Image("polis/tiles/road-0.png"),

            "residence-0", new Image("polis/tiles/residence-0.png"),
            "residence-1", new Image("polis/tiles/residence-1.png"),
            "residence-2", new Image("polis/tiles/residence-2.png"),
            "residence-3", new Image("polis/tiles/residence-3.png")
    );

    public Image getImage(String name){
        return images.get(name);
    }

}
