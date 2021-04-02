package polis.other;

import javafx.scene.image.Image;

import java.util.Map;

public class ImageLoader {

    private static final Map<String,Image> images = Map.ofEntries(

            Map.entry("road-0", new Image("polis/tiles/road-0.png")),

            Map.entry("residence-0", new Image("polis/tiles/residence-0.png")),
            Map.entry("residence-1", new Image("polis/tiles/residence-1.png")),
            Map.entry("residence-2", new Image("polis/tiles/residence-2.png")),
            Map.entry("residence-3", new Image("polis/tiles/residence-3.png")),

            Map.entry("factory-0", new Image("polis/tiles/industry-0.png")),
            Map.entry("factory-1", new Image("polis/tiles/industry-1.png")),
            Map.entry("factory-2", new Image("polis/tiles/industry-2.png")),
            Map.entry("factory-3", new Image("polis/tiles/industry-3.png")),

            Map.entry("commerce-0", new Image("polis/tiles/commerce-0.png")),
            Map.entry("commerce-1", new Image("polis/tiles/commerce-1.png")),
            Map.entry("commerce-2", new Image("polis/tiles/commerce-2.png")),
            Map.entry("commerce-3", new Image("polis/tiles/commerce-3.png"))

    );


    public Image getImage(String name){
        return images.get(name);
    }

}
