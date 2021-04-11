package polis.other;

import javafx.scene.image.ImageView;
import polis.datakeepers.FieldData;

public class Background extends ImageView {

    public Background(){
        super(FieldData.getImageLoader().getImage("background"));
        double scale = 3;
        setScaleX(scale);
        setScaleY(scale);
    }

}
