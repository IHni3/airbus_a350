package section;

import base.CrewMember;
import base.Passenger;
import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.business_class_seat.SeatAssignPassenger;
import event.crew_seat.SeatAssignCrewMember;
import event.economy_class_seat.*;
import event.fire_detector.FireDetectorBodyScan;
import event.fire_detector.FireDetectorWingScan;
import event.ice_detector_probe.IceDetectorProbeBodyActivate;
import event.ice_detector_probe.IceDetectorProbeBodyDeactivate;
import event.ice_detector_probe.IceDetectorProbeWingActivate;
import event.ice_detector_probe.IceDetectorProbeWingDeactivate;
import event.oxygen_sensor.OxygenSensorMeasure;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
import event.taxi_light.TaxiLightOff;
import event.taxi_light.TaxiLightOn;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> businessClassSeatPortList;
    private ArrayList<Object> crewSeatPortList;
    private ArrayList<Object> economyClassSeatPortList;
    private ArrayList<Object> fireDetectorPortList;
    private ArrayList<Object> iceDetectorProbePortList;
    private ArrayList<Object> oxygenSensorPortList;
    private ArrayList<Object> tailNavigationLightPortList;
    private ArrayList<Object> taxiLightPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
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
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfBusinessClassSeat; i++) {
            businessClassSeatPortList.add(BusinessClassSeatFactory.build());
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
        for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
            iceDetectorProbePortList.add(IceDetectorProbeFactory.build());
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
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
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
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
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

    // ----------------------------------------------------------------------------------------------------------------

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

    // ----------------------------------------------------------------------------------------------------------------

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

    // ----------------------------------------------------------------------------------------------------------------

    // --- FireDetector -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FireDetectorBodyScan fireDetectorBodyScan) {
        LogEngine.instance.write("+ Body.receive(" + fireDetectorBodyScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fireDetectorBodyScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFireDetectorBody; i++) {
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

    @Subscribe
    public void receive(FireDetectorWingScan fireDetectorWingScan) {
        LogEngine.instance.write("+ Body.receive(" + fireDetectorWingScan.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fireDetectorWingScan.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFireDetectorBody; i++) {
                Method scanMethod = fireDetectorPortList.get(i).getClass().getDeclaredMethod("scan", String.class);
                LogEngine.instance.write("scanMethod = " + scanMethod);

                boolean alarm = (boolean) scanMethod.invoke(fireDetectorPortList.get(i), fireDetectorWingScan.getAir());
                LogEngine.instance.write("alarm = " + alarm);

                PrimaryFlightDisplay.instance.isFireDetectedWing = alarm;
                FlightRecorder.instance.insert("Body", "FireDetector (alarm): " + alarm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isFireDetectedWing): " + PrimaryFlightDisplay.instance.isFireDetectedWing);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isFireDetectedWing: " + PrimaryFlightDisplay.instance.isFireDetectedWing);
    }

    // ----------------------------------------------------------------------------------------------------------------

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
    public void receive(IceDetectorProbeWingActivate iceDetectorProbeWingActivate) {
        LogEngine.instance.write("+ Body.receive(" + iceDetectorProbeWingActivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + iceDetectorProbeWingActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
                Method activateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean isActivated = (boolean) activateMethod.invoke(iceDetectorProbePortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = isActivated;
                FlightRecorder.instance.insert("Body", "IceDetectorProbe (isActivated): " + isActivated);

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

    @Subscribe
    public void receive(IceDetectorProbeWingDeactivate iceDetectorProbeWingDeactivate) {
        LogEngine.instance.write("+ Body.receive(" + iceDetectorProbeWingDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + iceDetectorProbeWingDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfIceDetectorProbeBody; i++) {
                Method deactivateMethod = iceDetectorProbePortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean isActivated = (boolean) deactivateMethod.invoke(iceDetectorProbePortList.get(i));
                LogEngine.instance.write("isActivated = " + isActivated);

                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = isActivated;
                FlightRecorder.instance.insert("Body", "IceDetectorProbe (isActivated): " + isActivated);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isIceDetectorProbeWingActivated): " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isIceDetectorProbeWingActivated: " + PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
    }

    // ----------------------------------------------------------------------------------------------------------------

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

                PrimaryFlightDisplay.instance.isOxgenSensorAlarm = oxygen ? 1 : 0;
                FlightRecorder.instance.insert("Body", "OxygenSensor (oxygen): " + oxygen);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isOxgenSensorAlarm): " + PrimaryFlightDisplay.instance.isOxgenSensorAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isOxgenSensorAlarm: " + PrimaryFlightDisplay.instance.isOxgenSensorAlarm);
    }

    // ----------------------------------------------------------------------------------------------------------------

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

    // ----------------------------------------------------------------------------------------------------------------

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

    // ----------------------------------------------------------------------------------------------------------------


}
