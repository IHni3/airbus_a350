package event.left_aileron;

public class LeftAileronDown {

    int degreeLeftAileron;
    public String toString() {
        return "Event: Left_Aileron - LeftAileronDown";
    }
    public LeftAileronDown(int degree) {
        this.degreeLeftAileron=degree; }
    public int getDegree(){ return degreeLeftAileron;}

}
