import base.PrimaryFlightDisplay;
import factory.CameraFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCamera {
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
        componentPort = CameraFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = CameraFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("setType", String.class);
            assertNotNull(scanMethod);

            Method recordMethod = componentPort.getClass().getDeclaredMethod("record");
            assertNotNull(recordMethod);

            Method zoomInMethod = componentPort.getClass().getDeclaredMethod("zoomIn", Double.TYPE);
            assertNotNull(zoomInMethod);

            Method zoomOutMethod = componentPort.getClass().getDeclaredMethod("zoomOut", Double.TYPE);
            assertNotNull(zoomOutMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = CameraFactory.build();
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
    public void record() {
        componentPort = CameraFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("record");
            String result = (String) scanMethod.invoke(componentPort);
            assertEquals("Clouds", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void zoomIn() {
        componentPort = CameraFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("zoomIn", Double.TYPE);
            String result = (String) scanMethod.invoke(componentPort, 2.0);
            assertEquals("2.0x scaled \"Clouds\"", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void zoomOut() {
        componentPort = CameraFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("zoomOut", Double.TYPE);
            String result = (String) scanMethod.invoke(componentPort, 0.5);
            assertEquals("0.5x scaled \"Clouds\"", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void off() {
        componentPort = CameraFactory.build();
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
        componentPort = CameraFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // No Actions in other stages necessary

    @Test
    @Order(9)
    public void shutdown() {
        componentPort = CameraFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
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