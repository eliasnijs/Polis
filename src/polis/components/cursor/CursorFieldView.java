package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import polis.components.Manager;
import polis.components.cursor.cursors.Cursor;
import polis.components.cursor.cursortile.CursorTileView;
import polis.datakeepers.FieldData;
import polis.helpers.HelperPoly;

public class CursorFieldView extends Pane implements InvalidationListener {

    private final CursorField model;
    private final Polygon poly;
    private Cursor cursor;

    public CursorFieldView(Manager m) {
        this.model = m.getCursorField();
        this.cursor = m.getActiveManager();
        model.addListener(this);

        this.poly = new HelperPoly();
        getChildren().add(poly);

        this.setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());

        this.setOnMouseMoved(e -> cursor.hoover(e.getX(), e.getY()));
        this.setOnMousePressed(e -> cursor.setStartDrag(e.getX(), e.getY()));
        this.setOnMouseDragged(e -> cursor.drag(e.getX(), e.getY()));
        this.setOnMouseReleased(e -> cursor.place());
    }

    public void setModel(Cursor manager) {
        this.cursor = manager;
    }

    public void addView(CursorTileView v) {
        getChildren().add(v);
    }

    public void deleteView() {
        getChildren().clear();
        getChildren().add(poly);
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