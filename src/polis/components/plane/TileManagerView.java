package polis.components.plane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;

public class TileManagerView extends Pane implements InvalidationListener {

    private TileManagerModel model;

    // initialize
    public TileManagerView(TileManagerModel tileManagerModel){
        this.model = tileManagerModel;
        for(TileModel[] row : model.getTiles()){
            for(TileModel cell : row){
                TileView tileView = new TileView(cell);
                this.getChildren().add(tileView);
            }
        }
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
