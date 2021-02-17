package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fire_detector.FireDetectorBodyScan;
import event.fire_detector.FireDetectorWingScan;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.fuel_sensor.FuelSensorMeasure;
import event.ice_detector_probe.IceDetectorProbeBodyActivate;
import event.ice_detector_probe.IceDetectorProbeBodyDeactivate;
import event.ice_detector_probe.IceDetectorProbeWingActivate;
import event.ice_detector_probe.IceDetectorProbeWingDeactivate;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {
	private ArrayList<Object> exhaustGasTemperatureSensorPortList;
	private ArrayList<Object> fireDetectorPortList;
	private ArrayList<Object> fuelFlowSensorPortList;
	private ArrayList<Object> fuelSensorPortList;
	private ArrayList<Object> iceDetectorProbePortList;
	private ArrayList<Object> rightNavigationLightPortList;

	public Wing() {
		exhaustGasTemperatureSensorPortList = new ArrayList<>();
		fireDetectorPortList = new ArrayList<>();
		fuelFlowSensorPortList = new ArrayList<>();
		fuelSensorPortList = new ArrayList<>();
		iceDetectorProbePortList = new ArrayList<>();
		rightNavigationLightPortList = new ArrayList<>();
		build();
	}

	public void build() {
		for (int i = 0; i < Configuration.instance.numberOfExhaustGasTemperatureSensor; i++) {
			exhaustGasTemperatureSensorPortList.add(ExhaustGasTemperatureSensorFactory.build());
		}
		for (int i = 0; i < Configuration.instance.numberOfFireDetectorProbeWing; i++) {
			fireDetectorPortList.add(FireDetectorFactory.build());
		}
		for (int i = 0; i < Configuration.instance.numberOfFuelFlowSensor; i++) {
			fuelFlowSensorPortList.add(FuelFlowSensorFactory.build());
		}
		for (int i = 0; i < Configuration.instance.numberOfFuelSensor; i++) {
			fuelSensorPortList.add(FuelSensorFactory.build());
		}
		for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeWing; i++) {
			iceDetectorProbePortList.add(IceDetectorProbeFactory.build());
		}
		for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
			rightNavigationLightPortList.add(RightNavigationLightFactory.build());
		}
	}

	// --- ExhaustGasTemperatureSensor --------------------------------------------------------------------------------

	@Subscribe
	public void receive(ExhaustGasTemperatureSensorMeasure exhaustGasTemperatureSensorMeasure) {
		LogEngine.instance.write("+ Wing.receive(" + exhaustGasTemperatureSensorMeasure.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + exhaustGasTemperatureSensorMeasure.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfExhaustGasTemperatureSensor; i++) {
				Method measureMethod = exhaustGasTemperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
				LogEngine.instance.write("measureMethod = " + measureMethod);

				int temperature = (int) measureMethod.invoke(exhaustGasTemperatureSensorPortList.get(i));
				LogEngine.instance.write("temperature = " + temperature);

				PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = temperature;
				FlightRecorder.instance.insert("Wing", "ExhaustGasTemperatureSensor (temperature): " + temperature);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (temperatureExhaustGasTemperatureSensor): " + PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "temperatureExhaustGasTemperatureSensor: " + PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor);
	}

	// ----------------------------------------------------------------------------------------------------------------

	// --- FireDetector -----------------------------------------------------------------------------------------------

	@Subscribe
	public void receive(FireDetectorBodyScan fireDetectorBodyScan) {
		LogEngine.instance.write("+ Wing.receive(" + fireDetectorBodyScan.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + fireDetectorBodyScan.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfFireDetectorProbeWing; i++) {
				Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan");
				LogEngine.instance.write("scanMethod = " + scanMethod);

				boolean alarm = (boolean) scanMethod.invoke(fireDetectorPortList.get(i));
				LogEngine.instance.write("alarm = " + alarm);

				PrimaryFlightDisplay.instance.isFireDetectedBody = alarm;
				FlightRecorder.instance.insert("Wing", "FireDetector (alarm): " + alarm);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedBody): " + PrimaryFlightDisplay.instance.isFireDetectedBody);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedBody: " + PrimaryFlightDisplay.instance.isFireDetectedBody);
	}

	@Subscribe
	public void receive(FireDetectorWingScan fireDetectorWingScan) {
		LogEngine.instance.write("+ Wing.receive(" + fireDetectorWingScan.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + fireDetectorWingScan.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfFireDetectorProbeWing; i++) {
				Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan");
				LogEngine.instance.write("scanMethod = " + scanMethod);

				boolean alarm = (boolean) scanMethod.invoke(fireDetectorPortList.get(i));
				LogEngine.instance.write("alarm = " + alarm);

				PrimaryFlightDisplay.instance.isFireDetectedWing = alarm;
				FlightRecorder.instance.insert("Wing", "FireDetector (alarm): " + alarm);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedWing): " + PrimaryFlightDisplay.instance.isFireDetectedWing);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedWing: " + PrimaryFlightDisplay.instance.isFireDetectedWing);
	}

	// ----------------------------------------------------------------------------------------------------------------

	// --- FuelFlowSensor ---------------------------------------------------------------------------------------------

	@Subscribe
	public void receive(FuelFlowSensorMeasure fuelFlowSensorMeasure) {
		LogEngine.instance.write("+ Wing.receive(" + fuelFlowSensorMeasure.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + fuelFlowSensorMeasure.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfFuelFlowSensor; i++) {
				Method measureMethod = fuelFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure");
				LogEngine.instance.write("measureMethod = " + measureMethod);

				int fuelFlow = (int) measureMethod.invoke(fuelFlowSensorPortList.get(i));
				LogEngine.instance.write("fuelFlow = " + fuelFlow);

				PrimaryFlightDisplay.instance.fuelFlow = fuelFlow;
				FlightRecorder.instance.insert("Wing", "FuelFlowSensor (fuelFlow): " + fuelFlow);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (fuelFlow): " + PrimaryFlightDisplay.instance.fuelFlow);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "fuelFlow: " + PrimaryFlightDisplay.instance.fuelFlow);
	}

	// ----------------------------------------------------------------------------------------------------------------

	// --- FuelSensor -------------------------------------------------------------------------------------------------

	@Subscribe
	public void receive(FuelSensorMeasure fuelSensorMeasure) {
		LogEngine.instance.write("+ Wing.receive(" + fuelSensorMeasure.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + fuelSensorMeasure.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfFuelSensor; i++) {
				Method measureMethod = fuelSensorPortList.get(i).getClass().getDeclaredMethod("measure");
				LogEngine.instance.write("measureMethod = " + measureMethod);

				double amountOfFuel = (double) measureMethod.invoke(fuelSensorPortList.get(i));
				LogEngine.instance.write("amountOfFuel = " + amountOfFuel);

				PrimaryFlightDisplay.instance.amountOfFuel = amountOfFuel;
				FlightRecorder.instance.insert("Wing", "FuelSensor (amountOfFuel): " + amountOfFuel);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (amountOfFuel): " + PrimaryFlightDisplay.instance.amountOfFuel);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "amountOfFuel: " + PrimaryFlightDisplay.instance.amountOfFuel);
	}

	// ----------------------------------------------------------------------------------------------------------------

	// --- IceDetectorProbe -------------------------------------------------------------------------------------------

	@Subscribe
	public void receive(IceDetectorProbeBodyActivate iceDetectorProbeBodyActivate) {
		LogEngine.instance.write("+ Wing.receive(" + iceDetectorProbeBodyActivate.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + iceDetectorProbeBodyActivate.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeWing; i++) {
				Method activateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("activate");
				LogEngine.instance.write("activateMethod = " + activateMethod);

				boolean isActivated = (boolean) activateMethod.invoke(iceDetectorProbePortList.get(i));
				LogEngine.instance.write("isActivated = " + isActivated);

				PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = isActivated;
				FlightRecorder.instance.insert("Wing", "IceDetectorProbe (isActivated): " + isActivated);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeBodyActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeBodyActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
	}

	@Subscribe
	public void receive(IceDetectorProbeWingActivate iceDetectorProbeWingActivate) {
		LogEngine.instance.write("+ Wing.receive(" + iceDetectorProbeWingActivate.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + iceDetectorProbeWingActivate.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeWing; i++) {
				Method activateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("activate");
				LogEngine.instance.write("activateMethod = " + activateMethod);

				boolean isActivated = (boolean) activateMethod.invoke(iceDetectorProbePortList.get(i));
				LogEngine.instance.write("isActivated = " + isActivated);

				PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = isActivated;
				FlightRecorder.instance.insert("Wing", "IceDetectorProbe (isActivated): " + isActivated);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeWingActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeWingActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
	}

	@Subscribe
	public void receive(IceDetectorProbeBodyDeactivate iceDetectorProbeBodyDeactivate) {
		LogEngine.instance.write("+ Wing.receive(" + iceDetectorProbeBodyDeactivate.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + iceDetectorProbeBodyDeactivate.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeWing; i++) {
				Method deactivateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("deactivate");
				LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

				boolean isActivated = (boolean) deactivateMethod.invoke(iceDetectorProbePortList.get(i));
				LogEngine.instance.write("isActivated = " + isActivated);

				PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = isActivated;
				FlightRecorder.instance.insert("Wing", "IceDetectorProbe (isActivated): " + isActivated);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeBodyActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeBodyActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
	}

	@Subscribe
	public void receive(IceDetectorProbeWingDeactivate iceDetectorProbeWingDeactivate) {
		LogEngine.instance.write("+ Wing.receive(" + iceDetectorProbeWingDeactivate.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + iceDetectorProbeWingDeactivate.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeWing; i++) {
				Method deactivateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("deactivate");
				LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

				boolean isActivated = (boolean) deactivateMethod.invoke(iceDetectorProbePortList.get(i));
				LogEngine.instance.write("isActivated = " + isActivated);

				PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = isActivated;
				FlightRecorder.instance.insert("Wing", "IceDetectorProbe (isActivated): " + isActivated);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeWingActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeWingActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
	}

	// ----------------------------------------------------------------------------------------------------------------

	// --- RightNavigationLight ---------------------------------------------------------------------------------------

	@Subscribe
	public void receive(RightNavigationLightOn rightNavigationLightOn) {
		LogEngine.instance.write("+ Wing.receive(" + rightNavigationLightOn.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + rightNavigationLightOn.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
				Method onMethod = rightNavigationLightPortList.get(i).getClass().getDeclaredMethod("on");
				LogEngine.instance.write("onMethod = " + onMethod);

				boolean isOn = (boolean) onMethod.invoke(rightNavigationLightPortList.get(i));
				LogEngine.instance.write("isOn = " + isOn);

				PrimaryFlightDisplay.instance.isRightNavigationLightOn = isOn;
				FlightRecorder.instance.insert("Wing", "RightNavigationLight (isOn): " + isOn);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isRightNavigationLightOn): " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRightNavigationLightOn: " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
	}

	@Subscribe
	public void receive(RightNavigationLightOff rightNavigationLightOff) {
		LogEngine.instance.write("+ Wing.receive(" + rightNavigationLightOff.toString() + ")");
		FlightRecorder.instance.insert("Wing", "receive(" + rightNavigationLightOff.toString() + ")");

		try {
			for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
				Method offMethod = rightNavigationLightPortList.get(i).getClass().getDeclaredMethod("off");
				LogEngine.instance.write("offMethod = " + offMethod);

				boolean isOn = (boolean) offMethod.invoke(rightNavigationLightPortList.get(i));
				LogEngine.instance.write("isOn = " + isOn);

				PrimaryFlightDisplay.instance.isRightNavigationLightOn = isOn;
				FlightRecorder.instance.insert("Wing", "RightNavigationLight (isOn): " + isOn);

				LogEngine.instance.write("+");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LogEngine.instance.write("PrimaryFlightDisplay (isRightNavigationLightOn): " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
		FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRightNavigationLightOn: " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
	}

	// ----------------------------------------------------------------------------------------------------------------

}
