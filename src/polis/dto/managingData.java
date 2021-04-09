package polis.dto;

import polis.components.buildings.BuildingFieldModel;
import polis.components.cursor.cursortile.CursorTileView;

public class managingData {

    private final CursorTileView[][] cursorField;
    private final BuildingFieldModel buildingField;

    public managingData(CursorTileView[][] cursorField, BuildingFieldModel buildingField) {
        this.cursorField = cursorField;
        this.buildingField = buildingField;
    }

    public CursorTileView[][] getCursorField() {
        return cursorField;
    }

    public BuildingFieldModel getBuildingField() {
        return buildingField;
    }

}
