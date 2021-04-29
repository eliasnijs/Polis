package polis.other;

import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Random;

/**
 * Genereert noise.
 * **/

public class Noise {

    private final float roughness;
    private final float[][] grid;
    private final Random rand;


    public Noise(Random rand, float roughness, int width, int height) {
        this.roughness = roughness / width;
        grid = new float[width][height];
        this.rand = (rand == null) ? new Random() : rand;
        start();
    }

    private void start() {
        int xHeight = grid.length - 1;
        int yHeight = grid[0].length - 1;
        grid[0][0] = rand.nextFloat() - 0.5f;
        grid[0][yHeight] = rand.nextFloat() - 0.5f;
        grid[xHeight][0] = rand.nextFloat() - 0.5f;
        grid[xHeight][yHeight] = rand.nextFloat() - 0.5f;
        generate(0, 0, xHeight, yHeight);
    }


    private float roughen(float v, int l, int h) {
        return v + roughness * (float) (rand.nextGaussian() * (h - l));
    }


    private void generate(int xLength, int yLength, int xHeight, int yHeight) {
        int xMid = (xLength + xHeight) / 2;
        int yMid = (yLength + yHeight) / 2;

        if ((xLength == xMid) && (yLength == yMid)) {
            return;
        }

        grid[xMid][yLength] = 0.5f * (grid[xLength][yLength] + grid[xHeight][yLength]);
        grid[xMid][yHeight] = 0.5f * (grid[xLength][yHeight] + grid[xHeight][yHeight]);
        grid[xLength][yMid] = 0.5f * (grid[xLength][yLength] + grid[xLength][yHeight]);
        grid[xHeight][yMid] = 0.5f * (grid[xHeight][yLength] + grid[xHeight][yHeight]);

        float v = roughen(0.5f * (grid[xMid][yLength] + grid[xMid][yHeight]), xLength + yLength, yHeight + xHeight);
        grid[xMid][yMid] = v;
        grid[xMid][yLength] = roughen(grid[xMid][yLength], xLength, xHeight);
        grid[xMid][yHeight] = roughen(grid[xMid][yHeight], xLength, xHeight);
        grid[xLength][yMid] = roughen(grid[xLength][yMid], yLength, yHeight);
        grid[xHeight][yMid] = roughen(grid[xHeight][yMid], yLength, yHeight);

        generate(xLength, yLength, xMid, yMid);
        generate(xMid, yLength, xHeight, yMid);
        generate(xLength, yMid, xMid, yHeight);
        generate(xMid, yMid, xHeight, yHeight);
    }

    private boolean[][] toBooleans() {
        int w = grid.length;
        int h = grid[0].length;
        boolean[][] ret = new boolean[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                ret[i][j] = grid[i][j] < 0;
            }
        }
        return ret;
    }

    public ArrayList<int[]> getLocations() {
        ArrayList<int[]> locations = new ArrayList<>();
        boolean[][] noiseMap = toBooleans();
        for (int i = 0; i < FieldData.getGridSize(); i++) {
            for (int j = 0; j < FieldData.getGridSize(); j++) {
                if (noiseMap[i][j]) {
                    locations.add(new int[]{i, j});
                }
            }
        }
        return locations;
    }

}