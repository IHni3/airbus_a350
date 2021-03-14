package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fire_detector.FireDetectorWingScan;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.fuel_sensor.FuelSensorMeasure;
import event.ice_detector_probe.IceDetectorProbeWingActivate;
import event.ice_detector_probe.IceDetectorProbeWingDeactivate;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings({"FieldMayBeFinal", "UnstableApiUsage", "unused"})
public class Wing extends Subscriber {
    private ArrayList<Object> engineOilTankPortList;
    private ArrayList<Object> fuelTankPortList;
    private ArrayList<Object> cameraPortList;
    private ArrayList<Object> exhaustGasTemperatureSensorPortList;
    private ArrayList<Object> fireDetectorPortList;
    private ArrayList<Object> fuelFlowSensorPortList;
    private ArrayList<Object> fuelSensorPortList;
    private ArrayList<Object> iceDetectorProbePortList;
    private ArrayList<Object> rightNavigationLightPortList;
    private ArrayList<Object> turbulentAirFlowSensorList;

    public Wing() {
        engineOilTankPortList = new ArrayList<>();
        fuelTankPortList = new ArrayList<>();
        cameraPortList = new ArrayList<>();
        exhaustGasTemperatureSensorPortList = new ArrayList<>();
        fireDetectorPortList = new ArrayList<>();
        fuelFlowSensorPortList = new ArrayList<>();
        fuelSensorPortList = new ArrayList<>();
        iceDetectorProbePortList = new ArrayList<>();
        rightNavigationLightPortList = new ArrayList<>();
        turbulentAirFlowSensorList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
            engineOilTankPortList.add(EngineOilTankFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
            fuelTankPortList.add(FuelTankFactory.build());
        }
      
        for (int i = 0 ; i < Configuration.instance.numberOfCameraWing ; i++) {
            cameraPortList.add(setCameraType(CameraFactory.build()));
        }
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
        for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorWing ; i++) {
            turbulentAirFlowSensorList.add(TurbulentAirFlowSensorFactory.build());
        }
    }

    // --- Camera -----------------------------------------------------------------------------------------------------
    private Object setCameraType (Object cameraPort) {
        try {
            Method setTypeMethod = cameraPort.getClass().getDeclaredMethod("setType", String.class);
            setTypeMethod.invoke(cameraPort, "wing");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cameraPort;
    }

    @Subscribe
    public void receive(CameraWingOn cameraWingOn) {
        LogEngine.instance.write("+ Wing.receive(" + cameraWingOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + cameraWingOn.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfCameraWing ; i++) {
                Method onMethod = cameraPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cameraPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Wing", "Camera (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWingCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWingCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }

    @Subscribe
    public void receive(CameraWingOff cameraWingOff) {
        LogEngine.instance.write("+ Wing.receive(" + cameraWingOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + cameraWingOff.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfCameraWing ; i++) {
                Method offMethod = cameraPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(cameraPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Wing", "Camera (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWingCameraOff): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWingCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
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

    // --- FireDetector -----------------------------------------------------------------------------------------------

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

    // --- IceDetectorProbe -------------------------------------------------------------------------------------------

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

    // --- TurbulentAirFlowSensor--------------------------------------------------------------------------------------
    @Subscribe
    public void receive(TurbulentAirFlowSensorWingMeasure turbulentAirFlowSensorWingMeasure) {
        LogEngine.instance.write("+ Wing.receive(" + turbulentAirFlowSensorWingMeasure.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + turbulentAirFlowSensorWingMeasure.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorWing ; i++) {
                Method measureMethod = turbulentAirFlowSensorList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airFlow = (int) measureMethod.invoke(turbulentAirFlowSensorList.get(i), "laminarturbulentturbulentlaminarwindwindturbulent");
                LogEngine.instance.write("airFlow = " + airFlow);

                if (airFlow > 10) {
                    // Turbulences -> Alarm
                    boolean alarm = (boolean) turbulentAirFlowSensorList.get(i).getClass().getDeclaredMethod("alarm").invoke(turbulentAirFlowSensorList.get(i));
                    FlightRecorder.instance.insert("Wing", "TurbulentAirFlowSensorList (alarm): " + alarm);

                    LogEngine.instance.write("PrimaryFlightDisplay (isTurbulentAirFlowAlarm): " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
                    FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTurbulentAirFlowAlarm: " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
                }

                FlightRecorder.instance.insert("Wing", "TurbulentAirFlowSensorList (airFlow): " + airFlow);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ----------------------------------------------------------------------------------------------------------------
  
        //engine_oil_tank
    @Subscribe
    public void receive(EngineOilTankDecreaseLevel engineOilTankDecreaseLevel) {
        System.out.println(engineOilTankDecreaseLevel);
        LogEngine.instance.write("+ Body.receive(" + engineOilTankDecreaseLevel.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + engineOilTankDecreaseLevel.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
                Method decreaseLevelMethod = engineOilTankPortList.get(i).getClass().getDeclaredMethod("decreaseLevel", int.class);
                LogEngine.instance.write("decreaseLevelMethod = " + decreaseLevelMethod);

                int level = (int) decreaseLevelMethod.invoke(engineOilTankPortList.get(i), 1);
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelEngineOilTank = level;
                FlightRecorder.instance.insert("Body", "EngineOilTank (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelEngineOilTank): " + PrimaryFlightDisplay.instance.levelEngineOilTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelEngineOilTank: " + PrimaryFlightDisplay.instance.levelEngineOilTank);
    }

    @Subscribe
    public void receive(EngineOilTankIncreaseLevel engineOilTankIncreaseLevel) {
        System.out.println(engineOilTankIncreaseLevel);
        LogEngine.instance.write("+ Body.receive(" + engineOilTankIncreaseLevel.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + engineOilTankIncreaseLevel.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
                Method increaseLevelMethod = engineOilTankPortList.get(i).getClass().getDeclaredMethod("increaseLevel", int.class);
                LogEngine.instance.write("decreaseLevelMethod = " + increaseLevelMethod);

                int level = (int) increaseLevelMethod.invoke(engineOilTankPortList.get(i), 10);
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelEngineOilTank = level;
                FlightRecorder.instance.insert("Body", "EngineOilTank (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelEngineOilTank): " + PrimaryFlightDisplay.instance.levelEngineOilTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelEngineOilTank: " + PrimaryFlightDisplay.instance.levelEngineOilTank);
    }

    //fuel_tank
    @Subscribe
    public void receive(FuelTankRefill fuelTankRefill) {
        System.out.println(fuelTankRefill);
        LogEngine.instance.write("+ Body.receive(" + fuelTankRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fuelTankRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
                int level = 0;
                if(fuelTankRefill.getAmount() == -1){
                    Method refillMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("refill");
                    LogEngine.instance.write("refillMethod = " + refillMethod);
                    level = (int) refillMethod.invoke(fuelTankPortList.get(i));
                }else{
                    Method refillMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("refill", int.class);
                    LogEngine.instance.write("refillMethod = " + refillMethod);
                    level = (int) refillMethod.invoke(fuelTankPortList.get(i), fuelTankRefill.getAmount());
                }

                LogEngine.instance.write("amount = " + level);

                PrimaryFlightDisplay.instance.amountOfFuelInTank = level;
                FlightRecorder.instance.insert("Body", "Fuel Tank (amount): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (amountOfFuel): " + PrimaryFlightDisplay.instance.amountOfFuelInTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "amountOfFuel: " + PrimaryFlightDisplay.instance.amountOfFuelInTank);
    }

    @Subscribe
    public void receive(FuelTankTakeOut fuelTankTakeOut) {
        System.out.println(fuelTankTakeOut);
        LogEngine.instance.write("+ Body.receive(" + fuelTankTakeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fuelTankTakeOut.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
                Method takeOutMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("takeOut", int.class);
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);
                int amount = (int) takeOutMethod.invoke(fuelTankPortList.get(i), fuelTankTakeOut.getAmount());

                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountOfFuelInTank = amount;
                FlightRecorder.instance.insert("Body", "Fuel Tank (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (amountOfFuel): " + PrimaryFlightDisplay.instance.amountOfFuelInTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "amountOfFuel: " + PrimaryFlightDisplay.instance.amountOfFuelInTank);
    }
}
