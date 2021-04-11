package polis.helpers;

import polis.datakeepers.FieldData;

public class GridCoordsConverter {

    public static int[] gridToCoords(int[] c){
        int x = FieldData.getCellSize() * (1 - c[0]  + c[1]);
        int y = FieldData.getCellSize() * (c[0] + c[1]) / 2;
        return new int[]{x,y};
    }

    public static int[] coordsToGrid(double x, double y){
        int column = (int) ((x/FieldData.getCellSize())/2 + y/FieldData.getCellSize() - 0.5);
        int row = (int) (-(x/FieldData.getCellSize())/2 + y/FieldData.getCellSize() + 0.5);
        return new int[]{row,column};
    }

}
