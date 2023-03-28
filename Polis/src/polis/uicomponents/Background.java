package polis.uicomponents;

import javafx.scene.image.ImageView;
import polis.datakeepers.FieldData;

/**
 * De visuele achtergrond component.
 * **/

public class Background extends ImageView {

    public Background(double scale) {
        super(FieldData.getImageLoader().getImage("background"));
        setScaleX(scale);
        setScaleY(scale);
    }

}
