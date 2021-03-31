package polis.components.playingfield.cursor;


import polis.components.playingfield.TileModel;

public class CursorTileModel extends TileModel {

    private String status;
    private String color;

    public CursorTileModel(String color, int row, int column, int cellSize){
        super(row, column, cellSize);
        this.color = color;
        status = "AVAILABLE";
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
        fireInvalidationEvent();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
