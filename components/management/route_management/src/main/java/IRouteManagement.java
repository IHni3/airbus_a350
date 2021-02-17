public interface IRouteManagement {
    String version();

    boolean on();
    int add(CheckPoint checkpoint);
    int remove(CheckPoint checkPoint);
    void printCheckPoints();
    double setCostIndex(int value);
    boolean off();
}