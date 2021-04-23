package polis.helpers;

public class PropertyTester {

    public static void main(String[] args) {
        PropertyLoader p = new PropertyLoader();
        double initialRate = Double.parseDouble(p.getProperty("engine","region.initial.rate"));
        double slowestRate = Double.parseDouble(p.getProperty("engine","region.slowest.rate"));

        double recoveryFactor = Double.parseDouble(p.getProperty("engine","region.factor.recovery"));
        double slowDownFactor = Double.parseDouble(p.getProperty("engine","region.factor.slow.down"));

        System.out.println(initialRate + " " + slowestRate + " " + recoveryFactor + " " + slowDownFactor + " ");

    }

}
