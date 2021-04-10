package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.components.Manager;
import polis.components.cursor.cursortile.CursorTileView;

public class CursorFieldView extends Pane implements InvalidationListener {

    private final CursorFieldModel model;
    private CursorManager manager;
    private final Polygon poly;

    public CursorFieldView(Manager m){
        this.model = m.getCursorField();
        this.manager = m.getActiveManager();
        model.addListener(this);

        int length = model.getCellSize() * model.getGridSize();
        poly = new Polygon(0, 0, length, 0.5 * length, 0, length, -length, 0.5 * length);
        poly.setFill(Color.TRANSPARENT);
        poly.setTranslateX(manager.getCellSize());
        this.getChildren().add(poly);

        this.setTranslateX((double)(model.getGridSize()-1) * model.getCellSize());

        this.setOnMouseMoved(e -> manager.hoover(e.getX(),e.getY()));
        this.setOnMousePressed(e -> manager.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> manager.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> manager.place());
    }

    public void setModel(CursorManager manager) {
        this.manager = manager;
    }

    public void addView(CursorTileView v) {
        this.getChildren().add(v);
    }

    public void deleteView() {
        this.getChildren().removeIf(o -> o != poly);
    }

    @Override
    public void invalidated(Observable observable) {
        CursorTileView t = model.getPendingView();
        if (model.getPendingMode() == 0) {
            addView(t);
        } else {
            deleteView();
        }
    }

}