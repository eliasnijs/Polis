package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE = "Polis - A city builder";
    private static final String FXML_LOCATION = "/polis/main.fxml";


    @Override
    public void start(Stage stage) throws Exception {

        Label l = new Label("POLIS");
        l.setStyle("-fx-text-fill: #ffffffff; -fx-font-size: 72px");
        Button b = new Button("Press me!!!");
        b.setStyle("-fx-background-color: ddddddff; -fx-text-fill: black;");
        VBox v = new VBox(l,b);
        v.setAlignment(Pos.CENTER);
        v.setSpacing(30);
        Label l2 = new Label("" +
                "Programming - Elias Nijs\n" +
                "Asset Design - Elias Nijs\n" +
                "Music - HeatleyBros");
        l2.setAlignment(Pos.CENTER);
        VBox v2 = new VBox(v,l2);
        v2.setSpacing(60);
        v2.setAlignment(Pos.CENTER);
        l2.setStyle("-fx-text-fill: e2e2e2; -fx-alignment: center");

        StackPane pane = new StackPane(v2);
        Scene start = new Scene(pane, 1600,685);
        start.getStylesheets().add("/polis/main.css");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_LOCATION));
        Parent root = loader.load();
        Scene game = new Scene(root, 1600,685);

        b.setOnAction(e -> stage.setScene(game));

        stage.setScene(start);
        stage.setTitle(TITLE);
        stage.show();
    }

}
