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
import org.checkerframework.checker.units.qual.A;
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

    //Pitot Tube
    @Subscribe
    public void receive(PitotTubeClean pitotTubeClean) {
        System.out.println(pitotTubeClean);
    }

    @Subscribe
    public void receive(PitotTubeMeasureStaticPressure pitotTubeMeasureStaticPressure) {
        System.out.println(pitotTubeMeasureStaticPressure);
    }

    @Subscribe
    public void receive(PitotTubeMeasureTotalPressure pitotTubeMeasureTotalPressure) {
        System.out.println(pitotTubeMeasureTotalPressure);
    }

    @Subscribe
    public void receive(PitotTubeMeasureVelocity pitotTubeMeasureVelocity) {
        System.out.println(pitotTubeMeasureVelocity);
    }

    //Radar Altimeter
    @Subscribe
    public void receive(RadarAltimeterSend radarAltimeterSend) {
        System.out.println(radarAltimeterSend);
    }

    @Subscribe
    public void receive(RadarAltimeterReceive radarAltimeterReceive) {
        System.out.println(radarAltimeterReceive);
    }

    @Subscribe
    public void receive(RadarAltimeterOn radarAltimeterOn) {
        System.out.println(radarAltimeterOn);
    }

    @Subscribe
    public void receive(RadarAltimeterOff radarAltimeterOff) {
        System.out.println(radarAltimeterOff);
    }

    @Subscribe
    public void receive(RadarAlitmeterMeasureAltitude radarAlitmeterMeasureAltitude) {
        System.out.println(radarAlitmeterMeasureAltitude);
    }
}