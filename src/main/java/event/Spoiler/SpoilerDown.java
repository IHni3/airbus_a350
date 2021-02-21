package event.Spoiler;

public class SpoilerDown {

    int degreeSpoiler;

    public SpoilerDown(int degree) {
        this.degreeSpoiler = degree;
    }

    public String toString() {
        return "Event: Spoiler - SpoilerDown";
    }

    public int getDegreeSpoiler() {
        return degreeSpoiler;
    }
}
