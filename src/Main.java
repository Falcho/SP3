public class Main {
    public static void main(String[] args) {
        ChillFlix chillFlix = new ChillFlix("data/users.txt","data/film.txt");
        chillFlix.startDialog();
    }
}
