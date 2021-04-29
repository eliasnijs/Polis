package polis.uicomponents.statistics;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Visuele representatie van het de statistieken.
 * Verantwoordelijk voor het binnenhalen, en tonen van de juiste statistieken.
 * **/

public class StatsPanel extends VBox implements InvalidationListener {

    private final Label location;
    private final Label name;
    private final Label bewoners;
    private final Label jobs;
    private final Label goederen;
    private final Label klanten;

    private Stats model;

    public StatsPanel () {
        location = new Label();
        name = new Label();
        bewoners = new Label();
        jobs = new Label();
        goederen = new Label();
        klanten = new Label();
        this.getChildren().addAll(location, name, bewoners, jobs, goederen, klanten);
        this.getChildren().forEach(o -> o.setId("statsLabel"));
        name.setId("statsTitle");
        location.setId("statsTime");
    }

    public void setModel(Stats model) {
        this.model = model;
        model.addListener(this);
        Update();
    }

    private void Update(){
        location.setText(model.getLocation());
        name.setText(model.getName());
        getChildren().clear();
        getChildren().addAll(location, name);
        configureLabel(bewoners,
                "Bewoners: " + model.getBewoners()[0] + "/" + model.getBewoners()[1],
                model.getBewoners()[1]);
        configureLabel(jobs,
                "Jobs: " + model.getJobs()[0] + "/" + model.getJobs()[1],
                model.getJobs()[1]);
        configureLabel(goederen,
                "Goederen: " + model.getGoederen()[0] + "/" + model.getGoederen()[1],
                model.getGoederen()[1]);
        configureLabel(klanten,
                "Klanten: " + model.getKlanten()[0] + "/" + model.getKlanten()[1],
                model.getKlanten()[1]);
    }

    // Zorgt ervoor dat geen lege data getoond wordt.
    private void configureLabel(Label label, String message, double capacity){
        if (capacity > 0) {
            label.setText(message);
            getChildren().add(label);
        }
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
