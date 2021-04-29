package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import polis.components.Manager;
import polis.components.cursor.cursors.Cursor;
import polis.datakeepers.FieldData;
import polis.helpers.HelperPoly;

import java.util.Map;

public class CursorFieldView extends Pane implements InvalidationListener {

    private final CursorField model;
    private final Polygon poly;
    private Cursor cursor;

    private final Map<Integer, Runnable> events = Map.of(
            0, this::addView,
            1, this::deleteView
    );

    public CursorFieldView(Manager m) {
        model = m.getCursorField();
        cursor = m.getActiveCursor();
        model.addListener(this);
        poly = new HelperPoly();
        getChildren().add(poly);
        setTranslateX((double) (FieldData.getGridSize() - 1) * FieldData.getCellSize());
        setOnMouseMoved(e -> cursor.hoover(e.getX(), e.getY()));
        setOnMousePressed(e -> cursor.setStartDrag(e.getX(), e.getY()));
        setOnMouseDragged(e -> cursor.drag(e.getX(), e.getY()));
        setOnMouseReleased(e -> cursor.place());
    }

    public void setModel(Cursor manager) {
        this.cursor = manager;
    }

    private void addView() {
        getChildren().add(model.getPendingView());
    }

    private void deleteView() {
        getChildren().clear();
        getChildren().add(poly);
    }

    @Override
    public void invalidated(Observable observable) {
        events.get(model.getPendingMode()).run();
    }

}