package polis.uicomponents;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatsPanel extends VBox implements InvalidationListener {

    private final Label location;
    private final Label name;
    private final Label bewoners;
    private final Label jobs;
    private final Label goederen;
    private final Label klanten;

    private Stats model;

    public StatsPanel () {
        location = new Label("00:00:00 ");
        name = new Label("STATISTIEKEN");
        bewoners = new Label("Bewoners: ");
        jobs = new Label("Jobs: ");
        goederen = new Label("Goederen: ");
        klanten = new Label("Klanten: ");
        this.getChildren().addAll(location, name, bewoners, jobs, goederen, klanten);
        this.getChildren().forEach(o -> o.setId("statsLabel"));
        name.setId("statsTitle");
        location.setId("statsTime");
    }

    public void setModel(Stats model) {
        if (model != this.model) {
            model.removeListener(this);
        }
        this.model = model;
        if (model != null) {
            model.addListener(this);
        }
        Update();
    }


    public void Update(){
        location.setText(model.getLocation());
        name.setText(model.getName());
        getChildren().removeIf(o -> o != name && o != location);

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

    private void configureLabel(Label label, String message, double capacity){
        if (capacity != 0) {
            label.setText(message);
            getChildren().add(label);
        }
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
