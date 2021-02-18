import factory.ExhaustGasTemperatureSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExhaustGasTemperatureSensor {
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
		componentPort = ExhaustGasTemperatureSensorFactory.build();
		Assertions.assertNotNull(componentPort);
	}

	@Test
	@Order(2)
	public void methods() {
		componentPort = ExhaustGasTemperatureSensorFactory.build();
		try {
			Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
			Assertions.assertNotNull(measureMethod);

			Method alarmMajorMethod = componentPort.getClass().getDeclaredMethod("alarmMajor", Integer.TYPE);
			Assertions.assertNotNull(alarmMajorMethod);

			Method alarmCriticalMethod = componentPort.getClass().getDeclaredMethod("alarmCritical", Integer.TYPE);
			Assertions.assertNotNull(alarmCriticalMethod);
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void measure() {
		componentPort = ExhaustGasTemperatureSensorFactory.build();
		try {
			Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
			int temperature = (int) measureMethod.invoke(componentPort);
			Assertions.assertTrue(temperature > -273);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
	}
}
