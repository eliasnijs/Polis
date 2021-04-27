package polis.uicomponents;

import polis.components.playingfield.buildings.BuildingField;
import polis.components.playingfield.buildings.tiles.Building;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

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

    public String setTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public void Update() {
        model.setTime(setTime());
        if (building == null) {
            generalStats();
        } else {
            tileStats();
        }
    }

    public void generalStats(){
        model.reset();
        HashSet<Building> buildings = buildingField.getBuildingTilesArray();
        for (Building b : buildings) {
            if (b.getName().equals("residence")) {
                model.setBewoners(b.getOccupancy(),b.getCapacity());
            } else if (b.getName().equals("industry") || b.getName().equals("commerce")) {
                model.setJobs(b.getOccupancy(),b.getCapacity());
            }
        }
    }

    public void tileStats(){
    }

    public void residence(){

    }

    public void commerce(){

    }

    public void industry(){

    }

}
