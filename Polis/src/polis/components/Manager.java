package polis.components;

import polis.components.cursor.CursorField;
import polis.components.cursor.CursorFieldView;
import polis.components.cursor.cursors.Cursor;
import polis.components.cursor.cursors.CursorBuildings;
import polis.components.cursor.cursors.CursorRoads;
import polis.components.cursor.cursors.CursorSelect;
import polis.components.playingfield.PlayingField;
import polis.components.playingfield.PlayingFieldView;
import polis.components.playingfield.actors.FrameLine;
import polis.components.playingfield.buildings.BuildingField;
import polis.uicomponents.statistics.Stats;
import polis.uicomponents.statistics.StatsController;

import java.util.ArrayList;


/**
 * De main manager van het programma. Deze moet de onder-managers aanmaken en de relaties ertussen faciliteren.
 * Deze klasse heeft daarnaast ook een belangrijke rol in het selecteren van de juiste cursor manager.
 * **/
public class Manager {

    private final PlayingField playingField;
    private final CursorField cursorField;
    private final ArrayList<Cursor> cursors;
    private final CursorFieldView cursorView;
    private final PlayingFieldView playingFieldView;
    private final Stats statsModel;
    private final StatsController statsConstructor;
    private final FrameLine frameLine;
    private Cursor activeCursor;

    public Manager() {
        ArrayList<int[]> selected = new ArrayList<>();
        playingField = new PlayingField(this);
        cursorField = new CursorField();
        cursorView = new CursorFieldView(this);
        playingFieldView = new PlayingFieldView(playingField.getBuildingField(), playingField.getActorField());
        playingField.setView(playingFieldView);
        cursors = new ArrayList<>();
        cursors.add(new CursorBuildings(playingField.getBuildingField(), selected, cursorField));
        cursors.add(new CursorRoads(playingField.getBuildingField(), selected, cursorField));
        cursors.add(new CursorSelect(playingField.getBuildingField(), selected, cursorField, this));
        activeCursor = cursors.get(2);
        cursorView.setModel(activeCursor);
        activeCursor.setTool("select");
        statsModel = new Stats();
        statsConstructor = new StatsController(statsModel, playingField.getBuildingField());
        frameLine = new FrameLine(playingField.getActorField().getSimulator(), statsConstructor);
    }

    public void setActiveManager(int i, String tool) {
        activeCursor.clearSelectedTiles();
        activeCursor = cursors.get(i);
        activeCursor.setTool(tool);
        cursorView.setModel(activeCursor);
    }

    public BuildingField getBuildingField() {
        return playingField.getBuildingField();
    }

    public CursorField getCursorField() {
        return cursorField;
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

    public Stats getStatsModel() {
        return statsModel;
    }

    public StatsController getStatsConstructor() {
        return statsConstructor;
    }

    public FrameLine getFrameLine() {
        return frameLine;
    }

    public Cursor getActiveCursor() {
        return activeCursor;
    }

}
