package polis.other;


import java.util.ArrayList;
import java.lang.Math;

public class locationGen {
    public ArrayList<int[]> getRandomLocations(int gridsize, int size) {

        // generate
        ArrayList<int[]> locations = new ArrayList<>();
        int i = size;
        while (i > 0) {
            int r = (int) (Math.random()*(gridsize));
            int k = (int) (Math.random()*(gridsize));
            locations.add(new int[]{r,k});
            i--;
        }

        // remove doubles
        ArrayList<int[]> uniquelocations = new ArrayList<>();
        for (int[] c : locations) {
           boolean found = false;
           for (int[] t : uniquelocations) {
               if (c[0] == t[0] && c[1] == t[1]) {
                   found = true;
                   break;
               }
           }
            if (!found) {
                uniquelocations.add(c);
            }
        }


        return uniquelocations;
    }
}
