package polis.components.playingfield.buildings.tiles.buildings;

import polis.components.playingfield.buildings.tiles.Building;
import polis.helpers.PropertyLoader;

public class Commerce extends Building {

    private double goods;
    private double goodsCapacity;

    private double jobs;
    private double jobsCapacity;

    private final double customersPerTrader;
    private final double goodsPerCustomer;

    private final double goodTrade;
    private final double badTrade;

    public Commerce(int row, int column) {
        super(row, column, "commerce", "job", BuildingProperties.COMMERCE);
        goodsPerCustomer = Double.parseDouble(PropertyLoader.getProperty("engine", "goods.per.customer"));
        customersPerTrader = Double.parseDouble(PropertyLoader.getProperty("engine", "customers.per.trader"));
        goodTrade = Double.parseDouble(PropertyLoader.getProperty("engine", "factor.good.trade"));
        badTrade = Double.parseDouble(PropertyLoader.getProperty("engine", "factor.bad.trade"));
        updateCapacities();
    }

    @Override
    public void Update() {
        super.Update();
        updateCapacities();
        goodTrade();
    }

    private void updateCapacities() {
        jobsCapacity = getCapacity() / customersPerTrader;
        goodsCapacity = getCapacity() * goodsPerCustomer;
    }

    private void goodTrade() {
        if (getCapacity() == getOccupancy()) {
            factorCapacity(goodTrade);
        }
    }

    public void badTrade() {
        factorCapacity(badTrade);
        Update();
    }

    public double getGoods() {
        return goods;
    }

    public double getGoodsCapacity() {
        return goodsCapacity;
    }

    public double getJobs() {
        return jobs;
    }

    public double getJobsCapacity() {
        return jobsCapacity;
    }

    public void addGoods(int amount) {
        goods += amount;
        Update();
    }

    public void addJobs(int amount) {
        jobs += amount;
        Update();
    }

}
