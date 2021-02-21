import base.PrimaryFlightDisplay;
import domains.CheckPoint;
import factory.RouteManagementFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;
import recorder.FlightRecorder;

import java.io.InvalidObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RouteManagementTest {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = RouteManagementFactory.build();
    }

    @Test
    @Order(1)
    public void factory() {
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() throws NoSuchMethodException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("on");
        assertNotNull(onMethod);

        Method offMethod = componentPort.getClass().getDeclaredMethod("off");
        assertNotNull(offMethod);

        Method costIndexMethod = componentPort.getClass().getDeclaredMethod("setCostIndex", int.class);
        assertNotNull(costIndexMethod);

        Method removeMethod = componentPort.getClass().getDeclaredMethod("remove", CheckPoint.class);
        assertNotNull(removeMethod);

        Method addMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
        assertNotNull(addMethod);
    }

    @Test
    @Order(3)
    public void on() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("on");
        boolean isOn = (boolean) onMethod.invoke(componentPort);
        assertTrue(isOn);
    }

    @Test
    @Order(4)
    public void off() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("off");
        boolean isOn = (boolean) onMethod.invoke(componentPort);
        assertFalse(isOn);
    }

    @Test
    @Order(5)
    public void add() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
        int size = (int) onMethod.invoke(componentPort, new CheckPoint(1, "bla", "blub", "bring"));
        assertEquals(size, 1);
    }

    @Test
    @Order(6)
    public void remove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method onMethod = componentPort.getClass().getDeclaredMethod("remove", CheckPoint.class);
        int size = (int) onMethod.invoke(componentPort, new CheckPoint(1, "bla", "blub", "bring"));
        assertEquals(size, 0);
    }

    @Test
    @Order(7)
    public void setCostIndex() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int value = 10;
        Method onMethod = componentPort.getClass().getDeclaredMethod("setCostIndex", int.class);
        int returnValue = (int) onMethod.invoke(componentPort, value);
        assertEquals(returnValue, value);
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}