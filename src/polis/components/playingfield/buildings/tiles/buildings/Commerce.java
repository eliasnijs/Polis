package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;
import polis.helpers.PropertyLoader;

public class Commerce extends Building {

    private double goods;
    private double goodsCapacity;

    private double jobs;
    private double jobsCapacity;

    private double customersPerTrader;
    private double goodsPerCustomer;

    public Commerce(int row, int column) {
        super(row, column, "commerce", "job", BuildingProperties.COMMERCE);
        PropertyLoader p = new PropertyLoader();
        goodsPerCustomer = Double.parseDouble(p.getProperty("engine","goods.per.customer"));
        customersPerTrader = Double.parseDouble(p.getProperty("engine","customers.per.trader"));
    }

    @Override
    public void Update() {
        super.Update();
        setCapacity(Math.min(jobsCapacity*customersPerTrader + 1, goods));
        jobsCapacity = getCapacity() / customersPerTrader;
        goodsCapacity = getCapacity() * goodsPerCustomer;
    }


}
