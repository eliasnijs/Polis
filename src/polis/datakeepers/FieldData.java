package polis.datakeepers;

public class FieldData {

    private static final int CELL_SIZE = 64;
    private static final int GRID_SIZE = 32;
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
