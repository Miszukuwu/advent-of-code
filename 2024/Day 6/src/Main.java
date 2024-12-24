import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("data.txt"));
        ArrayList<String> allLines = new ArrayList<>();
        while (sc.hasNextLine()) {
            allLines.add(sc.nextLine());
        }
        char[][] map = new char[allLines.size()][allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                map[i][j] = allLines.get(i).charAt(j);
            }
        }
        int guardX = 0, guardY = 0;
        char direction = '^';
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '^') {
                    guardX = j;
                    guardY = i;
                }
            }
        }
        int nextX = guardX, nextY = guardY;
        int sum = 1;
        do {
            switch (direction) {
                case '^':
                    nextY--;
                    break;
                case '>':
                    nextX++;
                    break;
                case 'V':
                    nextY++;
                    break;
                case '<':
                    nextX--;
                    break;
            }
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length){
                break;
            }
            if (map[nextY][nextX] == '#'){
                switch (direction) {
                    case '^':
                        direction = '>';
                        nextY++;
                        nextX++;
                        break;
                    case '>':
                        direction = 'V';
                        nextX--;
                        nextY++;
                        break;
                    case 'V':
                        direction = '<';
                        nextY--;
                        nextX--;
                        break;
                    case '<':
                        direction = '^';
                        nextX++;
                        nextY--;
                        break;
                }
            }
            if (map[nextY][nextX] != 'X'){
                sum++;
            }
            map[guardY][guardX] = 'X';
            guardX = nextX;
            guardY = nextY;
            map[guardY][guardX] = direction;
        } while (true);
        System.out.println(sum);
    }
}