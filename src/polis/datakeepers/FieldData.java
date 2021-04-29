package polis.datakeepers;

/**
 * Klasse die de algemene data over het speelveld bijhoudt. Deze klasse bevat statische methodes voor de eigenschappen
 * zodat deze overal gemakkelijk kunnen opgevraagd worden.
 * **/
public class FieldData {

    private final static int CELL_SIZE = 64;
    private final static int GRID_SIZE = 32;
    private static final ImageLoader imageLoader = new ImageLoader();

    public static int getCellSize() {
        return CELL_SIZE;
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }

}
