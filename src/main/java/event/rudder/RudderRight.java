package event.rudder;

public class RudderRight {

    int degreeRudder;
    public String toString() {
        return "Event: Rudder - RudderRight";
    }
    public RudderRight(int degree) {
        this.degreeRudder=degree; }
    public int getDegree(){ return degreeRudder;}
}
