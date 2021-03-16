import base.PrimaryFlightDisplay;
import factory.OxygenBottleFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOxygenBottle {
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
        componentPort = OxygenBottleFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            assertNotNull(takeOutMethod);

            Method refill = componentPort.getClass().getDeclaredMethod("refill");
            assertNotNull(refill);

            Method refillWithArg = componentPort.getClass().getDeclaredMethod("refill", Integer.TYPE);
            assertNotNull(refillWithArg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void takeOut() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(50, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void refillWithArg() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(50, amount);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill", Integer.TYPE);
            amount = (int) refillMethod.invoke(componentPort, 20);
            assertEquals(70, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void refill() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(50, amount);

            Method refill = componentPort.getClass().getDeclaredMethod("refill");
            amount = (int) refill.invoke(componentPort);
            assertEquals(100, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void startup() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method refill = componentPort.getClass().getDeclaredMethod("refill");
            int amount = (int) refill.invoke(componentPort);

            PrimaryFlightDisplay.instance.amountOfOxygen = amount;
            assertEquals(100, PrimaryFlightDisplay.instance.amountOfOxygen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void otherPhases() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 2);

            PrimaryFlightDisplay.instance.amountOfOxygen = amount;
            assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void shutdown() {
        componentPort = OxygenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            PrimaryFlightDisplay.instance.amountOfOxygen = amount;
            assertEquals(50, PrimaryFlightDisplay.instance.amountOfOxygen);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            amount = (int) refillMethod.invoke(componentPort);

            PrimaryFlightDisplay.instance.amountOfOxygen = amount;
            assertEquals(100, PrimaryFlightDisplay.instance.amountOfOxygen);
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