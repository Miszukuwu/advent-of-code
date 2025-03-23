import java.util.ArrayList;

public class Trailhead {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private int score = 0;

    public int getScore() {
        return score;
    }

    public final int x, y;

    ArrayList<String> reachedCoordinates = new ArrayList<>();

    public Trailhead(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void searchForPaths(final ArrayList<ArrayList<Integer>> map, int currentX, int currentY, int currentHeight, Direction lastMove) {
        System.out.println("Current pos: "+currentX+" "+currentY);
        if (map.get(currentY).get(currentX) == 9 && !reachedCoordinates.contains(currentX+" "+currentY)){
            reachedCoordinates.add(currentX+" "+currentY);
            score++;
            return;
        }
        if (currentY != 0 && map.get(currentY-1).get(currentX) == currentHeight+1 && lastMove != Direction.DOWN) {
            // Up
            searchForPaths(map, currentX, currentY-1, currentHeight+1, Direction.UP);
        }
        if (currentX != map.getFirst().size()-1 && map.get(currentY).get(currentX+1) == currentHeight+1 && lastMove != Direction.LEFT){
            // Right
            searchForPaths(map, currentX+1, currentY, currentHeight+1, Direction.RIGHT);
        }
        if (currentY != map.size()-1 && map.get(currentY+1).get(currentX) == currentHeight+1 && lastMove != Direction.UP){
            // Down
            searchForPaths(map, currentX, currentY+1, currentHeight+1, Direction.DOWN);
        }
        if (currentX != 0 && map.get(currentY).get(currentX-1) == currentHeight+1 && lastMove != Direction.RIGHT){
            // Left
            searchForPaths(map, currentX-1, currentY, currentHeight+1, Direction.LEFT);
        }
    }
}
