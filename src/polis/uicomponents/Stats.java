package polis.uicomponents;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public class Stats implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private String time;
    private String name;

    private double[] bewoners;
    private double[] jobs;
    private double[] goederen;
    private double[] klanten;

    public Stats() {
        time = "00:00";
        name = "STATISTIEKEN";
        this.bewoners = new double[]{0,0};
        this.jobs = new double[]{0,0};
        this.goederen = new double[]{0,0};
        this.klanten = new double[]{0,0};
    }

    public void reset(){
        this.bewoners = new double[]{0,0};
        this.jobs = new double[]{0,0};
        this.goederen = new double[]{0,0};
        this.klanten = new double[]{0,0};
    }

    public double[] getBewoners() {
        return bewoners;
    }

    public void setBewoners(double occ, double cap) {
        bewoners[0] += occ; bewoners[1] += cap;
        fireInvalidationEvent();
    }

    public double[] getJobs() {
        return jobs;
    }

    public void setJobs(double occ, double cap) {
        jobs[0] += occ; jobs[1] += cap;
        fireInvalidationEvent();
    }

    public double[] getGoederen() {
        return goederen;
    }

    public void setGoederen(double[] goederen) {
        this.goederen = goederen;
        fireInvalidationEvent();
    }

    public double[] getKlanten() {
        return klanten;
    }

    public void setKlanten(double[] klanten) {
        this.klanten = klanten;
        fireInvalidationEvent();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        fireInvalidationEvent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        fireInvalidationEvent();
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
