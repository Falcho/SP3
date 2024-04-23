import java.awt.*;
import java.util.*;
import java.util.List;

public class Serie extends Media {
    Map<String, Map<String, Media>> seasonMap;



    Serie(String title, int releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
        this.seasonMap = new TreeMap<String, Map<String, Media>>();
    }
    public Map<String, Map<String, Media>> getSeasonMap() {
        return seasonMap;
    }

    @Override
    public void playPrevious() {
        System.out.println("play previous");
    }

    @Override
    public void playNext() {
        System.out.println("play next");
    }

    public void mediaDialog(User currentUser){
        ArrayList<String>actions= new ArrayList<>();
        List seasonList = new ArrayList<>(this.seasonMap.keySet());
        actions.addAll(seasonList);
        int choice = 0;
       while (choice<actions.size()){
           choice = ui.promptChoice(actions, "");
       }
       switch (choice) {
           case 1:
               this.play();
               break;
           default:
               ChillFlix.selectMovieDialog(this.seasonMap.get(seasonList.get(choice)));
               break;
       }
    }

}
