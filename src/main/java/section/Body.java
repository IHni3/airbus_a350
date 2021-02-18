package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.gps.GPSConnect;
import event.gps.GPSOff;
import event.gps.GPSOn;
import event.gps.GPSReceive;
import event.gps.GPSSend;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.tcas.TCASConnect;
import event.tcas.TCASDetermineAltitude;
import event.tcas.TCASOff;
import event.tcas.TCASOn;
import event.tcas.TCASScan;
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
    private ArrayList<Object> nitrogenBottlePortList;
    private ArrayList<Object> oxygenBottlePortList;
    private ArrayList<Object> tcasPortList;
    private ArrayList<Object> turbulentAirFlowSensorPortList;
    private ArrayList<Object> weatherRadarPortList;

    public Body () {
        cameraPortList = new ArrayList<>();
        gpsPortList = new ArrayList<>();
        nitrogenBottlePortList = new ArrayList<>();
        oxygenBottlePortList = new ArrayList<>();
        tcasPortList = new ArrayList<>();
        turbulentAirFlowSensorPortList = new ArrayList<>();
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
            nitrogenBottlePortList.add(NitrogenBottleFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfOxygen ; i++) {
            oxygenBottlePortList.add(OxygenBottleFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
            tcasPortList.add(TcasFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody ; i++) {
            turbulentAirFlowSensorPortList.add(TurbulentAirFlowSensorFactory.build());
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

                PrimaryFlightDisplay.instance.isCameraOn = isOn;
                FlightRecorder.instance.insert("Body", "Camera (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isBodyCameraOn): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isBodyCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
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

                PrimaryFlightDisplay.instance.isCameraOn = isOff;
                FlightRecorder.instance.insert("Body", "Camera (isOff): " + isOff);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isBodyCameraOff): " + PrimaryFlightDisplay.instance.isCameraOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isBodyCameraOn: " + PrimaryFlightDisplay.instance.isCameraOn);
    }

    // --- GPS --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive (GPSOn gpsOn) {
        LogEngine.instance.write("+ Body.receive(" + gpsOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsOn.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
                Method onMethod = gpsPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("onMethod = " + isOn);

                PrimaryFlightDisplay.instance.isGPSOn = isOn;
                FlightRecorder.instance.insert("Body", "Gps (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGpsOn): " + PrimaryFlightDisplay.instance.isGPSOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGpsOn: " + PrimaryFlightDisplay.instance.isGPSOn);
    }

    @Subscribe
    public void receive (GPSOff gpsOff) {
        LogEngine.instance.write("+ Body.receive(" + gpsOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsOff.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
                Method offMethod = gpsPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isGPSOn = isOn;
                FlightRecorder.instance.insert("Body", "Gps (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGPSOn): " + PrimaryFlightDisplay.instance.isGPSOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGPSOn: " + PrimaryFlightDisplay.instance.isGPSOn);
    }

    @Subscribe
    public void receive (GPSConnect gpsConnect) {
        LogEngine.instance.write("+ Body.receive(" + gpsConnect.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsConnect.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
                Method connectMethod = gpsPortList.get(i).getClass().getDeclaredMethod("connect", String.class);
                LogEngine.instance.write("connectMethod = " + connectMethod);

                boolean isConnected = (boolean) connectMethod.invoke(gpsPortList.get(i), gpsConnect.getSatelite());
                LogEngine.instance.write("isConnected = " + isConnected);

                PrimaryFlightDisplay.instance.isGPSOn = isConnected;
                FlightRecorder.instance.insert("Body", "Gps (isConnected): " + isConnected);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGPSConnected): " + PrimaryFlightDisplay.instance.isGPSConnected);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGPSConnected: " + PrimaryFlightDisplay.instance.isGPSConnected);
    }

    @Subscribe
    public void receive (GPSSend gpsSend) {
        LogEngine.instance.write("+ Body.receive(" + gpsSend.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsSend.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
                Method sendMethod = gpsPortList.get(i).getClass().getDeclaredMethod("send", String.class);
                LogEngine.instance.write("sendMethod = " + sendMethod);

                sendMethod.invoke(gpsPortList.get(i), gpsSend.getRequest());
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Subscribe
    public void receive (GPSReceive gpsReceive) {
        LogEngine.instance.write("+ Body.receive(" + gpsReceive.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gpsReceive.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
                Method receiveMethod = gpsPortList.get(i).getClass().getDeclaredMethod("receive");
                LogEngine.instance.write("receiveMethod = " + receiveMethod);

                String received = (String) receiveMethod.invoke(gpsPortList.get(i));
                LogEngine.instance.write("received = " + received);

                FlightRecorder.instance.insert("Body", "Gps (receive): " + received);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // --- NitrogenBottle ---------------------------------------------------------------------------------------------

    @Subscribe
    public void receive (NitrogenBottleRefill nitrogenBottleRefill) {
        LogEngine.instance.write("+ Body.receive(" + nitrogenBottleRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nitrogenBottleRefill.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfNitrogen ; i++) {
                int nitrogenAmount;
                if (nitrogenBottleRefill.getAmount() == null) {
                    Method refillMethod = nitrogenBottlePortList.get(i).getClass().getDeclaredMethod("refill");
                    LogEngine.instance.write("refillMethod = " + refillMethod);

                    nitrogenAmount = (int) refillMethod.invoke(nitrogenBottlePortList.get(i));
                } else {
                    Method refillMethod = nitrogenBottlePortList.get(i).getClass().getDeclaredMethod("refill", Integer.TYPE);
                    LogEngine.instance.write("refillMethod = " + refillMethod);

                    nitrogenAmount = (int) refillMethod.invoke(nitrogenBottlePortList.get(i), nitrogenBottleRefill.getAmount());
                }
                LogEngine.instance.write("nitrogenAmount = " + nitrogenAmount);

                PrimaryFlightDisplay.instance.amountOfNitrogen = nitrogenAmount;
                FlightRecorder.instance.insert("Body", "NitrogenBottle (amount): " + nitrogenAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("NitrogenBottle (amountOfNitrogen): " + PrimaryFlightDisplay.instance.amountOfNitrogen);
        FlightRecorder.instance.insert("NitrogenBottle", "amountOfNitrogen: " + PrimaryFlightDisplay.instance.amountOfNitrogen);
    }

    @Subscribe
    public void receive (NitrogenBottleTakeOut nitrogenBottleTakeOut) {
        LogEngine.instance.write("+ Body.receive(" + nitrogenBottleTakeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nitrogenBottleTakeOut.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfNitrogen ; i++) {
                Method takeOutMethod = nitrogenBottlePortList.get(i).getClass().getDeclaredMethod("takeOut", Integer.TYPE);
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);

                int nitrogenAmount = (int) takeOutMethod.invoke(nitrogenBottlePortList.get(i), nitrogenBottleTakeOut.getAmount());

                LogEngine.instance.write("nitrogenAmount = " + nitrogenAmount);

                PrimaryFlightDisplay.instance.amountOfNitrogen = nitrogenAmount;
                FlightRecorder.instance.insert("Body", "NitrogenBottle (amount): " + nitrogenAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("NitrogenBottle (amountOfNitrogen): " + PrimaryFlightDisplay.instance.amountOfNitrogen);
        FlightRecorder.instance.insert("NitrogenBottle", "amountOfNitrogen: " + PrimaryFlightDisplay.instance.amountOfNitrogen);
    }

    // --- OxygenBottle -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive (OxygenBottleRefill oxygenBottleRefill) {
        LogEngine.instance.write("+ Body.receive(" + oxygenBottleRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + oxygenBottleRefill.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfOxygen ; i++) {
                int oxygenAmount;
                if (oxygenBottleRefill.getAmount() == null) {
                    Method refillMethod = oxygenBottlePortList.get(i).getClass().getDeclaredMethod("refill");
                    LogEngine.instance.write("refillMethod = " + refillMethod);

                    oxygenAmount = (int) refillMethod.invoke(oxygenBottlePortList.get(i));
                } else {
                    Method refillMethod = oxygenBottlePortList.get(i).getClass().getDeclaredMethod("refill", Integer.TYPE);
                    LogEngine.instance.write("refillMethod = " + refillMethod);

                    oxygenAmount = (int) refillMethod.invoke(oxygenBottlePortList.get(i), oxygenBottleRefill.getAmount());
                }
                LogEngine.instance.write("oxygenAmount = " + oxygenAmount);

                PrimaryFlightDisplay.instance.amountOfOxygen = oxygenAmount;
                FlightRecorder.instance.insert("Body", "OxygenBottle (amount): " + oxygenAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("OxygenBottle (amountOfOxygen): " + PrimaryFlightDisplay.instance.amountOfOxygen);
        FlightRecorder.instance.insert("OxygenBottle", "amountOfOxygen: " + PrimaryFlightDisplay.instance.amountOfOxygen);
    }

    @Subscribe
    public void receive (OxygenBottleTakeOut oxygenBottleTakeOut) {
        LogEngine.instance.write("+ Body.receive(" + oxygenBottleTakeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + oxygenBottleTakeOut.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfOxygen ; i++) {
                Method takeOutMethod = oxygenBottlePortList.get(i).getClass().getDeclaredMethod("takeOut", Integer.TYPE);
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);

                int oxygenAmount = (int) takeOutMethod.invoke(oxygenBottlePortList.get(i), oxygenBottleTakeOut.getAmount());

                LogEngine.instance.write("oxygenAmount = " + oxygenAmount);

                PrimaryFlightDisplay.instance.amountOfOxygen = oxygenAmount;
                FlightRecorder.instance.insert("Body", "OxygenBottle (amount): " + oxygenAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("OxygenBottle (amountOfOxygen): " + PrimaryFlightDisplay.instance.amountOfOxygen);
        FlightRecorder.instance.insert("OxygenBottle", "amountOfOxygen: " + PrimaryFlightDisplay.instance.amountOfOxygen);
    }

    // --- TCAS -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive (TCASOn tcasOn) {
        LogEngine.instance.write("+ Body.receive(" + tcasOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOn.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
                Method onMethod = tcasPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("tcasIsOn = " + isOn);

                PrimaryFlightDisplay.instance.isTCASOn = isOn;
                FlightRecorder.instance.insert("Body", "Tcas (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTcasOn): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTcasOn: " + PrimaryFlightDisplay.instance.isTCASOn);
    }

    @Subscribe
    public void receive (TCASOff tcasOff) {
        LogEngine.instance.write("+ Body.receive(" + tcasOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOff.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
                Method offMethod = tcasPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTCASOn = isOn;
                FlightRecorder.instance.insert("Body", "Tcas (isOn): " + isOn);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASOn): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASOn: " + PrimaryFlightDisplay.instance.isTCASOn);
    }

    @Subscribe
    public void receive (TCASConnect tcasConnect) {
        LogEngine.instance.write("+ Body.receive(" + tcasConnect.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasConnect.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
                Method connectMethod = tcasPortList.get(i).getClass().getDeclaredMethod("connect", String.class);
                LogEngine.instance.write("connectMethod = " + connectMethod);

                boolean isConnected = (boolean) connectMethod.invoke(tcasPortList.get(i), tcasConnect.getFrequency());
                LogEngine.instance.write("isConnected = " + isConnected);

                PrimaryFlightDisplay.instance.isTCASOn = isConnected;
                FlightRecorder.instance.insert("Body", "Tcas (isConnected): " + isConnected);
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASConnected): " + PrimaryFlightDisplay.instance.isTCASConnected);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASConnected: " + PrimaryFlightDisplay.instance.isTCASConnected);
    }

    @Subscribe
    public void receive (TCASScan tcasScan) {
        LogEngine.instance.write("+ Body.receive(" + tcasScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasScan.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
                Method scanMethod = tcasPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean scanResult = (boolean) scanMethod.invoke(tcasPortList.get(i), tcasScan.getEnvironment());
                LogEngine.instance.write("tcasScanResult = " + scanResult);
                FlightRecorder.instance.insert("Body", "Tcas (scanResult): " + scanResult);

                if (scanResult) {
                    // Scan positive -> Alarm
                    boolean alarm = (boolean) tcasPortList.get(i).getClass().getDeclaredMethod("alarm").invoke(tcasPortList.get(i));
                    FlightRecorder.instance.insert("Body", "Tcas (alarm): " + alarm);

                    PrimaryFlightDisplay.instance.isTCASAlarm = alarm;

                    LogEngine.instance.write("PrimaryFlightDisplay (isTCASAlarm): " + PrimaryFlightDisplay.instance.isTCASAlarm);
                    FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASAlarm: " + PrimaryFlightDisplay.instance.isTCASAlarm);
                }
                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Subscribe
    public void receive (TCASDetermineAltitude tcasDetermineAltitude) {
        LogEngine.instance.write("+ Body.receive(" + tcasDetermineAltitude.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasDetermineAltitude.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTCAS ; i++) {
                Method determineAltitudeMethod = tcasPortList.get(i).getClass().getDeclaredMethod("determineAltitude", String.class);
                LogEngine.instance.write("determineAltitudeMethod = " + determineAltitudeMethod);

                int altitude = (int) determineAltitudeMethod.invoke(tcasPortList.get(i), tcasDetermineAltitude.getEnvironment());
                LogEngine.instance.write("altitude = " + altitude);

                PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
                FlightRecorder.instance.insert("Body", "Tcas (altitude): " + altitude);

                if (altitude < 300) {
                    // lower than 300m -> Alarm
                    boolean alarm = (boolean) tcasPortList.get(i).getClass().getDeclaredMethod("alarm").invoke(tcasPortList.get(i));
                    FlightRecorder.instance.insert("Body", "Tcas (alarm): " + alarm);

                    PrimaryFlightDisplay.instance.isTCASAlarm = alarm;

                    LogEngine.instance.write("PrimaryFlightDisplay (isTCASAlarm): " + PrimaryFlightDisplay.instance.isTCASAlarm);
                    FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASAlarm: " + PrimaryFlightDisplay.instance.isTCASAlarm);
                }

                LogEngine.instance.write("+");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        LogEngine.instance.write("TCAS (altitudeTCAS): " + PrimaryFlightDisplay.instance.altitudeTCAS);
        FlightRecorder.instance.insert("TCAS", "altitudeTCAS: " + PrimaryFlightDisplay.instance.amountOfOxygen);
    }

    // --- TurbulentAirFlowSensor -------------------------------------------------------------------------------------

    @Subscribe
    public void receive (TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0 ; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody ; i++) {
                Method measureMethod = turbulentAirFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int airFlow = (int) measureMethod.invoke(turbulentAirFlowSensorPortList.get(i), "laminarturbulentturbulentlaminarwindwindturbulent");
                LogEngine.instance.write("airFlow = " + airFlow);

                if (airFlow > 10) {
                    // Turbulences -> Alarm
                    boolean alarm = (boolean) turbulentAirFlowSensorPortList.get(i).getClass().getDeclaredMethod("alarm").invoke(turbulentAirFlowSensorPortList.get(i));
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