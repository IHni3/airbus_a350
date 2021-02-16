package event.slat;

public class SlatUp {

    int degree;

    public SlatUp(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Slat - SlatDown";
    }

    public int getDegree() {
        return degree;
    }
}
