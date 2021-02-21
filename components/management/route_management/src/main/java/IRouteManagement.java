import domains.CheckPoint;

public interface IRouteManagement {
    String version();

    boolean on();
    int add(CheckPoint checkpoint);
    int remove(CheckPoint checkPoint);
    void printCheckPoints();
    int setCostIndex(int value); //change beacause i dont know why it is returning a double value
    boolean off();
}