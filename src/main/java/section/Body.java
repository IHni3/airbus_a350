package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.CameraFactory;
import factory.GpsFactory;
import factory.NitrogenBottleFactory;
import factory.OxygenBottleFactory;
import factory.TcasFactory;
import factory.TurbulentAirFlowSensorFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> cameraPortList;
    private ArrayList<Object> gpsPortList;
    private ArrayList<Object> nitrogenBottleList;
    private ArrayList<Object> oxygenBottleList;
    private ArrayList<Object> tcasList;
    private ArrayList<Object> turbulentAirFlowSensorList;
    private ArrayList<Object> weatherRadarPortList;

    public Body () {
        cameraPortList = new ArrayList<>();
        gpsPortList = new ArrayList<>();
        nitrogenBottleList = new ArrayList<>();
        oxygenBottleList = new ArrayList<>();
        tcasList = new ArrayList<>();
        turbulentAirFlowSensorList = new ArrayList<>();
        weatherRadarPortList = new ArrayList<>();
        build();
    }

    public void build () {
        for (int i = 0 ; i < Configuration.instance.numberOfCameraBody ; i++) {
            cameraPortList.add(setCameraType(CameraFactory.build()));
        }
        for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
            gpsPortList.add(GpsFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfNitrogen ; i++) {
            nitrogenBottleList.add(NitrogenBottleFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfOxygen ; i++) {
            oxygenBottleList.add(OxygenBottleFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
            tcasList.add(TcasFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody ; i++) {
            turbulentAirFlowSensorList.add(TurbulentAirFlowSensorFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfWeatherRadar ; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
    }


    // --- Camera -----------------------------------------------------------------------------------------------------
    private Object setCameraType (Object cameraPort) {
        try {
            Method setTypeMethod = cameraPort.getClass().getDeclaredMethod("setType", String.class);
            if (cameraPortList.size() > 1) {
                setTypeMethod.invoke(cameraPort, "taxi");
            } else {
                setTypeMethod.invoke(cameraPort, "tail");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cameraPort;
    }

    @Subscribe
    public void receive (CameraBodyOn cameraBodyOn) {
        LogEngine.instance.write("+ Body.receive(" + cameraBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cameraBodyOn.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfCameraBody ; i++) {
                Method onMethod = cameraPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cameraPortList.get(i));
                LogEngine.instance.write("onMethod = " + isOn);

                PrimaryFlightDisplay.instance.isBodyCameraOn = isOn;
                FlightRecorder.instance.insert("Body", "Camera (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isBodyCameraOn): " + PrimaryFlightDisplay.instance.isBodyCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isBodyCameraOn: " + PrimaryFlightDisplay.instance.isBodyCameraOn);
    }

    @Subscribe
    public void receive (CameraBodyOff cameraBodyOff) {
        LogEngine.instance.write("+ Body.receive(" + cameraBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cameraBodyOff.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfCameraBody ; i++) {
                Method offMethod = cameraPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOff = (boolean) offMethod.invoke(cameraPortList.get(i));
                LogEngine.instance.write("offMethod = " + isOff);

                PrimaryFlightDisplay.instance.isBodyCameraOn = isOff;
                FlightRecorder.instance.insert("Body", "Camera (isOff): " + isOff);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isBodyCameraOff): " + PrimaryFlightDisplay.instance.isBodyCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isBodyCameraOn: " + PrimaryFlightDisplay.instance.isBodyCameraOn);
    }

    // --- TurbulentAirFlowSensor--------------------------------------------------------------------------------------

    @Subscribe
    public void receive (TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody ; i++) {
                Method measureMethod = turbulentAirFlowSensorList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airFlow = (int) measureMethod.invoke(turbulentAirFlowSensorList.get(i), "laminarturbulentturbulentlaminarwindwindturbulent");
                LogEngine.instance.write("airFlow = " + airFlow);

                if (airFlow > 10) {
                    // Turbulences -> Alarm
                    boolean alarm = (boolean) turbulentAirFlowSensorList.get(i).getClass().getDeclaredMethod("alarm").invoke(turbulentAirFlowSensorList.get(i));
                    FlightRecorder.instance.insert("Body", "TurbulentAirFlowSensorList (alarm): " + alarm);

                    LogEngine.instance.write("PrimaryFlightDisplay (isTurbulentAirFlowAlarm): " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
                    FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTurbulentAirFlowAlarm: " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
                }

                FlightRecorder.instance.insert("Body", "TurbulentAirFlowSensorList (airFlow): " + airFlow);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive (WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfWeatherRadar ; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive (WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfWeatherRadar ; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive (WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------
}