package polis.helpers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.datakeepers.FieldData;

public class HelperPoly extends Polygon {

    private static final int length = FieldData.getCellSize() * FieldData.getGridSize();

    public HelperPoly(){
        super(0, 0, length, 0.5 * length, 0, length, -length, 0.5 * length);
        setFill(Color.TRANSPARENT);
        setTranslateX(FieldData.getCellSize());
    }

}
