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

    public boolean createUser(){
        String usernameInput = ui.promptText("Indtast nyt brugernavn");
        if(userlist.contains(usernameInput)) {
            ui.displayMsg("Brugernavn er allerede taget");
            return createUser();
        }else{
            String passwordInput = ui.promptText("Indtast kodeord");
            String repeatPasswordInput = ui.promptText("Gentag kodeord");

            if(!passwordInput.equals(repeatPasswordInput)){
                ui.displayMsg("Kodeord ikke ens - prøv igen");
                //toDo skal refactors til at bruge resetPassword()
            }else if(passwordInput.equals(repeatPasswordInput)){
                User user = new User(usernameInput, passwordInput);
                userlist.add(user);
            }
        } return loginDialog();
    }


    public boolean loginDialog() {
        String username = ui.promptText("Indtast brugernavn");
        String password = ui.promptText("Indtast kodeord");
        if (!userlist.contains(username) || !userlist.contains(password)) {
            ui.displayMsg("brugernavn/kodeord kombination eksisterer ikke");
            return loginDialog();
        } else if (userlist.contains(username) && userlist.contains(password)) {
            return true;
        }
        return true;
    }

    public boolean checkIfUserExists(String username, String msg){

        if(userlist.contains(username)){
            ui.displayMsg(msg);
            return true;
        }else{
            return false;
        }
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
