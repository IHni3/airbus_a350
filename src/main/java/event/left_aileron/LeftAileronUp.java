package event.left_aileron;

public class LeftAileronUp {
    int degreeLeftAileron;
    public String toString() {
        return "Event: Left_Aileron - LeftAileronUp";
    }
    public LeftAileronUp(int degree) {
       this.degreeLeftAileron=degree; }
       public int getDegree(){ return degreeLeftAileron;}
}
