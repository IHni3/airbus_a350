package event.right_aileron;

public class RightAileronUp {
    int degreeRightAileron;
    public String toString() {
        return "Event: Right_Aileron - RightAileronUp";
    }
    public RightAileronUp(int degree) {
        this.degreeRightAileron=degree; }
    public int getDegree(){ return degreeRightAileron;}
}
