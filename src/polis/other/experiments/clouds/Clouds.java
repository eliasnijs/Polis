package polis.other.experiments.clouds;

import polis.datakeepers.FieldData;
import polis.other.Noise;

public class Clouds {

    private final float roughness;
    private final int multiply;

    private boolean[][] map;
    private final boolean[][] displayMap;

    private int step;

    public Clouds(){
        roughness = 0.4f;
        multiply = 5;
        displayMap = new boolean[FieldData.getGridSize()][FieldData.getGridSize()];
        init();
    }

    public void init(){
        Noise n = new Noise(null, roughness, FieldData.getGridSize()*multiply, FieldData.getGridSize()*(multiply-1));
        n.start();
        map = n.toBooleans();
        step = 0;
        advance();
    }

    public void advance(){
        if (step == FieldData.getGridSize()*(multiply-1)) {
            step = 0;
        }
        if (FieldData.getGridSize() + step - step >= 0)
            System.arraycopy(map, step + 1, displayMap, 0, FieldData.getGridSize() + step - step);
        step++;
    }

    public boolean[][] getDisplayMap() {
        return displayMap;
    }

}
