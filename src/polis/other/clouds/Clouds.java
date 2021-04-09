package polis.other.clouds;

import javafx.beans.binding.StringExpression;
import polis.other.Noise;

public class Clouds {

    private final float roughness;
    private final int gridSize;
    private final int multiply;

    private boolean[][] map;
    private final boolean[][] displayMap;

    private int step;

    public Clouds(){
        gridSize = 32;
        roughness = 0.4f;
        multiply = 5;
        displayMap = new boolean[gridSize][gridSize];
        init();
    }

    public void init(){
        Noise n = new Noise(null, roughness, gridSize*multiply, gridSize*(multiply-1));
        n.start();
        map = n.toBooleans();
        step = 0;
        advance();
    }

    public void advance(){
        if (step == gridSize*(multiply-1)) {
            step = 0;
        }
        if (gridSize + step - step >= 0)
            System.arraycopy(map, step + 1, displayMap, 0, gridSize + step - step);
        step++;
    }

    public boolean[][] getDisplayMap() {
        return displayMap;
    }

}
