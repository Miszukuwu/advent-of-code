import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        char[] disk = sc.nextLine().toCharArray();
        Block[] fileBlocks = new Block[disk.length/2+1];
        for (int i = 0, j = 0; i < disk.length; i+=2,j++) {
            fileBlocks[j] = new Block(j, (int)disk[i]-'0', (i+1<disk.length-1)?(int)disk[i+1]-'0':0);
        }
        String compactedFIle = "";
        for (Block block : fileBlocks) {
            for (int i = 0; i < block.size; i++) {
                System.out.print(block.id);
                compactedFIle += block.id;
            }
            for (int i = 0; i < block.freeSpace; i++) {
                System.out.print(".");
                compactedFIle += ".";
            }
        }
        System.out.println();
        for (int i = compactedFIle.length()-1; i >= 0; i--) {
            int length = compactedFIle.length();
            int spaceIndex = compactedFIle.indexOf('.');
            if (spaceIndex>i)
                break;
            compactedFIle = compactedFIle.substring(0, spaceIndex) + compactedFIle.charAt(i) + compactedFIle.substring(spaceIndex+1, i);
            for (int j = 0; j <  length - i; j++) {
                compactedFIle += '.';
            }
//            System.out.println(compactedFIle);
        }
        int sum = 0;
        for (int i = 0; i < compactedFIle.length(); i++) {
            if (compactedFIle.charAt(i)=='.'){
                break;
            }
            sum += (compactedFIle.charAt(i)-'0')*i;
        }
        System.out.println(sum);
    }
}