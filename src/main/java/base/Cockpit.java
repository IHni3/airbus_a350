package base;

import logging.LogEngine;
import recorder.FlightRecorder;

public class Cockpit implements ICockpit {
    private Airplane airplane;

    public Cockpit(Airplane airplane) {
        this.airplane = airplane;
    }

    public void startup() {
        System.out.println("--- startup");
        LogEngine.instance.write("*** Cockpit.startup ***");
        FlightRecorder.instance.insert("Cockpit", "startup");
        airplane.startup();
        LogEngine.instance.write("");
    }

    public void taxi() {
        System.out.println("--- taxi");
        LogEngine.instance.write("*** Cockpit.taxi ***");
        FlightRecorder.instance.insert("Cockpit", "taxi");
        airplane.taxi();
        LogEngine.instance.write("");
    }

    public void takeoff() {
        System.out.println("--- takeoff");
        LogEngine.instance.write("*** Cockpit.takeoff ***");
        FlightRecorder.instance.insert("Cockpit", "takeoff");
        airplane.takeoff();
        LogEngine.instance.write("");
    }

    public void climbing() {
        System.out.println("--- climbing");
        LogEngine.instance.write("*** Cockpit.climbing ***");
        FlightRecorder.instance.insert("Cockpit", "climbing");
        airplane.climbing();
        LogEngine.instance.write("");
    }

    public void rightTurn() {
        System.out.println("--- rightTurn");
        LogEngine.instance.write("*** Cockpit.rightTurn ***");
        FlightRecorder.instance.insert("Cockpit", "rightTurn");
        airplane.rightTurn();
        LogEngine.instance.write("");
    }

    public void leftTurn() {
        System.out.println("--- leftTurn");
        LogEngine.instance.write("*** Cockpit.leftTurn ***");
        FlightRecorder.instance.insert("Cockpit", "leftTurn");
        airplane.leftTurn();
        LogEngine.instance.write("");
    }

    public void descent() {
        System.out.println("--- descent");
        LogEngine.instance.write("*** Cockpit.descent ***");
        FlightRecorder.instance.insert("Cockpit", "descent");
        airplane.descent();
        LogEngine.instance.write("");
    }

    public void landing() {
        System.out.println("--- landing");
        LogEngine.instance.write("*** Cockpit.landing ***");
        FlightRecorder.instance.insert("Cockpit", "landing");
        airplane.landing();
        LogEngine.instance.write("");
    }

    public void shutdown() {
        System.out.println("--- shutdown");
        LogEngine.instance.write("*** Cockpit.shutdown ***");
        FlightRecorder.instance.insert("Cockpit", "shutdown");
        airplane.shutdown();
        LogEngine.instance.write("");
    }

    public void startSimulation() {
        startup();
        taxi();
        takeoff();
        climbing();
        rightTurn();
        leftTurn();
        descent();
        landing();
        shutdown();
    }
}