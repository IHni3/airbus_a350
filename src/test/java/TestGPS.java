import base.PrimaryFlightDisplay;
import factory.GpsFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGPS {
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
        componentPort = GpsFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = GpsFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            assertNotNull(connectMethod);

            Method sendMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            assertNotNull(sendMethod);

            Method receiveMethod = componentPort.getClass().getDeclaredMethod("receive");
            assertNotNull(receiveMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = GpsFactory.build();
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
    public void connect() {
        componentPort = GpsFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "asia-5");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void send() {
        componentPort = GpsFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            assertDoesNotThrow(() -> scanMethod.invoke(componentPort, "requestData"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void receive() {
        componentPort = GpsFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("receive");
            String data = (String) offMethod.invoke(componentPort);
            assertEquals("GPS DATA EXAMPLE", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void off() {
        componentPort = GpsFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void startup() {
        componentPort = GpsFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isGPSOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isGPSOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "asia-5");
            PrimaryFlightDisplay.instance.isGPSConnected = result;
            assertTrue(PrimaryFlightDisplay.instance.isGPSConnected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void otherPhases() {
        componentPort = GpsFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            assertDoesNotThrow(() -> scanMethod.invoke(componentPort, "requestData"));
            Method offMethod = componentPort.getClass().getDeclaredMethod("receive");
            String data = (String) offMethod.invoke(componentPort);
            assertEquals("GPS DATA EXAMPLE", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void shutdown() {
        componentPort = GpsFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isGPSOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isGPSOn);
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