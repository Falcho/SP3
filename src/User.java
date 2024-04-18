import java.util.List;

public class User {


    private String userName;
    private String userPassword;
    private List<Media> favorites;
    private List<Media> history;


    public User(String name,String password){
        this.userName= name;
        this.userPassword = password;
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


}
