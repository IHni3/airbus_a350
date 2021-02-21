package event.right_aileron;

public class RightAileronDown {

    int degreeRightAileron;
    public String toString() {
        return "Event: Right_Aileron - RightAileronDown";
    }
    public RightAileronDown(int degree) {
        this.degreeRightAileron=degree; }
    public int getDegree(){ return degreeRightAileron;}

}
