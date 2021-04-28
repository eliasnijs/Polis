package polis.uicomponents;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public class Stats implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private String location;
    private String name;

    private int[] bewoners;
    private int[] jobs;
    private int[] goederen;
    private int[] klanten;

    public Stats() {
        location = "General";
        name = "STATISTIEKEN";
        reset();
    }

    public void reset(){
        this.bewoners = new int[]{0,0};
        this.jobs = new int[]{0,0};
        this.goederen = new int[]{0,0};
        this.klanten = new int[]{0,0};
    }

    public int[] getBewoners() {
        return bewoners;
    }

    public void addBewoners(double occ, double cap) {
        bewoners[0] += occ; bewoners[1] += cap;
        fireInvalidationEvent();
    }

    public int[] getJobs() {
        return jobs;
    }

    public void addJobs(double occ, double cap) {
        jobs[0] += occ; jobs[1] += cap;
        fireInvalidationEvent();
    }

    public int[] getGoederen() {
        return goederen;
    }

    public void addGoederen(double occ, double cap) {
        goederen[0] += occ; goederen[1] += cap;
        fireInvalidationEvent();
    }

    public int[] getKlanten() {
        return klanten;
    }

    public void addKlanten(double occ, double cap) {
        klanten[0] += occ; klanten[1] += cap;
        fireInvalidationEvent();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
