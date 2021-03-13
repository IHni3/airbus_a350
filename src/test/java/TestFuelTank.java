import factory.FuelTankFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFuelTank {
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
        componentPort = FuelTankFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = FuelTankFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method outMethod = componentPort.getClass().getDeclaredMethod("takeOut", int.class);
            assertNotNull(outMethod);


            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            assertNotNull(refillMethod);

            Method refillParamMethod = componentPort.getClass().getDeclaredMethod("refill", int.class);
            assertNotNull(refillParamMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = FuelTankFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String version = (String) versionMethod.invoke(componentPort);

            assertEquals(version, "FuelTank // 8843476 / 9668368 - team 16 - 8843476 / 9668368");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(4)
    public void refill() {
        componentPort = FuelTankFactory.build();
        try {
            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            int result = (int) refillMethod.invoke(componentPort);
            assertEquals(result, 1000);

            Method refillParamMethod = componentPort.getClass().getDeclaredMethod("refill", int.class);
            result = (int) refillParamMethod.invoke(componentPort, 20);
            assertEquals(result, 20);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(5)
    public void takeOut() {
        componentPort = FuelTankFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("takeOut", int.class);
            int result = (int) outMethod.invoke(componentPort, 10);
            assertEquals(result, 10);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            refillMethod.invoke(componentPort);

            result = (int) outMethod.invoke(componentPort, 10000);
            assertEquals(result, 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}