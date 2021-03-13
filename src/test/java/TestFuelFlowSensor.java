import factory.FuelFlowSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFuelFlowSensor {
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
		componentPort = FuelFlowSensorFactory.build();
		Assertions.assertNotNull(componentPort);
	}

	@Test
	@Order(2)
	public void methods() {
		componentPort = FuelFlowSensorFactory.build();
		try {
			Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
			Assertions.assertNotNull(measureMethod);
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void measure() {
		componentPort = FuelFlowSensorFactory.build();
		try {
			Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
			int temperature = (int) measureMethod.invoke(componentPort);
			Assertions.assertTrue(temperature >= 0);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
	}
}
