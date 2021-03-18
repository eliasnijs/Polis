package polis.components;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import polis.components.tile.TileModel;

public class TileManagerView extends Pane implements InvalidationListener {

    private TileManagerModel model;

    // initialize
    public TileManagerView(){

    }

    // Handles the model
    public TileManagerModel getModel(){
        return model;
    }
    public void setModel(TileManagerModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    // Handles events
    @Override
    public void invalidated(Observable observable) {
        System.out.println("TileManager event to be executed");
    }
}
