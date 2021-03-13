import factory.TailNavigationLightFactory;
import logging.LogEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestTailNavigationLight {
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
		componentPort = TailNavigationLightFactory.build();
		Assertions.assertNotNull(componentPort);
	}

	@Test
	@Order(2)
	public void methods() {
		componentPort = TailNavigationLightFactory.build();
		try {
			Method onMethod = componentPort.getClass().getDeclaredMethod("on");
			Assertions.assertNotNull(onMethod);

			Method offMethod = componentPort.getClass().getDeclaredMethod("off");
			Assertions.assertNotNull(offMethod);
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void on() {
		componentPort = TailNavigationLightFactory.build();
		try {
			Method onMethod = componentPort.getClass().getMethod("on");
			boolean isOn = (boolean) onMethod.invoke(componentPort);
			Assertions.assertTrue(isOn);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(4)
	public void off() {
		componentPort = TailNavigationLightFactory.build();
		try {
			Method offMethod = componentPort.getClass().getDeclaredMethod("off");
			boolean isOn = (boolean) offMethod.invoke(componentPort);
			Assertions.assertFalse(isOn);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
	}
}
