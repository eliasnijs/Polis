package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.buildings.tiles.Building;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;
import polis.components.playingfield.buildings.tiles.buildings.Commerce;
import polis.helpers.PropertyLoader;

public class Cargo extends Mover {

    public Cargo(int row, int column, ActorField actorField, int[] coords, int id, Building factory) {
        super(row, column, "commerce","#FA944B", "goods", actorField, coords, id, factory);
    }

    @Override
    public void time0() {
        getHome().factorCapacity(Double.parseDouble(
                PropertyLoader.getProperty("engine","factor.goods.not.delivered")));
        getActorField().removeActor(this);
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        if (b.getName().equals(getDestination())) {
            Commerce shop = (Commerce) b;
            if (shop.getGoods() < shop.getGoodsCapacity()-1) {
                getActorField().removeActor(this);
                shop.addGoods(1);
                getHome().factorCapacity(Double.parseDouble(
                        PropertyLoader.getProperty("engine","factor.goods.delivered")));
            }
        }
        return false;
    }

}
