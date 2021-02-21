package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import domains.CheckPoint;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.route_management.*;
import event.rudder.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Body extends Subscriber {
    private List<Object> weatherRadarPortList;
    private List<Object> antiCollisionLightPortList;
    private List<Object> landingLightPortList;
    private List<Object> costOptimizerPortList;
    private List<Object> routeManagementPortList;
    private List<Object> cargoCompartmentLightPortList;
    private List<Object> rudderPortList;


    public Body() {
        weatherRadarPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        costOptimizerPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();
        cargoCompartmentLightPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLights; i++) {
            antiCollisionLightPortList.add(AntiCollisionLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLandingLightsBody; i++) {
            landingLightPortList.add(LandingLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCostOptimizers; i++) {
            costOptimizerPortList.add(CostOptimizerFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRouteManagements; i++) {
            routeManagementPortList.add(RouteManagementFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLights; i++) {
            cargoCompartmentLightPortList.add(CargoCompartmentLightFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            rudderPortList.add(RudderFactory.build());
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
    @Subscribe
    public void receive(CargoCompartmentLightOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), cargoCompartmentLightPortList, "CargoCompartmentLight", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setCargoCompartmentLightEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CargoCompartmentLightOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), cargoCompartmentLightPortList, "CargoCompartmentLight", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setCargoCompartmentLightEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CargoCompartmentLightDim event) {
        ProcessEvent p = new ProcessEvent(event.toString(), cargoCompartmentLightPortList, "CargoCompartmentLight", "dim", "") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                method.invoke(port);
                //TODO add gui command
                return 0;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------


    // --- LandingLight -----------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(LandingLightBodyOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), landingLightPortList, "LandingLight", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setLandingLightBodyEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LandingLightBodyOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), landingLightPortList, "LandingLight", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setLandingLightBodyEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------


    // --- CostOptimizer ----------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(CostOptimizerOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setCostOptimizerEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CostOptimizerOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setCostOptimizerEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CostOptimizerAddCheckPoint event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "add", "size", CheckPoint.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int size = (int) method.invoke(port, event.getCheckpoint());
                PrimaryFlightDisplay.instance.setNumberOfCostOptimizerCheckpoints(size);
                return size;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CostOptimizerRemoveCheckPoint event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "remove", "size", CheckPoint.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int size = (int) method.invoke(port, event.getCheckpoint());
                PrimaryFlightDisplay.instance.setNumberOfCostOptimizerCheckpoints(size);
                return size;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CostOptimizerOptimize event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "optimize", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port, event.getCheckPointList());
                PrimaryFlightDisplay.instance.setIndexCostOptimizer(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(CostOptimizerValidate event) {
        ProcessEvent p = new ProcessEvent(event.toString(), costOptimizerPortList, "CostOptimizer", "validate", "valid") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean valid = (boolean) method.invoke(port);
                //todo i dont know what to call
                return valid;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------


    // --- RouteManagement --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(RouteManagementOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), routeManagementPortList, "RouteManagement", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setRouteManagementEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RouteManagementOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), routeManagementPortList, "RouteManagement", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setRouteManagementEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RouteManagementAdd event) {
        ProcessEvent p = new ProcessEvent(event.toString(), routeManagementPortList, "RouteManagement", "add", "size", CheckPoint.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int size = (int) method.invoke(port, event.getCheckpoint());
                PrimaryFlightDisplay.instance.setNumberOfCheckPointsRouteManagement(size);
                return size;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RouteManagementRemove event) {
        ProcessEvent p = new ProcessEvent(event.toString(), routeManagementPortList, "RouteManagement", "remove", "size", CheckPoint.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int size = (int) method.invoke(port, event.getCheckpoint());
                PrimaryFlightDisplay.instance.setNumberOfCheckPointsRouteManagement(size);
                return size;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RouteManagementSetCostIndex event) {
        ProcessEvent p = new ProcessEvent(event.toString(), routeManagementPortList, "RouteManagement", "setCostIndex", "value", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int value = (int) method.invoke(port, event.getCostIndex());
                PrimaryFlightDisplay.instance.setIndexRouteManagement(value);
                return value;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- Rudder --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(RudderNeutral event) {
        ProcessEvent p = new ProcessEvent(event.toString(), rudderPortList, "Rudder", "neutral", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRudder(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RudderFullLeft event) {
        ProcessEvent p = new ProcessEvent(event.toString(), rudderPortList, "Rudder", "fullleft", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRudder(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RudderFullRight event) {
        ProcessEvent p = new ProcessEvent(event.toString(), rudderPortList, "Rudder", "fullright", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRudder(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RudderLeft event) {
        ProcessEvent p = new ProcessEvent(event.toString(), rudderPortList, "Rudder", "left", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRudder(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RudderRight event) {
        ProcessEvent p = new ProcessEvent(event.toString(), rudderPortList, "Rudder", "right", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRudder(degree);
                return degree;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------
}