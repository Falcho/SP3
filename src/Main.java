public class Main {
    public static void main(String[] args) {
        ChillFlix chillFlix = new ChillFlix("data/users.csv","data/film.txt");
        chillFlix.startDialog();
    }
}
