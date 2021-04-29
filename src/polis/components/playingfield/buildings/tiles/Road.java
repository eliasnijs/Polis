package polis.components.playingfield.buildings.tiles;

/**
 * Straat klasse. Deze klasse is buiten het bijhouden van de data voor een straat ook verantwoordelijk
 * voor het bepalen van zijn richting op basis van een gekregen adjacentie array.
 * De adjacentie array bestaat in volgende volgorde: boven, rechts, onder, links
 * **/
public class Road extends BuildingTileModel {

    private boolean[] neighbours;

    public Road(int row, int column, boolean[] neighbours, boolean isDestructible) {
        super(row, column, "road", 1);
        this.neighbours = neighbours;
        setDestructible(isDestructible);
        Update();
    }

    public void setNeighbours(boolean[] adj) {
        neighbours = adj;
        Update();
    }

    public void Update() {
        int level = calculateLevel();
        setImage(getName() + "-" + level);
    }

    private int calculateLevel() {
        int level = 0;
        int[] codes = new int[]{1, 2, 4, 8};
        for (int i = 0; i < 4; i++) {
            if (neighbours[i]) {
                level += codes[i];
            }
        }
        return level;
    }

}
