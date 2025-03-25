import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
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
        ArrayList<Long> stones = new ArrayList<>();
        while (sc.hasNextInt()) {
            stones.add((long)sc.nextInt());
        }
        int maxBlinks = 75;
        boolean mitosisHappened = false;
        for (int i = 0; i < maxBlinks; i++) {
            System.out.println("Blink nr. "+i+" Current size: "+stones.size() + " Current Time: "+ LocalTime.now());
            for (int j = 0; j < stones.size(); j++) {
                if (mitosisHappened) {
                    mitosisHappened = false;
                    continue;
                }
                long stone = stones.get(j);
                int length = String.valueOf(stone).length();
                if (stone == 0) {
                    stones.set(j, 1L);
                } else if (length%2 == 0) {
                    long firstPart = Integer.parseInt(String.valueOf(stone).substring(0, length/2));
                    long secondPart = Integer.parseInt(String.valueOf(stone).substring(length/4));
                    stones.set(j, firstPart);
                    stones.add(j, secondPart);
                    mitosisHappened = true;
                } else {
                    stones.set(j, stone*2024);
                }
            }
        }
        int sum = stones.size();
        for (long stone : stones) {
            System.out.print(stone + " ");
        }
        System.out.println();
        System.out.println(sum);
    }
}