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
    User currentUser;

    ChillFlix(String userPath, String mediaPath) {
        ui = new TextUI();
        io = new FileIO();
        this.userPath = userPath;
        this.mediaPath = mediaPath;
        this.mediaList = new TreeMap<>();
        this.userList = new HashMap<>();

    }

    public void sampleData() {
        mediaList.put("Naruto", new Serie("Naruto", 2004, "awesome", 9.0f));
        mediaList.put("Chain Saw Man", new Serie("Chain Saw Man", 2009, "Splatter", 8));
        mediaList.put("One Punch Man", new Serie("One Punch Man", 2004, "Næver", 9));
        mediaList.put("SwordArt Online", new Serie("SwordArt Online", 2006, "Sværd", 7));
        mediaList.put("The Deadly Seven Sins", new Serie("The Deadly Seven Sins", 2003, "Dæmoner", 7));
        userList.put("Mhaa", new User("Mhaa", "ElskerKage"));
        userList.put("AndyTheDragon", new User("AndyTheDragon", "CodeGenius123"));
        userList.put("Francky", new User("Francky", "123BabyDaddy"));
        userList.put("DamBoii", new User("DamBoii", "TheBigBoi"));
        userList.put("Falcho", new User("Falcho", "MasterPassword"));

    }

    public boolean checkIfUserExists(String username, String msg) {
        if (userList.containsKey(username)) {
            ui.displayMsg(msg);
            return true;
        } else {
            return false;
        }
    }


    public boolean loginDialog() {
        String username = ui.promptText("Indtast brugernavn");
        String password = ui.promptText("Indtast kodeord");
        if (!checkIfUserExists(username, "") || !userList.get(username).checkPassword(password)) {
            ui.displayMsg("brugernavn/kodeord kombination eksisterer ikke");
            return false;
        }
        this.currentUser = userList.get(username);
        return true;
    }




    public boolean createUserDialog(String username) {
        String usernameInput;
        if (username!=null) {
            usernameInput = ui.promptText("Indtast brugernavn. Dette må ikke være tomt");
            if (usernameInput.equalsIgnoreCase("") || checkIfUserExists(usernameInput, "Brugernavnet er enten taget, eller tomt - prøv igen.")) {
                return createUserDialog(null);
            }
        }
        else {
            usernameInput = username;
        }
        String passwordInput = ui.promptText("Indtast kodeord");
        String repeatPasswordInput = ui.promptText("Gentag kodeord");
        if (!passwordInput.equals(repeatPasswordInput)) {
            return createUserDialog(usernameInput);
        } else {
            User user = new User(usernameInput, passwordInput);
            userList.put(usernameInput, user);
            io.saveData("username\tpassword\t[favorites]\t[history]", new ArrayList<Object>(userList.values()), userPath);
            ui.displayMsg("Bruger oprettet.");
            return true;

        }
    }


    public void runDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1. Opret bruger");
        list.add("2. Login");
        list.add("3. Luk Chillflix");
        ui.displayMsg("Login menu");
        boolean proceed = true;
        while (proceed) {
            int choice = ui.promptChoice(list, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Opret bruger");
                    this.createUserDialog(null);
                    break;
                case 2:
                    ui.displayMsg("Login");
                    this.loginDialog();
                    break;
                case 3:
                    proceed = false;
                    break;
            }
        }
    }
    public void selectMovieDialog(Map<String, Media>mediaMap){
        List<String> titleList = new ArrayList(mediaMap.keySet());
        int choice = ui.promptChoice(titleList,"Vælg en title fra listen");
        mediaMap.get(titleList.get(choice)).display();
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
