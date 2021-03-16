package section;

import base.CrewMember;
import base.Passenger;
import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.business_class_seat.SeatAssignPassenger;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.crew_seat.SeatAssignCrewMember;
import event.economy_class_seat.*;
import event.fire_detector.FireDetectorBodyScan;
import event.gps.*;
import event.ice_detector_probe.IceDetectorProbeBodyActivate;
import event.ice_detector_probe.IceDetectorProbeBodyDeactivate;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.oxygen_sensor.OxygenSensorMeasure;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureStaticPressure;
import event.pitot_tube.PitotTubeMeasureTotalPressure;
import event.pitot_tube.PitotTubeMeasureVelocity;
import event.radar_altimeter.*;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
import event.taxi_light.TaxiLightOff;
import event.taxi_light.TaxiLightOn;
import event.tcas.*;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings({"FieldMayBeFinal", "UnstableApiUsage", "unused"})
public class Body extends Subscriber {
    private ArrayList<Object> aPUPortList;
    private ArrayList<Object> businessClassSeatPortList;
    private ArrayList<Object> cameraPortList;
    private ArrayList<Object> crewSeatPortList;
    private ArrayList<Object> economyClassSeatPortList;
    private ArrayList<Object> fireDetectorPortList;
    private ArrayList<Object> gearPortList;
    private ArrayList<Object> gpsPortList;
    private ArrayList<Object> hydraulicPumpPortList;
    private ArrayList<Object> iceDetectorProbePortList;
    private ArrayList<Object> nitrogenBottlePortList;
    private ArrayList<Object> oxygenBottlePortList;
    private ArrayList<Object> oxygenSensorPortList;
    private ArrayList<Object> tailNavigationLightPortList;
    private ArrayList<Object> taxiLightPortList;
    private ArrayList<Object> tcasPortList;
    private ArrayList<Object> turbulentAirFlowSensorPortList;
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> pitotTubePortList;
    private ArrayList<Object> radarAltimeterPortList;

