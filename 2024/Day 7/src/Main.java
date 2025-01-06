import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static boolean checkCalculation(int[] numbers, long target, int index, long sum){
        if (index == numbers.length) {
            return target == sum;
        }
        long sum1 = sum+numbers[index];
        long sum2 = sum*numbers[index];
        long sum3 = Long.parseLong(String.valueOf(sum)+String.valueOf(numbers[index]));
        System.out.println("Index: " + index + ", Sum1: " + sum1 + ", Sum2: " + sum2);
        index++;
        boolean check1 = checkCalculation(numbers, target, index, sum1);
        boolean check2 = checkCalculation(numbers, target, index, sum2);
        boolean check3 = checkCalculation(numbers, target, index, sum3);
        return check1 || check2 || check3;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("data.txt"));
        long result = 0;
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] halfs = line.split(":");
            long testNumber = Long.parseLong(halfs[0]);
            String[] numberStrings = halfs[1].trim().split(" ");
            int[] numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }

            boolean flag = checkCalculation(numbers, testNumber, 1, numbers[0]);
            if (flag){
                result += testNumber;
            }
        }
        System.out.println(result);
        sc.close();
    }
}