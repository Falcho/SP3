public class Serie extends Media {
    @Override
    public void play() {
        System.out.println("play");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void restart() {
        System.out.println("restart");
    }

    @Override
    public void skipIntro() {
        System.out.println("skip intro");
    }

    @Override
    public void playPrevious() {
        System.out.println("play previous");
    }

    @Override
    public void playNext() {
        System.out.println("play next");
    }
}
