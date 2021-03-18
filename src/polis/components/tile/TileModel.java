package polis.components.tile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TileModel implements Observable {
    
    private String name;
    private Image image;
    private int row;
    private int column;
    private int size;
    private int cellSize;
    private final List<InvalidationListener> listenerList = new ArrayList<>();

    // Handle name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(!name.equals(this.name)){
            this.name = name;
            fireInvalidationEvent();
        }
    }
    
    // Handle image
    public Image getImage() {
        return image; 
    }
    public void setImage(Image image) {
        if(image != this.image){
            this.image = image;
            fireInvalidationEvent();
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public int getCellSize() {
        return cellSize;
    }

    // Handle external communication
    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }
    private void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }
    

}
