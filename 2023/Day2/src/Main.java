import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("dane.txt");
            Scanner sc = new Scanner(file);
            int id,red,minRed,green,minGreen,blue,minBlue,sum;
//            boolean possible;
            sum = 0;
            String[] firstSplit,secondSplit,thirdSplit, fourthSplit, fithfSplit;
            while (sc.hasNextLine()) {
                minRed = 100;
                minGreen = 100;
                minBlue = 100;
//                possible = true;
                String line = sc.nextLine();
                firstSplit = line.split("\\:");
                System.out.println(firstSplit[1]);
                fithfSplit = firstSplit[0].split("\\ ");
                secondSplit = firstSplit[1].split("\\;");

                id = Integer.parseInt(fithfSplit[1]);
                System.out.println("id: "+id);
                for (int i = 0; i < secondSplit.length; i++) {
                    red = 100;
                    green = 100;
                    blue = 100;
                    System.out.println(secondSplit[i]);
                    thirdSplit = secondSplit[i].trim().split("\\,");

                    for (int j = 0; j < thirdSplit.length; j++) {
//                        System.out.println(thirdSplit[j]);
                        fourthSplit = thirdSplit[j].trim().split("\\ ");
//                        for (int k = 0; k < fourthSplit.length; k++) {

                            switch (fourthSplit[1]){
                                case "blue":
                                    blue = Integer.parseInt(fourthSplit[0]);
                                    break;
                                case "green":
                                    green = Integer.parseInt(fourthSplit[0]);
                                    break;
                                case "red":
                                    red = Integer.parseInt(fourthSplit[0]);
                                    break;
                            }
//                      }
                    }
                    if (red<minRed)
                        minRed = red;
                    if (green<minGreen)
                        minGreen = green;
                    if (blue<minBlue)
                        minBlue = blue;

//                    if ((red>12||green>13||blue>14)){
//                        possible = false;
//                    }
                }
                System.out.println("min red: "+minRed+" green: "+minGreen+" blue: "+minBlue);
                System.out.println("Suma wynosi: "+minRed*minGreen*minBlue);
                sum+= minRed*minGreen*minBlue;
                System.out.println("Łącznie jest teraz: "+sum);
//                System.out.println(possible);
//                if (possible){
//                    sum+=id;
//                }
            }
            System.out.println("Odpowiedź to: "+sum);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}