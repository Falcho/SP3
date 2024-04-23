import util.TextUI;

import java.util.*;

public class User {


    private String userName;
    private String userPassword;
    private Map<String, Media> favorites;
    private Map<String, Media> history;

    TextUI ui = new TextUI();

    public User(String name, String password) {
        this.userName = name;
        this.userPassword = password;
        this.favorites = new LinkedHashMap<String, Media>();
        this.history = new LinkedHashMap<String, Media>();
    }


    public boolean checkPassword(String pwd) {
        return this.getPassword().equals(pwd);
    }

    public void resetPassword() {
        System.out.println("resetPassword()");
    }

    public void changePassword() {
        System.out.println("changePassword()");
    }

    public void addFavorite(Media media) {
        favorites.put(media.getTitle(), media);
    }

    public void removeFavorite(Media media) {
        favorites.remove(media.getTitle());
    }
    public void toggleFavorite(Media media) {
        if (isFavorite(media)) {
            ui.displayMsg(media.getTitle() + " er fjernet fra favoritlisten");
            removeFavorite(media);
        } else {
            addFavorite(media);
            ui.displayMsg(media.getTitle() + " er tilføjet til favoritlisten");
        }
    }

    public boolean isFavorite(Media media) {
        return favorites.containsKey(media.getTitle());
    }

    public void addHistory(Media media) {
        history.put(media.getTitle(), media);
    }

    public void resetHistory() {
        history.clear();
    }


    public Map<String, Media> getFavorites() {
        return favorites;
    }

    public Map<String, Media> getHistory() {
        return history;
    }

    public String getPassword() {
        return userPassword;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userName, user.userName) && Objects.equals(userPassword, user.userPassword) && Objects.equals(favorites, user.favorites) && Objects.equals(history, user.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword, favorites, history);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(userName+'\t'+userPassword+'\t');
        sb.append("[");
        for(String movie: favorites.keySet()) {
            sb.append(movie).append(";");
        }
        sb.append("]").append('\t').append("[");
        for(String movie: history.keySet()) {
            sb.append(movie).append(";");
        }
        sb.append("]");
        return sb.toString();
    }


}
