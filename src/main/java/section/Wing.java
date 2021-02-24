package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import factory.CameraFactory;
import factory.TurbulentAirFlowSensorFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> cameraPortList;
    private ArrayList<Object> turbulentAirFlowSensorList;

    public Wing() {
        cameraPortList = new ArrayList<>();
        turbulentAirFlowSensorList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0 ; i < Configuration.instance.numberOfCameraWing ; i++) {
            cameraPortList.add(setCameraType(CameraFactory.build()));
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
}