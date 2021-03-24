package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import polis.components.plane.tile.TileManagerModel;
import polis.components.plane.tile.TileModel;
import polis.components.plane.tile.TileView;

public class CursorPlainView extends Pane  implements InvalidationListener {

    private CursorPlainModel model;

    public CursorPlainView(CursorPlainModel tileManagerModel){
        this.model = tileManagerModel;
        for(CursorTileModel[] row : model.getTiles()){
            for(CursorTileModel cell : row){
                CursorTileView cursorTileView = new CursorTileView(cell);
                this.getChildren().add(cursorTileView);
            }
        }
    }

    // Handles the model
    public CursorPlainModel getModel(){
        return model;
    }
    public void setModel(CursorPlainModel model){
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
        System.out.println("CursorPlain event to be executed");
    }

}
