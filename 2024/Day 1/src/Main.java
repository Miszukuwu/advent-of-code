import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("data.txt");
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        while(sc.hasNext()){
            list1.add(sc.nextInt());
            list2.add(sc.nextInt());
        }
        list1.sort(null);
        list2.sort(null);
        int sum1 = 0;
        for (int i = 0; i < list1.size(); i++) {
            sum1 += Math.abs(list1.get(i)-list2.get(i));
        }
        System.out.println(sum1);
        int sum2 = 0;
        for (Integer i : list1) {
            int numberOfAppearance = list2.lastIndexOf(i)+1-list2.indexOf(i);
            if (list2.indexOf(i) == -1) numberOfAppearance = 0;
            System.out.println(i+" "+numberOfAppearance);
            sum2 += i*numberOfAppearance;
        }
        System.out.println(sum2);
    }
}