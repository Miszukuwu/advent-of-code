import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean isLevelsSafe(ArrayList<Integer> levels){
        int increase = levels.get(1) - levels.get(0);
        boolean isIncreasing = Math.abs(increase) == increase;
        int numberOfErrors = 0;
        for (int i = 0; i < levels.size()-1; i++) {
            if (numberOfErrors > 1){
                return false;
            }
            int difference = levels.get(i+1)-levels.get(i);
            if (Math.abs(difference) < 1 || Math.abs(difference) > 3 || difference > 0 != isIncreasing) {
                numberOfErrors++;
                levels.remove(i + 1);
                i = -1;
            }
        }
        return true;
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        int sum = 0;
        while (sc.hasNextLine()){
            String currentLine = sc.nextLine();
            String[] levelsString = currentLine.split(" ");
            ArrayList<Integer> levels = new ArrayList<>();
            for (String s : levelsString) {
                levels.add(Integer.parseInt(s));
            }
            if (isLevelsSafe(levels))
                sum++;
        }
        System.out.println(sum);
    }
}