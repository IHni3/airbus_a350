import factory.RightAileronFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import org.opentest4j.TestAbortedException;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Right_AileronTest {


    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = RightAileronFactory.build();
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

        Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
        assertNotNull(fullDownMethod);

        Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
        assertNotNull(upMethod);

        Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
        assertNotNull(downMethod);
    }
/*
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
*/
    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

}
