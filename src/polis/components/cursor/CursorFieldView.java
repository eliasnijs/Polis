package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.components.Manager;
import polis.components.cursor.cursortile.CursorTileView;

public class CursorFieldView extends Pane implements InvalidationListener {

    private CursorFieldModel model;
    private CursorManager manager;

    public CursorFieldView(Manager m){
        this.model = m.getCursorField();
        this.manager = m.getActiveManager();

        int length = model.getCellSize() * model.getGridSize();
        Polygon poly = new Polygon(0, 0, length, 0.5 * length, 0, length, -length, 0.5 * length);
        this.getChildren().add(poly);

        model.addListener(this);

        this.setTranslateX((double)(model.getGridSize()-1) * model.getCellSize());

        this.setStyle("-fx-border-color: RED; -fx-border-width: 10px");

        this.setOnMouseMoved(e -> manager.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> manager.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> manager.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> { manager.place(); });
    }

    public void setModel(CursorManager manager) {
        this.manager = manager;
    }

    public void addView(CursorTileView v) {
        this.getChildren().add(v);
    }

    public void deleteView(CursorTileView v) {
        this.getChildren().remove(v);
    }

    @Override
    public void invalidated(Observable observable) {
        CursorTileView t = model.getPendingView();
        if (model.getPendingMode() == 0) {
            addView(t);
        } else {
            deleteView(t);
        }
    }

}