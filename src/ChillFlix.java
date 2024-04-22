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
        this.mediaList = new TreeMap<>();
        this.userList = new HashMap<>();
        parseUserData();
    }

    public void sampleData() {
        mediaList.put("Naruto", new Serie("Naruto", 2004, "awesome", 9.0f));
        mediaList.put("Chain Saw Man", new Serie("Chain Saw Man", 2009, "Splatter", 8));
        mediaList.put("One Punch Man", new Serie("One Punch Man", 2004, "Næver", 9));
        mediaList.put("SwordArt Online", new Serie("SwordArt Online", 2006, "Sværd", 7));
        mediaList.put("The Deadly Seven Sins", new Serie("The Deadly Seven Sins", 2003, "Dæmoner", 7));
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
        ui.displayMsg("Du har indtastet et brugernavn.");
        String password = ui.promptText("Indtast kodeord");
        if (!userList.containsKey(username) || !userList.get(username).getPassword().equals(password)) {
            ui.displayMsg("brugernavn/kodeord kombination eksisterer ikke");
            return loginDialog();
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
                    this.createUser();
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

    public void parseUserData(){
        List<String> userDataList = io.readData(userPath);
        for(String userData : userDataList){
            String[] userDataArray = userData.split("\t");
            String userName = userDataArray[0].trim();
            String userPassword = userDataArray[1].trim();
            User user = new User(userName, userPassword);
            for(String movieTitle : userDataArray[2].split(";")){
                user.addFavorite(mediaList.get(movieTitle.trim()));
            }
            for(String movieTitle : userDataArray[3].split(";")){
                user.addHistory(mediaList.get(movieTitle.trim()));
            }
            userList.put(userName,user);
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
