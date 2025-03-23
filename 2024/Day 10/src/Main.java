import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("data.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] heights = line.split("");
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < heights.length; i++) {
                String height = heights[i];
                row.add(Integer.parseInt(height));
            }
            map.add(row);
        }
        ArrayList<Trailhead> trailheads = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            ArrayList<Integer> heights = map.get(i);
            for (int j = 0; j < heights.size(); j++) {
                Integer height = heights.get(j);
                if (height == 0){
                    trailheads.add(new Trailhead(j, i));
                }
            }
        }
        long sum = 0;
        for (Trailhead trailhead : trailheads) {
            trailhead.searchForPaths(map, trailhead.x, trailhead.y, 0, null);
            sum += trailhead.getScore();
        }
        System.out.println(sum);
    }
}