import factory.RudderFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RudderTest {


    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = RudderFactory.build();
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

        Method offMethod = componentPort.getClass().getDeclaredMethod("fullLeft");
        assertNotNull(offMethod);

        Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullRight");
        assertNotNull(fullDownMethod);

        Method upMethod = componentPort.getClass().getDeclaredMethod("left", int.class);
        assertNotNull(upMethod);

        Method downMethod = componentPort.getClass().getDeclaredMethod("right", int.class);
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
    public void fullLeft() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fullLeftMethod = componentPort.getClass().getDeclaredMethod("fullLeft");
        int fullLeft = (int) fullLeftMethod.invoke(componentPort);
        assertEquals(fullLeft, 90);
    }

    @Test
    @Order(5)
    public void fullRight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fullRightMethod = componentPort.getClass().getDeclaredMethod("fullRight");
        int fullRight = (int) fullRightMethod.invoke(componentPort);
        assertEquals(fullRight, -90);
    }

    @Test
    @Order(6)
    public void left() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = 10;
        Method leftMethod = componentPort.getClass().getDeclaredMethod("left", int.class);
        int left = (int) leftMethod.invoke(componentPort, value);
        assertEquals(left, value);
    }

    @Test
    @Order(7)
    public void right() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = 10;
        Method rightMethod = componentPort.getClass().getDeclaredMethod("right", int.class);
        int right = (int) rightMethod.invoke(componentPort, value);
        assertEquals(right, -value);
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

}
