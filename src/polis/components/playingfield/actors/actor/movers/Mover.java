package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.MoverManager;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.Building;
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

    private final String destination;

    public Mover(int row, int column, String destination, String color, String name, ActorField actorField, int[] coords, int id, Building home) {
        super(row, column, actorField, name, coords, id, home);
        this.moverManager = actorField.getMoverManager();
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

    @Override
    public void time0() {
        Actor actor = new Sleeper(getBaseCoords()[0],getBaseCoords()[1], getActorField(), getBaseCoords(), getResidentId(), getHome());
        transitionToNextFase(actor);
    }

    public void act(){
        changeDirection();
        move();
        checkSurrounding();
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
        }
        direction = newDirection;
    }

    private boolean canMoveInDirection(int direction) {
        int[] pos = getPosition();
        return moverManager.canMove(pos[0],pos[1],direction);
    }

    private ArrayList<BuildingTileModel> surroundings(){
        ArrayList<BuildingTileModel> buildings = new ArrayList<>();
        int index = 0;
        while (index < 4){
            int[] pos = getPosition();
            BuildingTileModel b = moverManager.getBuilding(pos[0],pos[1],index);
            if (b != null) {
                buildings.add(b);
            }
            index++;
        }
        return buildings;
    }

    public void checkSurrounding() {
        boolean found = false;
        int index = 0;
        ArrayList<BuildingTileModel> surroundings = surroundings();
        while (!found && index < surroundings.size()) {
            BuildingTileModel building = surroundings.get(index);
            if (this.isDestinationReached(building)) {
                getActorField().nextActorPhase(this, nextPhase());
                found = true;
            }
            index += 1;
        }
    }

    public String getDestination() {
        return destination;
    }

    public abstract boolean isDestinationReached(BuildingTileModel b);

}
