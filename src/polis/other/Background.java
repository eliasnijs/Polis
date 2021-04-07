package polis.other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background extends ImageView {

    private static final String IMG_LOCATION = "polis/other/background.png";

    public Background(){
        super(new Image(IMG_LOCATION));

        double scale = 3;

        setScaleX(scale);
        setScaleY(scale);
    }


}
