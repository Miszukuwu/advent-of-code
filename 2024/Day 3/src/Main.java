import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        int sum = 0;
        while (sc.hasNext()){
            String line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                try {
                    if (!line.substring(i, i + 3).equals("mul")) {
                        continue;
                    }
                    i += 3;
                    if (line.charAt(i) != '(') {
                        continue;
                    }
                    int endOfMulIndex = 0;
                    for (int j = i; j < i + 9; j++) {
                        if (line.charAt(j) == ')') {
                            endOfMulIndex = j;
                            break;
                        }
                    }
                    if (endOfMulIndex == 0) {
                        continue;
                    }
                    if (!line.substring(i, endOfMulIndex).contains(",")) {
                        continue;
                    }
                    String[] numbers = line.substring(i + 1, endOfMulIndex).split(",");
                    if (numbers.length < 2) {
                        continue;
                    }
                    int a = Integer.parseInt(numbers[0]);
                    int b = Integer.parseInt(numbers[1]);
                    sum += a * b;
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        System.out.println(sum);
    }
}