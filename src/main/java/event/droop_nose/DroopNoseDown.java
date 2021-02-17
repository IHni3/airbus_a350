package event.droop_nose;

public class DroopNoseDown {
    private int degree;

    public DroopNoseDown(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: DroopNose - Down degrees";
    }
}
