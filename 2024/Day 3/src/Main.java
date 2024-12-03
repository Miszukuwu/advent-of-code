import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        int sum = 0;
        String line = sc.nextLine();
        boolean enabled = true;
        for (int i = 0; i < line.length(); i++) {
            try {
                if (line.substring(i, i + 4).equals("do()") && !enabled){
                    System.out.println(line.substring(i, i + 4) + " detected, Enabling!");
                    enabled = true;
                    continue;
                }
                if (!enabled){
                    continue;
                }
                if (line.substring(i, i + 7).equals("don't()")) {
                    System.out.println(line.substring(i, i + 7) + " detected, disabling!");
                    enabled = false;
                    continue;
                }
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
                // Logging for debuging purpose
                System.out.println(line.substring(i - 3, endOfMulIndex + 1) + " " + line.substring(i - 3, endOfMulIndex + 1).matches("mul\\(\\d{1,3},\\d{1,3}\\)") + " " + enabled);
                System.out.println(a + "\t" + b);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(sum);
    }
}