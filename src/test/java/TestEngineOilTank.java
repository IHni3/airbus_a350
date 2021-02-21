import factory.EngineOilTankFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEngineOilTank {
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
        componentPort = EngineOilTankFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = EngineOilTankFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method inMethod = componentPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            assertNotNull(inMethod);


            Method outMethod = componentPort.getClass().getDeclaredMethod("decreaseLevel", int.class);
            assertNotNull(outMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = EngineOilTankFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String version = (String) versionMethod.invoke(componentPort);

            assertEquals(version, "EngineOilTank // 8843476 / 9668368 - team 16 - 8843476 / 9668368");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(4)
    public void increaseLevel() {
        componentPort = EngineOilTankFactory.build();
        try {
            Method increaseMethod = componentPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int result = (int) increaseMethod.invoke(componentPort, 20);
            assertEquals(result, 20);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(5)
    public void decreaseLevel() {
        componentPort = EngineOilTankFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("decreaseLevel", int.class);
            int result = (int) outMethod.invoke(componentPort, 10);
            assertEquals(result, 0);

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