package polis;

import javafx.scene.image.Image;

import java.util.Map;

public class ImageLoader {

    private static final Map<String,Image> images = Map.of(
            "grass",new Image("images/grass.png")
    );

    public Image getImage(String pathname){
        return images.get(pathname);
    }

}
