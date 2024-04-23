import util.FileIO;
import util.TextUI;

import java.util.*;

import static java.lang.System.exit;

public class ChillFlix {
    TextUI ui;
    private final FileIO io;
    private final String userPath;
    private final String moviePath;
    private final String seriePath;
    Map<String, Media> mediaList;
    Map<String, User> userList;
    Map<String, Map<String, Media>> genreMap;
    User currentUser;


    ChillFlix(String userPath, String moviePath, String seriePath) {
        ui = new TextUI();
        io = new FileIO();
        this.userPath = userPath;
        this.moviePath = moviePath;
        this.seriePath = seriePath;
        this.mediaList = new TreeMap<>();
        this.userList = new HashMap<>();
        this.genreMap = new TreeMap<>();
        this.parseMovieData();
        this.parseSerieData();
        this.parseUserData();
    }

    public boolean checkIfUserExists(String username, String msg) {
        if (userList.containsKey(username)) {
            ui.displayMsg(msg);
            return true;
        } else {
            return false;
        }
    }
    private void saveUserData() {
        io.saveData("username\tpassword\t[favorites]\t[history]", new ArrayList<>(userList.values()), userPath);
    }


    public void startDialog() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Log ind");
        actions.add("Opret bruger");
        actions.add("Luk");
        boolean loggedln = false;
        while (!loggedln) {
            int choice = ui.promptChoice(actions, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Log ind");
                    loggedln=loginDialog();
                    break;
                case 2:
                    ui.displayMsg("Opret Bruger");
                    createUserDialog(null);
                    break;
                case 3:
                    ui.displayMsg("Luk");
                    exit(0);
                    break;

            }
            if (loggedln) this.mainDialog();
        }
    }

    public void mainDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Vis katagori");
        list.add("Vis farvoritliste");
        list.add("Vis historik");
        list.add("Søg efter titel");
        list.add("Settings");
        list.add("Log ud");
        ui.displayMsg("Hovedmenu");
        boolean proceed = true;
        while (proceed) {
            int choice = ui.promptChoice(list, "Vælg en handling");
            switch (choice) {
                case 1:
                    ui.displayMsg("Kategorier");
                    this.selectGenreDialog();
                    break;
                case 2:
                    if (currentUser.getFavorites().isEmpty()) {
                        ui.displayMsg("-----------------");
                        ui.displayMsg("Favoritlisten er tom");
                        ui.displayMsg("-----------------");
                    } else {
                        ui.displayMsg("Din favoritliste");
                        this.selectMovieDialog(currentUser.getFavorites());
                    }
                    break;
                case 3:
                    if(currentUser.getHistory().isEmpty()) {
                        ui.displayMsg("-----------------");
                        ui.displayMsg("Historik er tom");
                        ui.displayMsg("-----------------");
                    } else {
                        ui.displayMsg("Din historik");
                        this.selectMovieDialog(currentUser.getHistory());
                    }
                    break;
                case 4:
                    this.searchDialog();
                    break;
                case 5:
                    ui.displayMsg("Indstillinger");
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
        if (username == null) {
            usernameInput = ui.promptText("Indtast brugernavn. Dette må ikke være tomt");
            if (usernameInput.equalsIgnoreCase("") || checkIfUserExists(usernameInput, "Brugernavnet er enten taget, eller tomt - prøv igen.")) {
                return createUserDialog(null);
            }
        } else {
            usernameInput = username;
        }
        String passwordInput = ui.promptText("Indtast kodeord");
        String repeatPasswordInput = ui.promptText("Gentag kodeord");
        if (!passwordInput.equals(repeatPasswordInput)) {
            ui.displayMsg("Kodeordene skal være ens");
            return createUserDialog(usernameInput);
        } else {
            User user = new User(usernameInput, passwordInput);
            userList.put(usernameInput, user);
            this.saveUserData();
            ui.displayMsg("Bruger oprettet.");
            return true;

        }
    }


    public void selectGenreDialog() {
        List<String> genreList = new ArrayList<>(genreMap.keySet());
        if (!genreList.isEmpty()) {
            int choice = ui.promptChoice(genreList, "Vælg en genre fra listen");
            selectMovieDialog(genreMap.get(genreList.get(choice - 1)));
        } else {
            ui.displayMsg("Der er ingen genredata i systemet.");
        }
    }


    public void selectMovieDialog(Map<String, Media> mediaMap) {
        if (!mediaMap.isEmpty()) {
            List<String> titleList = new ArrayList<>(mediaMap.keySet());
            //Få antal elementer i listen
            //TODO Tilføj "Tilbage" til listen
            int choice = ui.promptChoice(titleList, "Vælg fra listen");
            //Hvis du har valgt den sidste mulighed i listen (en mulighed som er større end antal elementer i listen
            //Gør ingenting
            //Ellers :
            Media chosenMedia = mediaMap.get(titleList.get(choice - 1));
            if (chosenMedia instanceof Serie) {
                this.serieDialog(chosenMedia);
            } else {
                this.mediaDialog(chosenMedia);
            }
        }
    }

    private void serieDialog(Media media) {
        List<String> seasonList = new ArrayList<>(((Serie)media).getSeasonMap().keySet());
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Afspil Serie fra starten");
        if (currentUser.isFavorite(media)) {
            actions.add("Fjern fra favoritter");
        } else {
            actions.add("Tilføj til favoritter");
        }
        actions.addAll(seasonList);
        actions.add("Tilbage");


        int choice = 0;
        while (choice < actions.size()-1) {
            ui.displayMsg("Du har valgt: " + media.getTitle());
            choice = ui.promptChoice(actions, "");
            switch (choice) {
                case 1:
                    media.play();
                    currentUser.addHistory(media);
                    this.saveUserData();
                    break;
                case 2:
                   currentUser.toggleFavorite(media);
                    this.saveUserData();
                    break;
                default:
                    //selectMovieDialog(((Serie) media).getSeasonMap().getOrDefault(seasonList.get(choice-2), "Sæson 1"));
                    if (choice-3<seasonList.size()) {
                        selectMovieDialog(((Serie) media).getSeasonMap().get(seasonList.get(choice - 3)));
                    }
            }
        }
    }

    public void mediaDialog(Media media) {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Afspil");
        if (currentUser.isFavorite(media)) {
            actions.add("Fjern fra favoritter");
        } else {
            actions.add("Tilføj til favoritter");
        }
        actions.add("Tilbage");

        int choice = 0;
        while (choice < 3) {
            ui.displayMsg("Du har valgt: " + media.getTitle());
            choice = ui.promptChoice(actions, "");
            switch (choice) {
                case 1:
                    media.play();
                    currentUser.addHistory(media);
                    this.saveUserData();
                    break;
                case 2:
                    boolean toggle = currentUser.toggleFavorite(media);
                    actions.set(1, (toggle ? "Fjern fra" : "Tilføj til") + " favoritter");
                    this.saveUserData();
                    break;
            }
        }
    }

    public void searchDialog() {
        String searchWord = ui.promptText("Søg efter en titel").toLowerCase();
        List<String> resultList = mediaList.keySet().stream().filter((title) -> title.toLowerCase().contains(searchWord)).toList();
        if (resultList.isEmpty()) {
            ui.displayMsg("Din søgning gav ingen resultater");
        } else {
            Map<String, Media> resultMap = new TreeMap<>();
            for (String title : resultList) {
                if (!(mediaList.get(title) instanceof Episode)) {
                    resultMap.put(title, mediaList.get(title));
                }
            }
            selectMovieDialog(resultMap);
        }
    }

    public void parseUserData() {
        List<String> userDataList = io.readData(userPath);
        for (String userData : userDataList) {
            String[] userDataArray = userData.split("\t");
            String userName = userDataArray[0].trim();
            String userPassword = userDataArray[1].trim();
            String favorites = userDataArray[2].replace("[","").replace("]","").trim();
            String history = userDataArray[3].replace("[","").replace("]","").trim();
            User user = new User(userName, userPassword);
            if (favorites.contains(";")) {
                for (String movieTitle : favorites.split(";")) {
                    user.addFavorite(mediaList.get(movieTitle.trim()));
                }
            }
            if (history.contains(";")) {
                for (String movieTitle : history.split(";")) {
                    user.addHistory(mediaList.get(movieTitle.trim()));
                }
            }
            userList.put(userName, user);
        }


    }

    public void parseSerieData() {
        ArrayList<String> serieList = io.readData(seriePath);
        for (String title : serieList) {
            Serie serie = createSerieFromString(title);
            mediaList.put(serie.getTitle(), serie);
            addGenre(serie);
        }
    }


    public void parseMovieData() {
        ArrayList<String> movieList = io.readData(moviePath);
        for (String title : movieList) {
            Movie movie = createMovieFromString(title);
            mediaList.put(movie.getTitle(), movie);
            addGenre(movie);
        }
    }

    public void addGenre(Media movie) {
        ArrayList<String> genreList = new ArrayList<>(List.of(movie.getGenre().split(",")));
        for (String genre : genreList) {
            if (!genreMap.containsKey(genre.trim())) {
                genreMap.put(genre.trim(), new TreeMap<>());
            }
            genreMap.get(genre.trim()).put(movie.getTitle(), movie);

        }

    }

    public Serie createSerieFromString(String serieString) {
        String[] serieData = serieString.split(";");
        String title = serieData[0].trim();
        String years = serieData[1].trim(); //1991-1992
        int startYear = Integer.parseInt(years.split("-")[0]);
        //int endYear= Integer.parseInt(years.split("-")[1]);

        String genre = serieData[2].trim();
        float rating = Float.parseFloat(serieData[3].replace(",", ".").trim());
        String seasonEpisodeString = serieData[4].trim();
        String[] seasonEpisodeList = seasonEpisodeString.split(",");
        Serie serie = new Serie(title, startYear, genre, rating);
        int numberOfSeasons = seasonEpisodeList.length;
        for (String seasonEpisode : seasonEpisodeList) {
            int seasonNumber = Integer.parseInt(seasonEpisode.split("-")[0].trim());
            int episodesInSeason = Integer.parseInt(seasonEpisode.split("-")[1].trim());
            StringBuilder seasonTitle = new StringBuilder(title + " sæson ");
            seasonTitle.append((numberOfSeasons>10 && seasonNumber<10) ? "0" : "").append(seasonNumber);
            serie.getSeasonMap().put(seasonTitle.toString(), new TreeMap<>());
            for (int i = 1; i <= episodesInSeason; i++) {
                StringBuilder episodeTitle = new StringBuilder(title);
                episodeTitle.append(" S").append(seasonNumber<10 ? "0" : "").append(seasonNumber);
                episodeTitle.append("E").append(i<10 ? "0" : "").append(i);
                Episode ep = new Episode(episodeTitle.toString(), startYear, genre, rating, 0);
                serie.getSeasonMap().get(seasonTitle.toString()).put(ep.getTitle(), ep);
                mediaList.put(ep.getTitle(), ep);
            }
        }
        return serie;
    }

    public Movie createMovieFromString(String movieString) {
        String[] movieData = movieString.split(";");
        String title = movieData[0].trim();
        int releaseYear = Integer.parseInt(movieData[1].trim());
        String genre = movieData[2].trim();
        float rating = Float.parseFloat(movieData[3].replace(",", ".").trim());
        return new Movie(title, releaseYear, genre, rating, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChillFlix chillFlix = (ChillFlix) o;
        return Objects.equals(ui, chillFlix.ui) && Objects.equals(io, chillFlix.io) && Objects.equals(userPath, chillFlix.userPath) && Objects.equals(moviePath, chillFlix.moviePath) && Objects.equals(mediaList, chillFlix.mediaList) && Objects.equals(userList, chillFlix.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ui, io, userPath, moviePath, mediaList, userList);
    }

}
