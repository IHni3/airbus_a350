package event.rudder;

public class RudderLeft {
    int degreeRudder;
    public String toString() {
        return "Event: Rudder - RudderLeft";
    }
    public RudderLeft(int degree) {
        this.degreeRudder=degree; }
    public int getDegree(){ return degreeRudder;}
}
