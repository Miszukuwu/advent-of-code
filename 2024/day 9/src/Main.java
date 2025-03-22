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
        ArrayList<Integer> compactedFileList = new ArrayList<>();
        for (Block block : fileBlocks) {
            for (int i = 0; i < block.size; i++) {
                System.out.print(block.id);
                compactedFileList.add(block.id);
            }
            for (int i = 0; i < block.freeSpace; i++) {
                System.out.print(".");
                compactedFileList.add(-1);
            }
        }
        System.out.println();
//        for (int i = 0; i < compactedFileList.size(); i++) {
//            if (compactedFileList.get(i) == -1) {
//                int value = -1;
//                while (value == -1){
//                    value = compactedFileList.removeLast();
//                }
//                if (compactedFileList.size() <= i){
//                    compactedFileList.add(value);
//                    break;
//                }
//                compactedFileList.remove(i);
//                compactedFileList.add(i, value);
//            }
//        }
        for (int i = compactedFileList.size() - 1; i > 0; i--) {
            int id = compactedFileList.get(i);
            if (id == -1)
                continue;
            int startIndex = i;
            while (startIndex > 0 && compactedFileList.get(startIndex) == id) {
                startIndex--;
            }
            int discSize = i - startIndex;
            int freeSpace = 0, freeSpaceIndex = 0;
            for (int j = 0; j < i; j++) {
                if (freeSpace >= discSize) {
                    break;
                }
                if (freeSpace > 0 && compactedFileList.get(j) != -1) {
                    freeSpace = 0;
                } else if (compactedFileList.get(j) == -1) {
                    if (freeSpace == 0) {
                        freeSpaceIndex = j;
                    }
                    freeSpace++;
                }
            }
            if (freeSpace == 0 || freeSpace < discSize) {
                i = startIndex + 1;
                continue;
            }
            for (int j = 0; j < freeSpace; j++) {
                compactedFileList.remove(freeSpaceIndex);
            }
            for (int j = 0; j < discSize; j++) {
                compactedFileList.add(freeSpaceIndex, id);
            }
            for (int j = 0; j < discSize; j++) {
                compactedFileList.add(i + 1, -1);
                compactedFileList.remove(i--);
            }
            i++;
        }
        for (Integer i : compactedFileList) {
           if (i != -1){
               System.out.print(i);
           } else {
               System.out.print('.');
           }
        }
        long sum = 0;
        for (int i = 0; i < compactedFileList.size(); i++) {
            if (compactedFileList.get(i) != -1)
                sum += i*compactedFileList.get(i);
        }
        System.out.println();
        System.out.println(sum);
    }
}