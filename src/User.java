import util.TextUI;

import java.util.*;

public class User {


    private String userName;
    private String userPassword;
    private Map<String, Media> favorites;
    private Map<String, Media> history;

TextUI ui= new TextUI();

    public User(String name,String password){
        this.userName= name;
        this.userPassword = password;
        this.favorites= new LinkedHashMap<String, Media>();
        this.history= new LinkedHashMap<String, Media>();
    }


    public boolean checkPassword(String pwd){
        return this.getPassword().equals(pwd);
    }

    public void resetPassword(){
        System.out.println("resetPassword()");
    }

    public void changePassword(){
        System.out.println("changePassword()");
    }
    public void changeUserName(){
        System.out.println("changeUserName()");
    }
    public void displayFavorites(){
        System.out.println("displayFavorites()");
    }
    public void displayHistory(){
        System.out.println("displayHistory()");
    }

    public void addFavorite(Media media){
        favorites.put(media.getTitle(), media);
        ui.displayMsg(media.getTitle() +" tilføjet til dine favoritter");
    }
    public void removeFavorite(Media media){
        favorites.remove(media);
    }

    public void addHistory(Media media){
        System.out.println("addHistory()");
    }
    public void resetHistory(){
        System.out.println("resetHistory()");
    }

    public void showSettings(){
        System.out.println("showSettings()");
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
        return  userName + '\t' + userPassword + '\t'  + favorites + '\t'  + history ;
    }




}
