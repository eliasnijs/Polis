package polis.other;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static final String tiles = "/polis/tiles/";
    private static final String[] tilenames = new String[]{
            "road-0", "road-1", "road-2","road-3",
            "road-4", "road-5", "road-6", "road-7",
            "road-8", "road-9", "road-10", "road-11",
            "road-12", "road-13", "road-14", "road-15",

            "residence-0", "residence-1", "residence-2", "residence-3",
            "industry-0", "industry-1", "industry-2", "industry-3",
            "commerce-0", "commerce-1", "commerce-2", "commerce-3",

            "tree-0", "tree-1", "tree-2", "tree-3"
    };

    private static final String other = "/polis/other/";
    private static final String[] othernames = new String[]{
            "background"
    };

    private final Map<String,Image> images;

    public ImageLoader() {
        images = new HashMap<>();
        for (String s : tilenames) {
            Image image = new Image(tiles+s+".png");
            images.put(s,image);
        }
        for (String s : othernames) {
            Image image = new Image(other+s+".png");
            images.put(s,image);
        }
    }

    public Image getImage(String name){
        return images.get(name);
    }


}
