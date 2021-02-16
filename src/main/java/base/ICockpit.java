package base;

public interface ICockpit {
    void startup();

    void taxi();

    void takeoff();

    void climbing();

    void rightTurn();

    void leftTurn();

    void descent();

    void landing();

    void shutdown();

    void startSimulation();
}