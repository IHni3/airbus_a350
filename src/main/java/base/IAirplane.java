package base;

import event.Subscriber;

public interface IAirplane {
    void addSubscriber(Subscriber subscriber);

    void build();

    void startup();

    void taxi();

    void takeoff();

    void climbing();

    void rightTurn();

    void leftTurn();

    void descent();

    void landing();

    void shutdown();
}