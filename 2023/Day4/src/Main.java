import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
//    static void convertStringToIntArray(String[] strings, int[] ints){
//        for (int i = 0; i < strings.length; i++) {
//            ints[i] = Integer.parseInt(strings[i]);
//        }
//    }
    public static void main(String[] args) {
        String filePath = "dane.txt";

        try (Scanner sc = new Scanner(new File(filePath))) {
            int sum = 0;
            int[] numberOfCard = new int[6];
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\:");
                String[] allNumbers = line[1].trim().split("\\|");
                String[] myNumbers = allNumbers[0].trim().split("\\ ");
                String[] winningNumbers = allNumbers[1].trim().split("\\ ");
                int wins = 0;
//                int points = 0;
                System.out.print("Matching numbers: ");
                for (String myNumber : myNumbers) {
                    for (String winningNumber : winningNumbers) {
                        if (myNumber.equals(winningNumber) && !winningNumber.equals(""))
                        {
                            System.out.print(myNumber+" ");
                            wins++;
                        }
                    }
                }
                System.out.println();
//                System.out.println(wins);
//                if (wins>0){
//                    points=1;
//                    for (int i = 0; i < wins - 1; i++) {
//                        points*=2;
//                    }
//                }
//                System.out.println("points = " + points);
//                sum+=points;
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
