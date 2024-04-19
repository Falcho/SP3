import java.util.ArrayList;
import java.util.List;

public class User {


    private String userName;
    private String userPassword;
    private Map<String, Media> favorites;
    private Map<String, Media> history;



    public User(String name,String password){
        this.userName= name;
        this.userPassword = password;
        this.favorites= new ArrayList<>();
        this.history= new ArrayList<>();
    }

    public void userLogin(){
        System.out.println("userLogin()");
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

    public void addFavorite(){
        System.out.println("addFavorite()");
    }

    public void removeFavorite(){
        System.out.println("removeFavorite()");
    }

    public void addHistory(){
        System.out.println("addHistory()");
    }
    public void resetHistory(){
        System.out.println("resetHistory()");
    }

    public void showSettings(){
        System.out.println("showSettings()");
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
}
