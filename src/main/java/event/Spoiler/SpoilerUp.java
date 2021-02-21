package event.Spoiler;

public class SpoilerUp {

    int degreeSpoiler;

    public SpoilerUp(int degree) {
        this.degreeSpoiler = degree;
    }

    public String toString() {
        return "Event: Spoiler - SpoilerUp";
    }

    public int getDegreeSpoiler() {
        return degreeSpoiler;
    }
}