    public Body() {
        aPUPortList = new ArrayList<>();
        cameraPortList = new ArrayList<>();
        gearPortList = new ArrayList<>();
        gpsPortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        nitrogenBottlePortList = new ArrayList<>();
        oxygenBottlePortList = new ArrayList<>();
        tcasPortList = new ArrayList<>();
        turbulentAirFlowSensorPortList = new ArrayList<>();
        weatherRadarPortList = new ArrayList<>();
        pitotTubePortList = new ArrayList<>();
        radarAltimeterPortList = new ArrayList<>();
        businessClassSeatPortList = new ArrayList<>();
        crewSeatPortList = new ArrayList<>();
        economyClassSeatPortList = new ArrayList<>();
        fireDetectorPortList = new ArrayList<>();
        iceDetectorProbePortList = new ArrayList<>();
        oxygenSensorPortList = new ArrayList<>();
        tailNavigationLightPortList = new ArrayList<>();
        taxiLightPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
            aPUPortList.add(APUFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
            businessClassSeatPortList.add(BusinessClassSeatFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfCameraBody ; i++) {
            cameraPortList.add(setCameraType(CameraFactory.build()));
        }
        for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
            crewSeatPortList.add(CrewSeatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
            economyClassSeatPortList.add(EconomyClassSeatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfFireDetectorProbeBody; i++) {
            fireDetectorPortList.add(FireDetectorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
            gearPortList.add(GearFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfGps ; i++) {
            gpsPortList.add(GpsFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
            iceDetectorProbePortList.add(IceDetectorProbeFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfNitrogen ; i++) {
            nitrogenBottlePortList.add(NitrogenBottleFactory.build());
        }
        for (int i = 0 ; i < Configuration.instance.numberOfOxygen ; i++) {
            oxygenBottlePortList.add(OxygenBottleFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfOxygenSensor; i++) {
            oxygenSensorPortList.add(OxygenSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
            tailNavigationLightPortList.add(TailNavigationLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfTaxiLight; i++) {
            taxiLightPortList.add(TaxiLightFactory.build());
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

        for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++) {
            pitotTubePortList.add(PitotTubeFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
            radarAltimeterPortList.add(RadarAltimeterFactory.build());
        }
    }

    // ----------------------------------------------------------------------------------------------------------------
    // --- APU --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(APUStart aPUStart) {
        LogEngine.instance.write("+ Body.receive(" + aPUStart.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method startMethod = aPUPortList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("startMethod = " + startMethod);

                boolean isStarted = (boolean) startMethod.invoke(aPUPortList.get(i));
                LogEngine.instance.write("isStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
                FlightRecorder.instance.insert("Body", "APU (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUShutdown aPUShutdown) {
        LogEngine.instance.write("+ Body.receive(" + aPUShutdown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method shutdownMethod = aPUPortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUIncreaseRPM aPUIncreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + aPUIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method increaseRPMMethod = aPUPortList.get(i).getClass().getDeclaredMethod("increaseRPM", Integer.TYPE);
                LogEngine.instance.write("increaseRPMMethod = " + increaseRPMMethod);

                int rpm = (int) increaseRPMMethod.invoke(aPUPortList.get(i), aPUIncreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    @Subscribe
    public void receive(APUDecreaseRPM aPUDecreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + aPUDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method decreaseRPMMethod = aPUPortList.get(i).getClass().getDeclaredMethod("decreaseRPM", Integer.TYPE);
                LogEngine.instance.write("decreaseRPMMethod = " + decreaseRPMMethod);

                int rpm = (int) decreaseRPMMethod.invoke(aPUPortList.get(i), aPUDecreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    // --- BusinessClassSeat ------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SeatAssignPassenger seatAssignPassenger) {
        LogEngine.instance.write("+ Body.receive(" + seatAssignPassenger.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatAssignPassenger.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method assignMethod = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("assign", Passenger.class);
                LogEngine.instance.write("assignMethod = " + assignMethod);

                int level = (int) assignMethod.invoke(businessClassSeatPortList.get(i), seatAssignPassenger.getPassenger());
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelSeat = level;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelSeat): " + PrimaryFlightDisplay.instance.levelSeat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelSeat: " + PrimaryFlightDisplay.instance.levelSeat);
    }

    @Subscribe
    public void receive(event.business_class_seat.NonSmokingSignOn nonSmokingSignOn) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method nonSmokingSignOnMethod = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOn");
                LogEngine.instance.write("nonSmokingSignOnMethod = " + nonSmokingSignOnMethod);

                boolean isOn = (boolean) nonSmokingSignOnMethod.invoke(businessClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.business_class_seat.NonSmokingSignOff nonSmokingSignOff) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method nonSmokingSignOffMethod = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOff");
                LogEngine.instance.write("nonSmokingSignOffMethod = " + nonSmokingSignOffMethod);

                boolean isOn = (boolean) nonSmokingSignOffMethod.invoke(businessClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.business_class_seat.SeatBeltSignOn seatBeltSignOn) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method seatBeltSignOnMethod = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOn");
                LogEngine.instance.write("seatBeltSignOnMethod = " + seatBeltSignOnMethod);

                boolean isOn = (boolean) seatBeltSignOnMethod.invoke(businessClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(event.business_class_seat.SeatBeltSignOff seatBeltSignOff) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method seatBeltSignOffMethod = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOff");
                LogEngine.instance.write("seatBeltSignOffMethod = " + seatBeltSignOffMethod);

                boolean isOn = (boolean) seatBeltSignOffMethod.invoke(businessClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(event.business_class_seat.SeatLevelOne seatLevelOne) {
        LogEngine.instance.write("+ Body.receive(" + seatLevelOne.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatLevelOne.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
                Method level1Method = businessClassSeatPortList.get(i).getClass().getDeclaredMethod("level1");
                LogEngine.instance.write("level1Method = " + level1Method);

                int level = (int) level1Method.invoke(businessClassSeatPortList.get(i));
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelSeat = level;
                FlightRecorder.instance.insert("Body", "BusinessClassSeat (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelSeat): " + PrimaryFlightDisplay.instance.levelSeat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelSeat: " + PrimaryFlightDisplay.instance.levelSeat);
    }

    // --- Camera -----------------------------------------------------------------------------------------------------
    private Object setCameraType(Object cameraPort) {
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
    public void receive(CameraBodyOn cameraBodyOn) {
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
    public void receive(CameraBodyOff cameraBodyOff) {
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

    // --- CrewSeat ---------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SeatAssignCrewMember seatAssignCrewMember) {
        LogEngine.instance.write("+ Body.receive(" + seatAssignCrewMember.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatAssignCrewMember.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method assignMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("assign", CrewMember.class);
                LogEngine.instance.write("assignMethod = " + assignMethod);

                boolean result = (boolean) assignMethod.invoke(crewSeatPortList.get(i), seatAssignCrewMember.getCrewMember());
                LogEngine.instance.write("result = " + result);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = result;
                FlightRecorder.instance.insert("Body", "CrewSeat (result): " + result);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.NonSmokingSignOn nonSmokingSignOn) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method nonSmokingSignOnMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOn");
                LogEngine.instance.write("nonSmokingSignOnMethod = " + nonSmokingSignOnMethod);

                boolean isOn = (boolean) nonSmokingSignOnMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.NonSmokingSignOff nonSmokingSignOff) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method nonSmokingSignOffMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOff");
                LogEngine.instance.write("nonSmokingSignOffMethod = " + nonSmokingSignOffMethod);

                boolean isOn = (boolean) nonSmokingSignOffMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.SeatBeltSignOn seatBeltSignOn) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method beltSignOnMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("beltSignOn");
                LogEngine.instance.write("beltSignOnMethod = " + beltSignOnMethod);

                boolean isOn = (boolean) beltSignOnMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(event.crew_seat.SeatBeltSignOff seatBeltSignOff) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCrewSeat; i++) {
                Method beltSignOffMethod = crewSeatPortList.get(i).getClass().getDeclaredMethod("beltSignOff");
                LogEngine.instance.write("beltSignOffMethod = " + beltSignOffMethod);

                boolean isOn = (boolean) beltSignOffMethod.invoke(crewSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "CrewSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    // --- EconomyClassSeat -------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(event.economy_class_seat.SeatAssignPassenger seatAssignPassenger) {
        LogEngine.instance.write("+ Body.receive(" + seatAssignPassenger.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatAssignPassenger.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method assignMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("assign", Passenger.class);
                LogEngine.instance.write("assignMethod = " + assignMethod);

                int level = (int) assignMethod.invoke(economyClassSeatPortList.get(i), seatAssignPassenger.getPassenger());
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelSeat = level;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelSeat): " + PrimaryFlightDisplay.instance.levelSeat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelSeat: " + PrimaryFlightDisplay.instance.levelSeat);
    }

    @Subscribe
    public void receive(NonSmokingSignOn nonSmokingSignOn) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method nonSmokingSignOnMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOn");
                LogEngine.instance.write("nonSmokingSignOnMethod = " + nonSmokingSignOnMethod);

                boolean isOn = (boolean) nonSmokingSignOnMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(NonSmokingSignOff nonSmokingSignOff) {
        LogEngine.instance.write("+ Body.receive(" + nonSmokingSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + nonSmokingSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method nonSmokingSignOffMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("nonSmokingSignOff");
                LogEngine.instance.write("nonSmokingSignOffMethod = " + nonSmokingSignOffMethod);

                boolean isOn = (boolean) nonSmokingSignOffMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isNonSmokingSignOn): " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isNonSmokingSignOn: " + PrimaryFlightDisplay.instance.isNonSmokingSignOn);
    }

    @Subscribe
    public void receive(SeatBeltSignOn seatBeltSignOn) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method seatBeltSignOnMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOn");
                LogEngine.instance.write("seatBeltSignOnMethod = " + seatBeltSignOnMethod);

                boolean isOn = (boolean) seatBeltSignOnMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(SeatBeltSignOff seatBeltSignOff) {
        LogEngine.instance.write("+ Body.receive(" + seatBeltSignOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatBeltSignOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method seatBeltSignOffMethod = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("seatBeltSignOff");
                LogEngine.instance.write("seatBeltSignOffMethod = " + seatBeltSignOffMethod);

                boolean isOn = (boolean) seatBeltSignOffMethod.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isSeatBeltSignOn): " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isSeatBeltSignOn: " + PrimaryFlightDisplay.instance.isSeatBeltSignOn);
    }

    @Subscribe
    public void receive(SeatLevelOne seatLevelOne) {
        LogEngine.instance.write("+ Body.receive(" + seatLevelOne.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + seatLevelOne.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEconomyClassSeat; i++) {
                Method level1Method = economyClassSeatPortList.get(i).getClass().getDeclaredMethod("level1");
                LogEngine.instance.write("level1Method = " + level1Method);

                int level = (int) level1Method.invoke(economyClassSeatPortList.get(i));
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelSeat = level;
                FlightRecorder.instance.insert("Body", "EconomyClassSeat (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelSeat): " + PrimaryFlightDisplay.instance.levelSeat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelSeat: " + PrimaryFlightDisplay.instance.levelSeat);
    }

    // --- FireDetector -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FireDetectorBodyScan fireDetectorBodyScan) {
        LogEngine.instance.write("+ Body.receive(" + fireDetectorBodyScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fireDetectorBodyScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFireDetectorProbeBody; i++) {
                Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean alarm = (boolean) scanMethod.invoke(fireDetectorPortList.get(i), fireDetectorBodyScan.getAir());
                LogEngine.instance.write("alarm = " + alarm);

                PrimaryFlightDisplay.instance.isFireDetectedBody = alarm;
                FlightRecorder.instance.insert("Body", "FireDetector (alarm): " + alarm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedBody): " + PrimaryFlightDisplay.instance.isFireDetectedBody);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedBody: " + PrimaryFlightDisplay.instance.isFireDetectedBody);
    }

    // --- GPS --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(GPSOn gpsOn) {
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
    public void receive(GPSOff gpsOff) {
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
    public void receive(GPSConnect gpsConnect) {
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
    public void receive(GPSSend gpsSend) {
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
    public void receive(GPSReceive gpsReceive) {
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

    // --- IceDetectorProbe -------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(IceDetectorProbeBodyActivate iceDetectorProbeBodyActivate) {
        LogEngine.instance.write("+ Body.receive(" + iceDetectorProbeBodyActivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + iceDetectorProbeBodyActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
                Method activateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean isActivated = (boolean) activateMethod.invoke(iceDetectorProbePortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = isActivated;
                FlightRecorder.instance.insert("Body", "IceDetectorProbe (isActivated): " + isActivated);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeBodyActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeBodyActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
    }

    @Subscribe
    public void receive(IceDetectorProbeBodyDeactivate iceDetectorProbeBodyDeactivate) {
        LogEngine.instance.write("+ Body.receive(" + iceDetectorProbeBodyDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + iceDetectorProbeBodyDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
                Method deactivateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean isActivated = (boolean) deactivateMethod.invoke(iceDetectorProbePortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = isActivated;
                FlightRecorder.instance.insert("Body", "IceDetectorProbe (isActivated): " + isActivated);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeBodyActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeBodyActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
    }

    // --- NitrogenBottle ---------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(NitrogenBottleRefill nitrogenBottleRefill) {
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
    public void receive(NitrogenBottleTakeOut nitrogenBottleTakeOut) {
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
    public void receive(OxygenBottleRefill oxygenBottleRefill) {
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
    public void receive(OxygenBottleTakeOut oxygenBottleTakeOut) {
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

    // --- OxygenSensor -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(OxygenSensorMeasure oxygenSensorMeasure) {
        LogEngine.instance.write("+ Body.receive(" + oxygenSensorMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + oxygenSensorMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfOxygenSensor; i++) {
                Method measureMethod = oxygenSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                boolean oxygen = (boolean) measureMethod.invoke(oxygenSensorPortList.get(i), oxygenSensorMeasure.getAirFlow());
                LogEngine.instance.write("oxygen = " + oxygen);

                PrimaryFlightDisplay.instance.isOxgenSensorAlarm = oxygen;
                FlightRecorder.instance.insert("Body", "OxygenSensor (oxygen): " + oxygen);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isOxgenSensorAlarm): " + PrimaryFlightDisplay.instance.isOxgenSensorAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isOxgenSensorAlarm: " + PrimaryFlightDisplay.instance.isOxgenSensorAlarm);
    }

    // --- TailNavigationLight ----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TailNavigationLightOn tailNavigationLightOn) {
        LogEngine.instance.write("+ Body.receive(" + tailNavigationLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tailNavigationLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
                Method onMethod = tailNavigationLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(tailNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTailNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Body", "TailNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTailNavigationLightOn): " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTailNavigationLightOn: " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
    }

    @Subscribe
    public void receive(TailNavigationLightOff tailNavigationLightOff) {
        LogEngine.instance.write("+ Body.receive(" + tailNavigationLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tailNavigationLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
                Method offMethod = tailNavigationLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(tailNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTailNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Body", "TailNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTailNavigationLightOn): " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTailNavigationLightOn: " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
    }

    // --- TaxiLight --------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TaxiLightOn taxiLightOn) {
        LogEngine.instance.write("+ Body.receive(" + taxiLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + taxiLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTaxiLight; i++) {
                Method onMethod = taxiLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(taxiLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTaxiLightOn = isOn;
                FlightRecorder.instance.insert("Body", "TaxiLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTaxiLightOn): " + PrimaryFlightDisplay.instance.isTaxiLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTaxiLightOn: " + PrimaryFlightDisplay.instance.isTaxiLightOn);
    }

    @Subscribe
    public void receive(TaxiLightOff taxiLightOff) {
        LogEngine.instance.write("+ Body.receive(" + taxiLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + taxiLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTaxiLight; i++) {
                Method offMethod = taxiLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(taxiLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTaxiLightOn = isOn;
                FlightRecorder.instance.insert("Body", "TaxiLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTaxiLightOn): " + PrimaryFlightDisplay.instance.isTaxiLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTaxiLightOn: " + PrimaryFlightDisplay.instance.isTaxiLightOn);
    }

    // --- TCAS -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TCASOn tcasOn) {
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
    public void receive(TCASOff tcasOff) {
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
    public void receive(TCASConnect tcasConnect) {
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
    public void receive(TCASScan tcasScan) {
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
    public void receive(TCASDetermineAltitude tcasDetermineAltitude) {
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
    public void receive(TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
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
    public void receive(WeatherRadarOn weatherRadarOn) {
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
    public void receive(WeatherRadarOff weatherRadarOff) {
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
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    //pitot_tube
    @Subscribe
    public void receive(PitotTubeClean pitotTubeClean) {
        System.out.println(pitotTubeClean);

        LogEngine.instance.write("+ Body.receive(" + pitotTubeClean.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + pitotTubeClean.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++) {
                Method cleanMethod = pitotTubePortList.get(i).getClass().getDeclaredMethod("clean");
                LogEngine.instance.write("cleanMethod = " + cleanMethod);

                cleanMethod.invoke(pitotTubePortList.get(i));
                LogEngine.instance.write("isClean = " + true);

                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                FlightRecorder.instance.insert("Body", "Pitot Tube (isClean): " + true);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isPitotTubeCleaned): " + PrimaryFlightDisplay.instance.isPitotTubeCleaned);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isPitotTubeCleaned: " + PrimaryFlightDisplay.instance.isPitotTubeCleaned);
    }

    @Subscribe
    public void receive(PitotTubeMeasureStaticPressure pitotTubeMeasureStaticPressure) {
        System.out.println(pitotTubeMeasureStaticPressure);
        LogEngine.instance.write("+ Body.receive(" + pitotTubeMeasureStaticPressure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + pitotTubeMeasureStaticPressure.toString() + ")");
    }

    @Subscribe
    public void receive(PitotTubeMeasureTotalPressure pitotTubeMeasureTotalPressure) {
        System.out.println(pitotTubeMeasureTotalPressure);
        LogEngine.instance.write("+ Body.receive(" + pitotTubeMeasureTotalPressure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + pitotTubeMeasureTotalPressure.toString() + ")");
    }

    @Subscribe
    public void receive(PitotTubeMeasureVelocity pitotTubeMeasureVelocity) {
        System.out.println(pitotTubeMeasureVelocity);

        LogEngine.instance.write("+ Body.receive(" + pitotTubeMeasureVelocity.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + pitotTubeMeasureVelocity.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++) {
                Method measureVelocityMethod = pitotTubePortList.get(i).getClass().getDeclaredMethod("measureVelocity");
                LogEngine.instance.write("measureVelocityMethod = " + measureVelocityMethod);

                int velocity = (int) measureVelocityMethod.invoke(pitotTubePortList.get(i));
                LogEngine.instance.write("isClean = " + velocity);

                PrimaryFlightDisplay.instance.velocity = velocity;
                FlightRecorder.instance.insert("Body", "Pitot Tube (velocity): " + velocity);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (velocity): " + PrimaryFlightDisplay.instance.velocity);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "velocity: " + PrimaryFlightDisplay.instance.velocity);
    }

    //radar_altimeter
    @Subscribe
    public void receive(RadarAltimeterSend radarAltimeterSend) {
        System.out.println(radarAltimeterSend);
        LogEngine.instance.write("+ Body.receive(" + radarAltimeterSend.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarAltimeterSend.toString() + ")");

        //needs to execute function to give information to measureAltitude
        try {
            for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
                Method sendMethod = radarAltimeterPortList.get(i).getClass().getDeclaredMethod("send", String.class);
                LogEngine.instance.write("sendMethod = " + sendMethod);

                sendMethod.invoke(radarAltimeterPortList.get(i), radarAltimeterSend.getRadioWave());
                LogEngine.instance.write("send Radiowave: " + radarAltimeterSend.getRadioWave());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(RadarAltimeterReceive radarAltimeterReceive) {
        System.out.println(radarAltimeterReceive);
        LogEngine.instance.write("+ Body.receive(" + radarAltimeterReceive.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarAltimeterReceive.toString() + ")");

        //needs to execute function to give information to measureAltitude
        try {
            for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
                Method receiveMethod = radarAltimeterPortList.get(i).getClass().getDeclaredMethod("receive", String.class);
                LogEngine.instance.write("receiveMethod = " + receiveMethod);

                receiveMethod.invoke(radarAltimeterPortList.get(i), radarAltimeterReceive.getRadioWave());
                LogEngine.instance.write("received Radiowave: " + radarAltimeterReceive.getRadioWave());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(RadarAltimeterOn radarAltimeterOn) {
        System.out.println(radarAltimeterOn);
        LogEngine.instance.write("+ Body.receive(" + radarAltimeterOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarAltimeterOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
                Method onMethod = radarAltimeterPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(radarAltimeterPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRadarAltimeterOn = isOn;
                FlightRecorder.instance.insert("Body", "RadarAltimeter (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRadarAltimeterOn): " + PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRadarAltimeterOn: " + PrimaryFlightDisplay.instance.isRadarAltimeterOn);
    }

    @Subscribe
    public void receive(RadarAltimeterOff radarAltimeterOff) {
        System.out.println(radarAltimeterOff);
        LogEngine.instance.write("+ Body.receive(" + radarAltimeterOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarAltimeterOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
                Method offMethod = radarAltimeterPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(radarAltimeterPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRadarAltimeterOn = isOn;
                FlightRecorder.instance.insert("Body", "RadarAltimeter (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRadarAltimeterOn): " + PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRadarAltimeterOn: " + PrimaryFlightDisplay.instance.isRadarAltimeterOn);
    }

    @Subscribe
    public void receive(RadarAlitmeterMeasureAltitude radarAlitmeterMeasureAltitude) {
        System.out.println(radarAlitmeterMeasureAltitude);
        LogEngine.instance.write("+ Body.receive(" + radarAlitmeterMeasureAltitude.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + radarAlitmeterMeasureAltitude.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
                Method measureAltitudeMethod = radarAltimeterPortList.get(i).getClass().getDeclaredMethod("measureAltitude");
                LogEngine.instance.write("measureAltitudeMethod = " + measureAltitudeMethod);

                int altitude = (int) measureAltitudeMethod.invoke(radarAltimeterPortList.get(i));
                LogEngine.instance.write("altitude = " + altitude);

                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
                FlightRecorder.instance.insert("Body", "RadarAltimeter (altitude): " + altitude);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (altitudeRadarAltimeter): " + PrimaryFlightDisplay.instance.altitudeRadarAltimeter);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "altitudeRadarAltimeter: " + PrimaryFlightDisplay.instance.altitudeRadarAltimeter);
    }
}