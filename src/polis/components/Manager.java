package polis.components;

import polis.components.playingfield.actors.ActorManager;
import polis.components.cursor.CursorFieldModel;
import polis.components.cursor.CursorFieldView;
import polis.components.cursor.cursors.Cursor;
import polis.components.cursor.cursors.CursorBuildings;
import polis.components.cursor.cursors.CursorSelect;
import polis.components.cursor.cursors.CursorRoads;
import polis.components.playingfield.buildings.BuildingFieldModel;

import java.util.ArrayList;

public class Manager {

    private final CursorFieldModel cursorField;
    private final BuildingFieldModel buildingField;
    private final ArrayList<Cursor> managers;
    private Cursor activeManager;
    private CursorFieldView view;

    public Manager() {
        ArrayList<int[]> selected = new ArrayList<>();
        this.buildingField = new BuildingFieldModel();
        this.cursorField = new CursorFieldModel();
        managers = new ArrayList<>();
        managers.add(new CursorBuildings(buildingField, selected, cursorField));
        managers.add(new CursorRoads(buildingField, selected, cursorField));
        managers.add(new CursorSelect(buildingField, selected, cursorField, this));
        activeManager = managers.get(2);
    }

    public Cursor getActiveManager() {
        return activeManager;
    }

    public BuildingFieldModel getBuildingField() {
        return buildingField;
    }

    public CursorFieldModel getCursorField() {
        return cursorField;
    }

    public void setView(CursorFieldView view){
        this.view = view;
    }

    public void setActiveManager(int i, String tool) {
        activeManager.clearSelectedTiles();
        this.activeManager = managers.get(i);
        activeManager.setTool(tool);
        view.setModel(activeManager);
    }

    public Cursor getManager(int i){
        return managers.get(i);
    }

}
