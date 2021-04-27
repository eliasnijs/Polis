package polis.uicomponents;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatsPanel extends VBox implements InvalidationListener {

    private Label time;
    private Label name;
    private Label bewoners;
    private Label jobs;
    private Label goederen;
    private Label klanten;

    private Stats model;

    public StatsPanel () {
        time = new Label("00:00:00 ");
        name = new Label("STATISTIEKEN");
        bewoners = new Label("Bewoners: ");
        jobs = new Label("Jobs: ");
        goederen = new Label("Goederen: ");
        klanten = new Label("Klanten: ");
        this.getChildren().addAll(time, name, bewoners, jobs, goederen, klanten);
        this.getChildren().forEach(o -> o.setId("statsLabel"));
        name.setId("statsTitle");
        time.setId("statsTime");
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
        time.setText(model.getTime());
        name.setText(model.getName());
        bewoners.setText("Bewoners: " + model.getBewoners()[0] + "/" + model.getBewoners()[1]);
        jobs.setText("Jobs: " + model.getJobs()[0] + "/" + model.getJobs()[1]);
        goederen.setText("Goederen: " + model.getGoederen()[0] + "/" + model.getGoederen()[1]);
        klanten.setText("Klanten: " + model.getKlanten()[0] + "/" + model.getKlanten()[1]);

//        this.getChildren().clear();
//        this.getChildren().addAll(time,name,jobs);
    }

    @Override
    public void invalidated(Observable observable) {
        Update();
    }

}
