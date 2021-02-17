package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.slat.SlatUp;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Body extends Subscriber {
    private List<Object> weatherRadarPortList;
    private List<Object> antiCollisionLightPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLights; i++) {
            antiCollisionLightPortList.add(WeatherRadarFactory.build());
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



    // --- AntiCollisionLight -----------------------------------------------------------------------------------------
    @Subscribe
    public void receive(AntiCollisionLightOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), antiCollisionLightPortList, "AntiCollisionLight", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setAntiCollisionLightEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(AntiCollisionLightOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), antiCollisionLightPortList, "AntiCollisionLight", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setAntiCollisionLightEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- CargoCompartmentLight --------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- LandingLight -----------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- CostOptimizer ----------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- RouteManagement --------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------




}