package polis.components.tile;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;

public class TileView extends ImageView implements InvalidationListener {

    private TileModel model;

    public TileView(TileModel model) {
        this.model = model;
        model.addListener(this);
        this.setImage(model.getImage());
        this.setX(-0.5 * model.getImage().getWidth());
        this.setY(0.5 * model.getImage().getWidth() - model.getImage().getHeight());
        int x = model.getCellSize() * (model.getSize() - model.getRow()  + model.getColumn() );
        int y = model.getCellSize() * (model.getRow() + model.getColumn()) / 2;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }


    public TileModel getModel(){
        return model;
    }

    public void setModel(TileModel model){
        if(model != this.model){
            model.removeListener(this);
        }
        this.model = model;
        if(model != null){
            model.addListener(this);
        }
    }

    @Override
    public void invalidated(Observable o) {
        this.setImage(model.getImage());
    }

}
