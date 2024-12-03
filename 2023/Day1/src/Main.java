import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("dane.txt");
            Scanner sc = new Scanner(file);
            int sum = 0;
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                boolean firstDigit = true;
                boolean secondDigit = false;
                int temp = 0;
                int temp2 = 0;
                char charData;
                String subData = "";
                int digit = 0;
                System.out.println("string: " + data);
                for (int i = 0; i < data.length(); i++) {


                    charData = data.charAt(i);

                    System.out.println("char: " + charData);

                    if (Character.isDigit(charData)) {
                        digit = Character.getNumericValue(charData);
                        if (firstDigit) {
                            temp = digit;
                            firstDigit = false;

                            System.out.println(temp);
                        } else {
                            temp2 = digit;
                            secondDigit = true;

                            System.out.println(temp2);
                        }
                    } else {
                        if (i < data.length() - 4) {
                            subData = data.substring(i, i + 5);
                            switch (subData) {
                                case "three":
                                    digit = 3;
                                    break;
                                case "seven":
                                    digit = 7;
                                    break;
                                case "eight":
                                    digit = 8;
                                    break;
                                default:
                            }
                        }
                        if (i < data.length() - 3) {
                            subData = data.substring(i, i + 4);
                            switch (subData) {
                                case "four":
                                    digit = 4;
                                    break;
                                case "five":
                                    digit = 5;
                                    break;
                                case "nine":
                                    digit = 9;
                                    break;
                                default:
                            }
                        }
                        if (i < data.length() - 2) {
                            subData = data.substring(i, i + 3);
                            switch (subData) {
                                case "one":
                                    digit = 1;
                                    break;
                                case "two":
                                    digit = 2;
                                    break;
                                case "six":
                                    digit = 6;
                                    break;
                                default:
                            }
                        }
                        System.out.println("SubString: " + subData);
                        if (firstDigit && digit!=0) {
                            temp = digit;
                            firstDigit = false;

                            System.out.println(temp);
                        } else if (digit!=0){
                            temp2 = digit;
                            secondDigit = true;

                            System.out.println(temp2);
                        }
                    }
                }

                    int liczba = (secondDigit) ? (temp * 10 + temp2) : (temp * 10 + temp);

                    System.out.println("Cyfra 1 " + temp);
                    System.out.println("Cyfra 2 " + temp2);
                    System.out.println("Liczba: " + liczba);

                    sum += liczba;

                    System.out.println("Suma: " + sum);
                    System.out.println("==============================");
            }
                System.out.println(sum);
                sc.close();
            } catch(FileNotFoundException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }