import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static long getAmountOfStones(HashMap<Long, Long> stones){
        long sum = 0;
        for (Long stoneAmount : stones.values()) {
            sum += stoneAmount;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("data.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        HashMap<Long, Long> stones = new HashMap<>();
        while (sc.hasNextInt()) {
            stones.put((long)sc.nextInt(), 1L);
        }

        int maxBlinks = 75;

        for (int i = 0; i < maxBlinks; i++) {
            System.out.println("Blink nr. "+i+" Current size: " + getAmountOfStones(stones) + " Current Time: "+ LocalTime.now());
            HashMap<Long, Long> stonesCopy = new HashMap<>();
            for (Long stone : stones.keySet()){
                int length = String.valueOf(stone).length();
                if (stone == 0) {
                    stonesCopy.put(1L, stones.get(0L)+stonesCopy.getOrDefault(1L, 0L));
                } else if (length%2 == 0) {
                    long firstPart = Integer.parseInt(String.valueOf(stone).substring(0, length/2));
                    long secondPart = Integer.parseInt(String.valueOf(stone).substring(length/2));
                    long partAmount = stones.get(stone);
//                    System.out.println(stone+" -> "+firstPart+" | "+secondPart +" x"+partAmount);
                    stonesCopy.put(firstPart, stonesCopy.getOrDefault(firstPart, 0L)+partAmount);
                    stonesCopy.put(secondPart, stonesCopy.getOrDefault(secondPart, 0L)+partAmount);
                } else {
                    stonesCopy.put(stone*2024, stonesCopy.getOrDefault(stone*2024, 0L)+stones.get(stone));
//                    System.out.println(stone+" -> "+stone*2024+" x"+stonesCopy.get(stone*2024));
                }
            }
            stones = stonesCopy;
//            for (Long stone : stones.keySet()) {
//                long N = stones.get(stone);
//                for (int j = 0; j < N; j++) {
//                    System.out.println(stone);
//                }
//            }
//            System.out.println();
        }
        long sum = getAmountOfStones(stones);
        System.out.println();
        System.out.println(sum);
    }
}