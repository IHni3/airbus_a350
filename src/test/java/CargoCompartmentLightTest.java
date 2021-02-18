import factory.CargoCompartmentLightFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CargoCompartmentLightTest {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = CargoCompartmentLightFactory.build();
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

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}