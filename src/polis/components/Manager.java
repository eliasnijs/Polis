package polis.components;

import polis.components.cursor.CursorField;
import polis.components.cursor.CursorFieldView;
import polis.components.cursor.cursors.Cursor;
import polis.components.cursor.cursors.CursorBuildings;
import polis.components.cursor.cursors.CursorRoads;
import polis.components.cursor.cursors.CursorSelect;
import polis.components.playingfield.buildings.BuildingField;

import java.util.ArrayList;

public class Manager {

    private final CursorField cursorField;
    private final BuildingField buildingField;
    private final ArrayList<Cursor> cursors;
    private Cursor activeManager;
    private CursorFieldView view;

    public Manager() {
        ArrayList<int[]> selected = new ArrayList<>();
        this.buildingField = new BuildingField();
        this.cursorField = new CursorField();
        cursors = new ArrayList<>();
        cursors.add(new CursorBuildings(buildingField, selected, cursorField));
        cursors.add(new CursorRoads(buildingField, selected, cursorField));
        cursors.add(new CursorSelect(buildingField, selected, cursorField, this));
        activeManager = cursors.get(2);
        activeManager.setTool("select");
    }

    public Cursor getActiveManager() {
        return activeManager;
    }

    public BuildingField getBuildingField() {
        return buildingField;
    }

    public CursorField getCursorField() {
        return cursorField;
    }

    public void setView(CursorFieldView view) {
        this.view = view;
    }

    public void setActiveManager(int i, String tool) {
        activeManager.clearSelectedTiles();
        activeManager = cursors.get(i);
        activeManager.setTool(tool);
        view.setModel(activeManager);
    }

    public Cursor getManager(int i) {
        return cursors.get(i);
    }

}
