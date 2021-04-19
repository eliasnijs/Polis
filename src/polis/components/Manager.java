package polis.components;

import polis.components.cursor.CursorField;
import polis.components.cursor.CursorFieldView;
import polis.components.cursor.cursors.Cursor;
import polis.components.cursor.cursors.CursorBuildings;
import polis.components.cursor.cursors.CursorRoads;
import polis.components.cursor.cursors.CursorSelect;
import polis.components.playingfield.PlayingField;
import polis.components.playingfield.PlayingFieldView;
import polis.components.playingfield.buildings.BuildingField;

import java.util.ArrayList;

public class Manager {

    private final PlayingField playingField;
    private final CursorField cursorField;

    private Cursor activeManager;
    private final ArrayList<Cursor> cursors;

    private final CursorFieldView cursorView;
    private final PlayingFieldView playingFieldView;

    public Manager() {
        ArrayList<int[]> selected = new ArrayList<>();
        this.playingField = new PlayingField();
        this.cursorField = new CursorField();

        this.cursorView = new CursorFieldView(this);
        this.playingFieldView = new PlayingFieldView(playingField.getBuildingField(), playingField.getActorField());

        playingField.setView(playingFieldView);

        cursors = new ArrayList<>();
        cursors.add(new CursorBuildings(playingField.getBuildingField(), selected, cursorField));
        cursors.add(new CursorRoads(playingField.getBuildingField(), selected, cursorField));
        cursors.add(new CursorSelect(playingField.getBuildingField(), selected, cursorField, this));

        activeManager = cursors.get(2);
        cursorView.setModel(activeManager);
        activeManager.setTool("select");
    }

    public Cursor getActiveManager() {
        return activeManager;
    }

    public BuildingField getBuildingField() {
        return playingField.getBuildingField();
    }

    public CursorField getCursorField() {
        return cursorField;
    }

    public void setActiveManager(int i, String tool) {
        activeManager.clearSelectedTiles();
        activeManager = cursors.get(i);
        activeManager.setTool(tool);
        cursorView.setModel(activeManager);
    }

    public Cursor getManager(int i) {
        return cursors.get(i);
    }

    public CursorFieldView getCursorView() {
        return cursorView;
    }

    public PlayingFieldView getPlayingFieldView() {
        return playingFieldView;
    }

    public PlayingField getPlayingField() {
        return playingField;
    }
}
