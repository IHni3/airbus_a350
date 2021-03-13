package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureStaticPressure;
import event.pitot_tube.PitotTubeMeasureTotalPressure;
import event.pitot_tube.PitotTubeMeasureVelocity;
import event.radar_altimeter.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.PitotTubeFactory;
import factory.RadarAltimeterFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> pitotTubePortList;
    private ArrayList<Object> radarAltimeterPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        pitotTubePortList = new ArrayList<>();
        radarAltimeterPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++) {
            pitotTubePortList.add(PitotTubeFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++) {
            radarAltimeterPortList.add(RadarAltimeterFactory.build());
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