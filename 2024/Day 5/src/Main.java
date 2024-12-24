import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String checkUpdateOrder(String rule, String update){
        ArrayList<Integer> pages = new ArrayList<>();
        String[] temp = update.split(",");
        for (int i = 0; i < temp.length; i++) {
            pages.add(Integer.parseInt(temp[i]));
        }
        temp = rule.split("\\|");
        int rule1 = Integer.parseInt(temp[0]);
        int rule2 = Integer.parseInt(temp[1]);

        if (pages.contains(rule1) && pages.contains(rule2)){
            for (Integer page : pages) {
                if (page==rule2){
                    int indexOfRule2 = pages.indexOf(rule2);
                    pages.set(pages.indexOf(rule1), rule2);
                    pages.set(indexOfRule2, rule1);
                    String result = "";
                    for (int i = 0; i < pages.size(); i++) {
                        result += pages.get(i);
                        if (i!=pages.size()-1){
                            result+=",";
                        }
                    }
                    return result;
                }
                if (page==rule1){
                    return update;
                }
            }
        }
        return update;
    }
    public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(new File("data.txt"));
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> updates = new ArrayList<>();
        int sum = 0, sum2 = 0;
        while (sc.hasNext()) {
            String rule = sc.nextLine();
            if (rule.equals("")){
             break;
            }
            rules.add(rule);
        }
        while (sc.hasNext()){
            updates.add(sc.nextLine());
        }
        boolean valid;
        String tempUpdate = "";
        for (String update : updates) {
            valid = true;
            for (String rule : rules) {
                tempUpdate = checkUpdateOrder(rule, update);
                if (!tempUpdate.equals(update)){
                    valid = false;
                    break;
                }
            }
            if (valid){
                String[] pages = update.split(",");
                sum += Integer.parseInt(pages[pages.length/2]);
            } else {
                String[] pages = tempUpdate.split(",");
                sum2 += Integer.parseInt(pages[pages.length/2]);
            }
        }
        System.out.println(sum);
        System.out.println(sum2);
    }
}