package polis.components.cursor.cursortile;


import polis.components.TileModel;

/**
 * Klasse die data van een cursor-tile bijhoudt.
 * **/
public class CursorTileModel extends TileModel {

    private final int strokeWidth;
    private String color;
    private String strokeColor;

    public CursorTileModel(int row, int column) {
        super(row, column);
        this.color = "#00000000";
        this.strokeColor = "#00000000";
        this.strokeWidth = 7;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        fireInvalidationEvent();
    }

    public void setStroke(String color) {
        this.strokeColor = color;
        fireInvalidationEvent();
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

}
