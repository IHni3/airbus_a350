import factory.SpoilerFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpoilerTest {


    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = SpoilerFactory.build();
    }

    @Test
    @Order(1)
    public void factory() {
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() throws NoSuchMethodException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("neutral");
        assertNotNull(onMethod);

        Method offMethod = componentPort.getClass().getDeclaredMethod("fullUp");
        assertNotNull(offMethod);

        Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
        assertNotNull(upMethod);

        Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
        assertNotNull(downMethod);
    }

    @Test
    @Order(3)
    public void neutral() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
        int neutral = (int) neutralMethod.invoke(componentPort);
        assertEquals(neutral, 0);
    }

    @Test
    @Order(4)
    public void fullUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
        int fullUp = (int) fullUpMethod.invoke(componentPort);
        assertEquals(fullUp, 90);
    }


    @Test
    @Order(5)
    public void up() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = 10;
        Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
        int up = (int) upMethod.invoke(componentPort, value);
        assertEquals(up, value);
    }

    @Test
    @Order(6)
    public void down() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = 10;
        Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
        int down = (int) downMethod.invoke(componentPort, value);
        assertEquals(down, -value);
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

}
