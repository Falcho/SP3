package util;


import javax.print.attribute.standard.Media;
import java.util.*;

public class TextUI {

    private final Scanner scan = new Scanner(System.in);

    public void displayMsg(String msg) {
        System.out.println(msg);
    }

    public void displayList(List<String> list) {
        int n = 1;
        for (String s : list) {
            System.out.println(n++ + ") " + s);
        }
    }

    public void displayMedia(Map<String, Media> mediaList, int start) {
        for (String s : mediaList.keySet()) {
            System.out.println(s);
        }
    }

    public String promptText(String s) {
        displayMsg(s);
        String input = scan.nextLine();
        return input;
    }

    public int promptNumeric(String s, int maxValue) {
        displayMsg(s);
        if (scan.hasNext()) {
            String input = scan.nextLine();
            try {
                int parsedInt = Integer.parseInt(input);
                if (parsedInt > 0 && parsedInt <= maxValue) {
                    return parsedInt;
                }
                displayMsg("Ugyldigt valg.");
            } catch (NumberFormatException e) {
                System.out.println("input skal være et tal. Prøv igen.");
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
        if (inputList.isEmpty()) {
            return 0;
        }
        int maxValue = inputList.size();
        int input = promptNumeric(s, maxValue);
        return input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextUI textUI = (TextUI) o;
        return Objects.equals(scan, textUI.scan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scan);
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
