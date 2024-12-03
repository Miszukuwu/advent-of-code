import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static char[][] getAllLines(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
        ArrayList<String> linesList = new ArrayList<>();
        while (sc.hasNext()){
            linesList.add(sc.nextLine());
        }
        char[][] lines = new char[linesList.size()][linesList.getFirst().length()];
        for (int i = 0; i < linesList.size(); i++) {
            for (int j = 0; j < linesList.get(i).length(); j++) {
                lines[i][j] = linesList.get(i).charAt(j);
            }
        }
        return lines;
    }
    public static void main(String[] args) throws FileNotFoundException {
        char[][] lines = getAllLines(new File("data.txt"));

    }
}