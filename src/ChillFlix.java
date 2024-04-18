import util.FileIO;
import util.TextUI;
import java.util.ArrayList;

public class ChillFlix {
    TextUI ui;
    FileIO io;
    String userPath;
    String mediaPath;
    list<Media> medialist;
    list<User> userlist;



    public void runDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Opret bruger");
        list.add("Login");
        list.add("Luk Chillflix");
        ui.displayMsg("Login menu");
        boolean proceed = true;
        while (proceed) {
            int choice = ui.promptChoice(list, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Opret bruger");
                    break;
                case 2:
                    ui.displayMsg("Login");
                    break;
                case 3:
                    proceed = false;
                    break;
            }
        }
    }

    ChillFlix(String userPath, String mediaPath){

       ui = new TextUI();
       io = new FileIO();
       this.userPath = userPath;
       this.mediaPath = mediaPath;

    }

}
