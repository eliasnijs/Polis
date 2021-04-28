package polis.components.playingfield.buildings.tiles.buildings;

import polis.helpers.PropertyLoader;

public enum BuildingProperties {

    RESIDENCE("residential"),
    INDUSTRY("industrial"),
    COMMERCE("commercial");

    private final int minimumCapacity;
    private final int initialCapacity;
    private final int[] levelChanges;

    BuildingProperties(String name) {
        PropertyLoader p = new PropertyLoader();
        minimumCapacity = (int) Double.parseDouble(PropertyLoader.getProperty("engine", name + ".capacity.minimal"));
        initialCapacity = (int) Double.parseDouble(PropertyLoader.getProperty("engine", name + ".capacity.initial"));
        levelChanges = new int[]{
                -1,
                (int) Double.parseDouble(PropertyLoader.getProperty("levels", name + ".level1to2")),
                (int) Double.parseDouble(PropertyLoader.getProperty("levels", name + ".level2to1")),
                (int) Double.parseDouble(PropertyLoader.getProperty("levels", name + ".level2to3")),
                (int) Double.parseDouble(PropertyLoader.getProperty("levels", name + ".level3to2")),
                Integer.MAX_VALUE};
    }

    public int getMinimumCapacity() {
        return minimumCapacity;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public int getLevelChanges(int index) {
        return levelChanges[index];
    }

}
