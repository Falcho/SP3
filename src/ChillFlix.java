import util.FileIO;
import util.TextUI;

import java.util.*;

public class ChillFlix {
    TextUI ui;
    FileIO io;
    String userPath;
    String mediaPath;
    Map<String, Media> mediaList;
    Map<String, User> userList;

    ChillFlix(String userPath, String mediaPath){
        ui = new TextUI();
        io = new FileIO();
        this.userPath = userPath;
        this.mediaPath = mediaPath;
        this.mediaList = new HashMap<>();
        this.userList = new TreeMap<>();

    }

    public void sampleData() {
        mediaList.put("Naruto", new Serie("Naruto", 2004, "awesome", 9.0f));
        mediaList.put("ChainSawMan", new Serie("ChainSawMan", 2009, "Splatter", 8));
        mediaList.put("OnePunchMan", new Serie("OnePunchMan", 2004, "Næver", 9));
        mediaList.put("SwordArtOnline", new Serie("SwordArtOnline", 2006, "Sværd", 7));
        mediaList.put("TheDeadlySevenSins", new Serie("TheDeadlySevenSins", 2003, "Dæmoner", 7));
        userList.put("Mhaa", new User("Mhaa","ElskerKage"));
        userList.put("AndyTheDragon", new User("AndyTheDragon","CodeGenius123"));
        userList.put("Francky",new User("Francky","123BabyDaddy"));
        userList.put("DamBoii", new User("DamBoii","TheBigBoi"));
        userList.put("Falcho",new User("Falcho","MasterPassword"));

    }

    public boolean createUser(){
        String usernameInput = ui.promptText("Indtast nyt brugernavn");
        if(userList.containsKey(usernameInput)) {
            ui.displayMsg("Brugernavn er allerede taget");
            return createUser();
        }else{
            String passwordInput = ui.promptText("Indtast kodeord");
            String repeatPasswordInput = ui.promptText("Gentag kodeord");

            if(!passwordInput.equals(repeatPasswordInput)){
                ui.displayMsg("Kodeord ikke ens - prøv igen");
                //toDo skal refactors til at bruge resetPassword()
            }else {
                User user = new User(usernameInput, passwordInput);
                userList.put(usernameInput, user);
            }
        } return loginDialog();
    }


    public boolean loginDialog() {
        String username = ui.promptText("Indtast brugernavn");
        String password = ui.promptText("Indtast kodeord");
        if (!userList.containsKey(username) || !userList.get(username).getPassword().equals(password)) {
            ui.displayMsg("brugernavn/kodeord kombination eksisterer ikke");
            return loginDialog();
        } else  {
            return true;
        }
        return true;
    }

    public boolean checkIfUserExists(String username, String msg){

        if(userList.containsKey(username)){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChillFlix chillFlix = (ChillFlix) o;
        return Objects.equals(ui, chillFlix.ui) && Objects.equals(io, chillFlix.io) && Objects.equals(userPath, chillFlix.userPath) && Objects.equals(mediaPath, chillFlix.mediaPath) && Objects.equals(mediaList, chillFlix.mediaList) && Objects.equals(userList, chillFlix.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ui, io, userPath, mediaPath, mediaList, userList);
    }
}
