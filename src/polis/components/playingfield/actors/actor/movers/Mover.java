package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.datakeepers.FieldData;

import java.util.ArrayList;
import java.util.Random;

public abstract class Mover extends Actor {

    private final static int[] DC = { -1, 0, 1, 0 };
    private final static int[] DR = { 0, -1, 0, 1};
    private final static int[][] diffs = new int[][] {
            { 0,1,3,2 }, {0,3,1,2}, {1,3,0,2}, {1,0,3,2}, {3,0,1,2}, {3,1,0,2}};

    private final MoverManager moverManager;
    private final Random RG;
    private int direction;

    // r = 0; f = 1; c = 2
    private final String destination;

    public Mover(int row, int column, MoverManager moverManager, String destination, String color) {
        super(row, column);
        this.moverManager = moverManager;
        this.destination = destination;
        setColor(color);
        direction = 0;
        RG = new Random();
    }

    @Override
    public int[] getCoords() {
        int[] c =  super.getCoords();
        // Lichte aanpassing aan de locatie zodat de autos aan de juiste kant van de weg rijden
        c[0] = c[0] + FieldData.getCellSize()/16*3 - DC[direction] * FieldData.getCellSize()/2;
        c[1] = c[1] + FieldData.getCellSize()/16*6 - DR[direction] * FieldData.getCellSize()/4;
        return c;
    }

    public void act(){
        checkSurrounding();
        changeDirection();
        move();
    }

    public void move() {
        int[] pos = getPosition();
        pos[0] += DR[direction]; pos[1] += DC[direction];
        setPosition(pos);
    }

    public void changeDirection(){
        int t = RG.nextInt(6);
        int i = 0;
        int newDirection = (direction + diffs[t][i]) % 4;
        while (! canMoveInDirection(newDirection)) {
            i ++;
            newDirection = (direction + diffs[t][i]) % 4;
        } direction = newDirection;
    }

    private boolean canMoveInDirection(int direction) {
        int[] pos = getPosition();
        return moverManager.canMove(pos[0],pos[1],direction);
    }

    private ArrayList<BuildingTileModel> surroundings(){
        ArrayList<BuildingTileModel> buildings = new ArrayList<>();
        int checkDirection = 0;
        while (checkDirection < 4){
            int[] pos = getPosition();
            BuildingTileModel b = moverManager.getBuilding(pos[0],pos[1],checkDirection);
            if (b != null) {
              buildings.add(b);
            } checkDirection++;
        }
        System.out.println(buildings);
        return buildings;
    }

    public void checkSurrounding(){
        for (BuildingTileModel building : surroundings()) {
            if (building.getName().equals(destination)){
                moverManager.destinationReached(this);
            }
        }
    }

}
