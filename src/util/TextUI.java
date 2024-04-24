package util;

import java.util.*;

public class TextUI {

    private final Scanner scan = new Scanner(System.in);

    public void displayMsg(String msg) {
        if (!msg.isEmpty()) System.out.println(msg);
    }

    public void displayList(List<String> list) {
        int n = 1;
        for (String s : list) {
            System.out.println(n++ + ") " + s);
        }
    }

    public String promptText(String s) {
        displayMsg(s);
        return scan.nextLine();
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
                displayMsg("Ugyldigt valg. Prøv igen.");
            } catch (NumberFormatException e) {
                System.out.println("Input skal være et tal. Prøv igen.");
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
        return promptNumeric(s, maxValue);
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