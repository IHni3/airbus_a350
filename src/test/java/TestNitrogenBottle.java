import base.PrimaryFlightDisplay;
import factory.NitrogenBottleFactory;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestNitrogenBottle {
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
        componentPort = NitrogenBottleFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = NitrogenBottleFactory.build();
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
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(200, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void refillWithArg() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(200, amount);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill", Integer.TYPE);
            amount = (int) refillMethod.invoke(componentPort, 20);
            assertEquals(220, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void refill() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 50);
            assertEquals(200, amount);

            Method refill = componentPort.getClass().getDeclaredMethod("refill");
            amount = (int) refill.invoke(componentPort);
            assertEquals(250, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void startup() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 20);

            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(230, PrimaryFlightDisplay.instance.amountOfNitrogen);

            Method refill = componentPort.getClass().getDeclaredMethod("refill");
            amount = (int) refill.invoke(componentPort);

            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(250, PrimaryFlightDisplay.instance.amountOfNitrogen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void taxi() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 5);

            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(245, PrimaryFlightDisplay.instance.amountOfNitrogen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void otherPhases() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut", Integer.TYPE);
            int amount = (int) takeOutMethod.invoke(componentPort, 10);

            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void shutdown() {
        componentPort = NitrogenBottleFactory.build();
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("refill");
            int amount = (int) takeOutMethod.invoke(componentPort);

            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(250, PrimaryFlightDisplay.instance.amountOfNitrogen);
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