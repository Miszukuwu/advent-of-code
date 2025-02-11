import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        char[] disk = sc.nextLine().toCharArray();
        Block[] fileBlocks = new Block[disk.length/2];
        for (int i = 0, j = 0; i < disk.length; i+=2,j++) {
            fileBlocks[j] = new Block(j, (int)disk[i], (i+1<disk.length-1)?(int)disk[i+1]:0);
        }
        for (Block block : fileBlocks) {
            for (int i = 0; i < block.size; i++) {
                System.out.println(block.id);
            }
            for (int i = 0; i < block.freeSpace; i++) {
                System.out.println(".");
            }
        }
    }
}