package event.droop_nose;

public class DroopNoseUp {
    private int degree;

    public DroopNoseUp(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: DroopNose - Up degrees";
    }
}
