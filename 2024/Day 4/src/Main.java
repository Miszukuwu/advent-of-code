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
        char[][] lines = new char[linesList.size()][linesList.get(0).length()];
        for (int i = 0; i < linesList.size(); i++) {
            for (int j = 0; j < linesList.get(i).length(); j++) {
                lines[i][j] = linesList.get(i).charAt(j);
            }
        }
        return lines;
    }
    public static void main(String[] args) throws FileNotFoundException {
        char[][] chars = getAllLines(new File("data.txt"));
        char[][] tempChars = getAllLines(new File("data.txt"));
        int N = chars.length;
        int M = chars[0].length;
        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (chars[i][j] == 'X') {
                    if (i >= 3) {
                        if (chars[i - 1][j] == 'M' && chars[i - 2][j] == 'A' && chars[i - 3][j] == 'S') {
                            sum++;
                        }
                        if (j >= 3) {
                            if (chars[i - 1][j - 1] == 'M' && chars[i - 2][j - 2] == 'A' && chars[i - 3][j - 3] == 'S') {
                                sum++;
                            }
                        }
                        if (j <= M - 4) {
                            if (chars[i - 1][j + 1] == 'M' && chars[i - 2][j + 2] == 'A' && chars[i - 3][j + 3] == 'S') {
                                sum++;
                            }
                        }
                    }
                    if (j >= 3) {
                        if (chars[i][j - 1] == 'M' && chars[i][j - 2] == 'A' && chars[i][j - 3] == 'S') {
                            sum++;
                        }
                    }
                    if (j <= M - 4) {
                        if (chars[i][j + 1] == 'M' && chars[i][j + 2] == 'A' && chars[i][j + 3] == 'S') {
                            sum++;
                        }
                    }
                    if (i <= N - 4) {
                        if (chars[i + 1][j] == 'M' && chars[i + 2][j] == 'A' && chars[i + 3][j] == 'S') {
                            sum++;
                        }
                        if (j >= 3) {
                            if (chars[i + 1][j - 1] == 'M' && chars[i + 2][j - 2] == 'A' && chars[i + 3][j - 3] == 'S') {
                                sum++;
                            }
                        }
                        if (j <= M - 4) {
                            if (chars[i + 1][j + 1] == 'M' && chars[i + 2][j + 2] == 'A' && chars[i + 3][j + 3] == 'S') {
                                sum++;
                            }
                        }
                    }
                }
                if (chars[i][j] == 'A') {
                    if (i == 0 || i == N-1 || j == 0 || j == M-1){
                        continue;
                    }
                    if ((chars[i-1][j-1] == 'M' && chars[i+1][j+1] == 'S') || (chars[i-1][j-1] == 'S' && chars[i+1][j+1] == 'M')){
                        if ((chars[i-1][j+1] == 'M' && chars[i+1][j-1] == 'S') || (chars[i-1][j+1] == 'S' && chars[i+1][j-1] == 'M')){
                            sum2++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(tempChars[i][j]);
            }
            System.out.println();
        }
        System.out.println(sum);
        System.out.println(sum2);
    }
}