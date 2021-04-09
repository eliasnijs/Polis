package polis.other.clouds;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Map;

public class CloudView extends Pane {

    private Clouds clouds;
    private Polygon[][] map;
    boolean[][] display;
    private static final Map<Boolean, Color> color = Map.of(
            true, Color.BLUE,
            false, Color.RED
    );

    public CloudView(){
        clouds = new Clouds();
        display = clouds.getDisplayMap();
        map = new Polygon[32][32];

        for (int i=0; i < display.length; i++) {
            for (int j=0; j < display.length; j++) {
                Polygon p = new Polygon(0, 0, 64, 0.5 * 64, 0, 64, -64, 0.5 * 64);
                p.setFill(color.get(display[i][j]));
                int[] c = gridToCoordinates(i,j);
                p.setTranslateX(c[0]);
                p.setTranslateY(c[1]);
                map[i][j] = p;
                getChildren().add(p);
            }
        }

        this.setTranslateX((double)(64-1) * 32);
        this.setOnMouseReleased(e -> advance());
    }

    public int[] gridToCoordinates(int row, int column){
        int x = 64 * (1 - row  + column);
        int y = 64 * (row + column) / 2;
        return new int[]{x,y};
    }

    public void advance(){
        clouds.advance();
        display = clouds.getDisplayMap();
        for (int i=0; i < display.length; i++) {
            for (int j=0; j < display.length; j++) {
                map[i][j].setFill(color.get(display[i][j]));
            }
        }
        System.out.println("----");
    }


}
