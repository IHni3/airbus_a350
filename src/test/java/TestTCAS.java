import base.PrimaryFlightDisplay;
import factory.TcasFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTCAS {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory() {
        componentPort = TcasFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TcasFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            assertNotNull(connectMethod);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);

            Method determineAltitude = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            assertNotNull(determineAltitude);

            Method setAltitude = componentPort.getClass().getDeclaredMethod("setAltitude", Integer.TYPE);
            assertNotNull(setAltitude);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = TcasFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void off() {
        componentPort = TcasFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void connect() {
        componentPort = TcasFactory.build();
        try {
            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean isConnected = (boolean) connectMethod.invoke(componentPort, "110.9");
            assertTrue(isConnected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void scan() {
        componentPort = TcasFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean planeDetected = (boolean) scanMethod.invoke(componentPort, "clouds");
            assertFalse(planeDetected);

            planeDetected = (boolean) scanMethod.invoke(componentPort, "plane");
            assertTrue(planeDetected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void alarm() {
        componentPort = TcasFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isAlarm = (boolean) alarmMethod.invoke(componentPort);
            assertTrue(isAlarm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void determineAltitude() {
        componentPort = TcasFactory.build();
        try {
            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitudeMethod.invoke(componentPort, "alt=578m");
            assertEquals(578, altitude);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void setAltitude() {
        componentPort = TcasFactory.build();
        try {
            Method setAltitudeMethod = componentPort.getClass().getDeclaredMethod("setAltitude", Integer.TYPE);
            int altitude = (int) setAltitudeMethod.invoke(componentPort, 430);
            assertEquals(430, altitude);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void startup() {
        componentPort = TcasFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);

            PrimaryFlightDisplay.instance.isTCASOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isTCASOn);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean isConnected = (boolean) connectMethod.invoke(componentPort, "110.9");

            PrimaryFlightDisplay.instance.isTCASConnected = isConnected;
            assertTrue(PrimaryFlightDisplay.instance.isTCASConnected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(11)
    public void takeoffAndLanding() {
        componentPort = TcasFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean planeDetected = (boolean) scanMethod.invoke(componentPort, "trees");

            PrimaryFlightDisplay.instance.isTCASAlarm = planeDetected;
            assertFalse(PrimaryFlightDisplay.instance.isTCASAlarm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(12)
    public void climbingAndDescent() {
        componentPort = TcasFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean planeDetected = (boolean) scanMethod.invoke(componentPort, "clouds");

            PrimaryFlightDisplay.instance.isTCASAlarm = planeDetected;
            assertFalse(PrimaryFlightDisplay.instance.isTCASAlarm);

            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitudeMethod.invoke(componentPort, "alt=400m");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(400, PrimaryFlightDisplay.instance.altitudeTCAS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(13)
    public void turning() {
        componentPort = TcasFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean planeDetected = (boolean) scanMethod.invoke(componentPort, "plain sky");

            PrimaryFlightDisplay.instance.isTCASAlarm = planeDetected;
            assertFalse(PrimaryFlightDisplay.instance.isTCASAlarm);

            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitudeMethod.invoke(componentPort, "alt=1000m");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(1000, PrimaryFlightDisplay.instance.altitudeTCAS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}