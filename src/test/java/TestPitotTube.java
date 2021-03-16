import factory.PitotTubeFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPitotTube {
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
        componentPort = PitotTubeFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = PitotTubeFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method staticMethod = componentPort.getClass().getDeclaredMethod("measureStaticPressure");
            assertNotNull(staticMethod);

            Method velocityMethod = componentPort.getClass().getDeclaredMethod("measureVelocity");
            assertNotNull(velocityMethod);

            Method cleanMethod = componentPort.getClass().getDeclaredMethod("clean");
            assertNotNull(cleanMethod);

            Method totalMethod = componentPort.getClass().getDeclaredMethod("measureTotalPressure");
            assertNotNull(totalMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = PitotTubeFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String version = (String) versionMethod.invoke(componentPort);

            assertEquals(version, "PitotTube // 8843476 / 9668368 - team 16 - 8843476 / 9668368");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(4)
    public void measureStaticPressure() {
        componentPort = PitotTubeFactory.build();
        try {
            Method increaseMethod = componentPort.getClass().getDeclaredMethod("measureStaticPressure");
            int result = (int) increaseMethod.invoke(componentPort);
            assertEquals(result, -1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(5)
    public void measureTotalPressure() {
        componentPort = PitotTubeFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("measureTotalPressure");
            int result = (int) outMethod.invoke(componentPort);
            assertEquals(result, -1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(6)
    public void measureVelocity() {
        componentPort = PitotTubeFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("measureVelocity");
            int result = (int) outMethod.invoke(componentPort);
            assertEquals(result, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(7)
    public void clean() {
        componentPort = PitotTubeFactory.build();
        try {
            Field field = componentPort.getClass().getEnclosingClass().getDeclaredField("isCleaned");
            field.setAccessible(true);
            Method outMethod = componentPort.getClass().getDeclaredMethod("clean");
            outMethod.invoke(componentPort);

            assertEquals(
                    (boolean) field.get(    //check Field
                            (componentPort.getClass().getEnclosingClass().cast( //cast instance to PitotTube
                                    componentPort.getClass().getEnclosingClass()    //get Class PitotTube
                                            .getDeclaredMethod("getInstance")
                                            .invoke(componentPort))))               //get current Instance
                    , true);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}