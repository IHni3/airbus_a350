import base.PrimaryFlightDisplay;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestWeatherRadar {
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
        componentPort = WeatherRadarFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = WeatherRadarFactory.build();
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
    public void scanCloud() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void scanBird() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void off() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void startup() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void taxi() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void takeoff() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void climbing() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(11)
    public void rightTurn() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(12)
    public void leftTurn() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(13)
    public void descent() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(14)
    public void landing() {
        componentPort = WeatherRadarFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean result = (boolean) scanMethod.invoke(componentPort, "cloud");
            assertFalse(result);

            result = (boolean) scanMethod.invoke(componentPort, "bird");
            assertTrue(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(15)
    public void shutdown() {
        componentPort = WeatherRadarFactory.build();
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