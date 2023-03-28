package polis.datakeepers;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse om alle afbeeldingen in te laden.
 * Deze klasse bevat een methode getImage(String name) waaraan objecten een afbeelding kunnen opvragen.
 * Er werd gekozen om alle afbeeldingen 1x aan het begin in te laden en bij te houden
 * door de kleine schaal van het project,
 * dit zorgt voor een drastische verbetering bij het plaatsen van wegen op grote schaal.
 * **/
public class ImageLoader {

    private static final String[] tileNames = new String[]{
            "road-0", "road-1", "road-2", "road-3",
            "road-4", "road-5", "road-6", "road-7",
            "road-8", "road-9", "road-10", "road-11",
            "road-12", "road-13", "road-14", "road-15",
            "residence-0", "residence-1", "residence-2", "residence-3",
            "industry-0", "industry-1", "industry-2", "industry-3",
            "commerce-0", "commerce-1", "commerce-2", "commerce-3",
            "tree-0", "tree-1", "tree-2", "tree-3"
    };

    private static final String[] otherNames = new String[]{"background"};

    private final Map<String, Image> images;

    public ImageLoader() {
        images = new HashMap<>();
        for (String s : tileNames) {
            Image image = new Image("/polis/tiles/" + s + ".png");
            images.put(s, image);
        }
        for (String s : otherNames) {
            Image image = new Image("/polis/other/" + s + ".png");
            images.put(s, image);
        }
    }

    public Image getImage(String name) {
        return images.get(name);
    }

}
