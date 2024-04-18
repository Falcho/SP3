package util;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final Scanner scan = new Scanner(System.in);

    public void displayMsg(String msg) {
        System.out.println(msg);
    }

    public void displayList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    /*public void displayMedia(List<Media> mediaList, int start) {
         if (mediaList.size() > start && mediaList.size() <= 10) {
            for (int i = start; i < mediaList.size(); i++) {
                System.out.println(mediaList.get(i));
            }
        }
    }*/

    public String promptText(String s) {
        displayMsg(s);
        String input = scan.nextLine();
        return input;
    }

    public int promptNumeric(String s, int maxValue) {
        displayMsg(s);
        if (scan.hasNextInt()) {
            String input = scan.next();
            int parsedInt = Integer.parseInt(input);
            if (parsedInt > 0 && parsedInt <= maxValue) {
                return parsedInt;
            }
        }
        return promptNumeric(s, maxValue);
    }

    public boolean promptBinary(String msg, String accept, String reject) {
        String input = promptText(msg);
        if (input.equalsIgnoreCase(accept)) {
            return true;
        } else if (input.equalsIgnoreCase(reject)) {
            return false;
        } else {
            return promptBinary("Ugyldigt input - prøv igen.\n" + msg, accept, reject);
        }
    }

    public int promptChoice(List<String> inputList, String s) {
        displayList(inputList);
        int maxValue = inputList.size();
        int input = promptNumeric(s, maxValue);
        return input;
    }


}




























    /*

    public int promptNumeric(String msg){
        String input = promptText(msg);         //Give brugere et sted at placere sit svar og vente pÃ¥ svaret
        int number = Integer.parseInt(input);       //Konvertere svaret til et tal
        return number;
    }
    public int promptChoice(ArrayList<String> optionslist, String msg){//["Gin&Tonic", "Beer","Vine" ]
        displayMsg(msg);
        displayList(optionslist, "");
        int input = promptNumeric("");//1
        //  return optionslist.get(input-1);
        return input;

    }

    */
