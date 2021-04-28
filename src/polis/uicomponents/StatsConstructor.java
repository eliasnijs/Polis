package polis.uicomponents;

import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileView;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;

import java.util.HashSet;

import static java.lang.Math.ceil;

public class StatsConstructor {

    private final BuildingField buildingField;
    private final Stats model;

    private Building building;

    public StatsConstructor(Stats model, BuildingField buildingField) {
        this.buildingField = buildingField;
        this.model = model;
        building = null;
        Update();
    }

    public boolean checkValidity(BuildingTileView view){
        if (view != null) {
            return view.getModel() instanceof Building;
        }  return false;
    }

    public void importBuilding(BuildingTileView view) {
        building = null;
        if (checkValidity(view)) {
            building = (Building) view.getModel();
        }
    }

    public void Update() {
        if (building == null) {
            model.setLocation("General");
            generalStats();
        } else {
            model.setLocation(building.getName().toUpperCase()
                    + "(" + building.getRow() + "x" + building.getColumn() + ")");
            tileStats();
        }
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void generalStats() {
        model.reset();
        HashSet<Building> buildings = buildingField.getBuildingTilesArray();
        for (Building b : buildings) {
            updateStats(b);
        }
    }

    public void tileStats() {
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

    public void residence(Building b) {
        model.addBewoners(b.getOccupancy(), b.getCapacity());
    }

    public void industry(Building b) {
        model.addJobs(b.getOccupancy(), b.getCapacity());
    }

    public void commerce(Building b) {
        Commerce shop = (Commerce) b;
        model.addKlanten(shop.getOccupancy(), shop.getCapacity());
        model.addGoederen(shop.getGoods(), shop.getGoodsCapacity());
        model.addJobs(shop.getJobs(), ceil(shop.getJobsCapacity()));
    }

}
