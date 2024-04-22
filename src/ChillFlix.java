import util.FileIO;
import util.TextUI;

import java.util.*;

import static java.lang.System.exit;

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
        //this.sampleData();
        this.parseMovieData();

        parseUserData();
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
    public void startDialog() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Login");
        actions.add("Opret bruger");
        actions.add("Luk");
        boolean loggedln = false;
        while (!loggedln) {
            int choice = ui.promptChoice(actions, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Login ind");
                    loggedln=loginDialog();
                    break;
                case 2:
                    ui.displayMsg("Create user");
                    createUserDialog(null);
                    break;
                case 3:
                    ui.displayMsg("Quit");
                    exit(0);
                    break;
                case 4:
                    loggedln = false;
                    break;

            }
            if(loggedln) this.mainDialog();
        }
    }
    public void mainDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Vis katagori");
        list.add("Vis farvoritliste");
        list.add("Vis historik");
        list.add("Søg efter titel");
        list.add("Settings");
        ui.displayMsg("Hovedmenu");
        boolean proceed = true;
        while (proceed) {
            int choice = ui.promptChoice(list, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Vis kategorier");
                    this.selectMovieDialog(mediaList);
                    break;
                case 2:
                    ui.displayMsg("Vis favoritliste");
                    this.selectMovieDialog(currentUser.getFavorites());
                    break;
                case 3:
                    ui.displayMsg("Vis historik");
                    this.selectMovieDialog(currentUser.getHistory());
                    break;
                case 4:
                    ui.displayMsg("Søg efter titel");
                    //this.searchDialog();
                    break;
                case 5:
                    ui.displayMsg("Settings");
                    //this.settingsDialog();
                    break;
                case 6:
                    proceed = false;
                    break;
            }
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
        if (username==null) {
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


    public void selectMovieDialog(Map<String, Media>mediaMap){
        List<String> titleList = new ArrayList(mediaMap.keySet());
        int choice = ui.promptChoice(titleList,"Vælg en film fra listen");
        mediaMap.get(titleList.get(choice-1)).mediaDialog(currentUser);
    }

    public void parseUserData(){
        List<String> userDataList = io.readData(userPath);
        for(String userData : userDataList){
            String[] userDataArray = userData.split("\t");
            String userName = userDataArray[0].trim();
            String userPassword = userDataArray[1].trim();
            User user = new User(userName, userPassword);
            for(String movieTitle : userDataArray[2].split(";")){
                if (!movieTitle.trim().equals("")){
                    //user.addFavorite(mediaList.get(movieTitle.trim()));
                }
            }
            for(String movieTitle : userDataArray[3].split(";")){
                if (!movieTitle.trim().equals("")) {
                    //user.addHistory(mediaList.get(movieTitle.trim()));
                }
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

    public void parseMovieData(){
      ArrayList<String>movieList= io.readData(mediaPath);
      for(String title:movieList){
          Movie movie=createMovieFromString(title);
          mediaList.put(movie.getTitle(),movie);
      }

    }

    public Movie createMovieFromString(String movieString) {
       String[] movieData=movieString.split(";");
        String title= movieData[0].trim();
        int releaseYear= Integer.parseInt(movieData[1].trim());
        String genre= movieData[2].trim();
        float rating = Float.parseFloat(movieData[3].replace(",",".").trim());
        Movie movie = new Movie(title,releaseYear,genre,rating,0);
        return movie;
    }

    public void searchDialog(){
        String searchWord = ui.promptText("Søg efter en titel");
        List resultList = mediaList.keySet().stream().filter((title) -> title.contains(searchWord)).toList();
        if(resultList.isEmpty()) {
            System.out.println("Fejlmeddelse");
        }else{
            //selectMovieDialog(resultList);
        }
    }
}
