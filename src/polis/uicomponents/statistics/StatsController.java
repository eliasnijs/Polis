package polis.uicomponents.statistics;

import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;

import java.util.HashSet;
import java.util.Map;

/**
 *  De controller voor de statistieken.
 *  Verantwoordelijk voor het updaten van de data binnenin de statistieken.
 * **/

public class StatsController {

    private final BuildingField buildingField;
    private final Stats model;

    private Building building;

    private final Map<Boolean,Runnable> map = Map.of(
            true, this::generalStats,
            false, this::tileStats
    );


    public StatsController(Stats model, BuildingField buildingField) {
        this.buildingField = buildingField;
        this.model = model;
        building = null;
        Update();
    }

    public void Update() {
        map.get(building == null).run();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        model.reset();
        this.building = building;
        Update();
    }

    private boolean checkValidity(BuildingTileView view){
        if (view != null) {
            return view.getModel() instanceof Building;
        }  return false;
    }

    public void importBuilding(BuildingTileView view) {
        building = null;
        if (checkValidity(view)) {
            setBuilding((Building) view.getModel());
        }
    }

    private void generalStats() {
        model.setLocation("General");
        model.reset();
        HashSet<Building> buildings = getBuildingTilesArray();
        for (Building b : buildings) {
            updateStats(b);
        }
    }

    public HashSet<Building> getBuildingTilesArray(){
        HashSet<Building> tilesArray = new HashSet<>();
        for (BuildingTileView[] row : buildingField.getTiles()) {
            for (BuildingTileView tile : row) {
                if (tile != null) {
                    if (tile.getModel() instanceof Building) {
                        tilesArray.add((Building) tile.getModel());
                    }
                }
            }
        }
        return tilesArray;
    }

    private void tileStats() {
        model.setLocation(building.getName().toUpperCase()
                + "(" + building.getRow() + "x" + building.getColumn() + ")");
        model.reset();
        updateStats(building);
    }

    private void updateStats(Building building) {
        switch (building.getName()) {
            case "residence":
                residence(building);
                break;
            case "industry":
                industry(building);
                break;
            case "commerce":
                commerce(building);
                break;
        }
    }

    private void residence(Building b) {
        model.addBewoners(b.getOccupancy(), b.getCapacity());
    }

    private void industry(Building b) {
        model.addJobs(b.getOccupancy(), b.getCapacity());
    }

    private void commerce(Building b) {
        Commerce shop = (Commerce) b;
        model.addKlanten(shop.getOccupancy(), shop.getCapacity());
        model.addGoederen(shop.getGoods(), shop.getGoodsCapacity());
        model.addJobs(shop.getJobs(), Math.ceil(shop.getJobsCapacity()));
    }

}
