import factory.SlatFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SlatTest {


    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = SlatFactory.build();
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

        Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
        assertNotNull(fullDownMethod);

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
    public void fullDown() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
        int fullDown = (int) fullDownMethod.invoke(componentPort);
        assertEquals(fullDown, -90);
    }

    @Test
    @Order(5)
    public void up() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = -10;
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
