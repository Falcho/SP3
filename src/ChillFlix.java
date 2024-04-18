import util.FileIO;
import util.TextUI;
import java.util.ArrayList;
import java.util.List;

public class ChillFlix {
    TextUI ui;
    FileIO io;
    String userPath;
    String mediaPath;
    List<Media> medialist;
    List<User> userlist;

    ChillFlix(String userPath, String mediaPath){
        ui = new TextUI();
        io = new FileIO();
        this.userPath = userPath;
        this.mediaPath = mediaPath;
        this.medialist = new ArrayList<>();
        this.userlist = new ArrayList<>();

    }

    public void sampleData() {
        medialist.add(new Serie("Naruto", 2004, "awesome", 9.0f));
        userlist.add(new User("Mhaa", "ElskerKage"));
    }

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



}
