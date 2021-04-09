package polis.components.cursor.cursortile;


import polis.components.TileModel;

public class CursorTileModel extends TileModel {

    private String status;
    private String color;
    private String strokeColor;
    private int strokeWidth;

    public CursorTileModel(int row, int column, int cellSize){
        super(row, column, cellSize);
        this.color = "#ffffff";
        this.strokeColor = "#ffffff";
        this.strokeWidth = 7;
        status = "AVAILABLE";
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
        fireInvalidationEvent();
    }

    public void setStroke(String color, int width){
        this.strokeColor = color;
        this.strokeWidth = width;
        fireInvalidationEvent();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

}
