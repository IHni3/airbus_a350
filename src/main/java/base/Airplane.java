package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.business_class_seat.NonSmokingSignOff;
import event.business_class_seat.NonSmokingSignOn;
import event.business_class_seat.SeatBeltSignOff;
import event.business_class_seat.SeatBeltSignOn;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.gps.*;
import event.ice_detector_probe.IceDetectorProbeBodyActivate;
import event.ice_detector_probe.IceDetectorProbeBodyDeactivate;
import event.ice_detector_probe.IceDetectorProbeWingActivate;
import event.ice_detector_probe.IceDetectorProbeWingDeactivate;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
import event.taxi_light.TaxiLightOff;
import event.taxi_light.TaxiLightOn;
import event.tcas.*;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

@SuppressWarnings({"UnstableApiUsage", "FieldCanBeLocal", "FieldMayBeFinal"})
public class Airplane implements IAirplane {
    private EventBus eventBus;
    private Body body;
    private Wing leftWing;
    private Wing rightWing;

    public Airplane() {
        eventBus = new EventBus("EB-A350");
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void build() {
        body = new Body();
        addSubscriber(body);

        leftWing = new Wing();
        addSubscriber(leftWing);

        rightWing = new Wing();
        addSubscriber(rightWing);
    }

    public void startup() {
        //camera
        eventBus.post(new CameraBodyOn());
        eventBus.post(new CameraWingOn());

        // gps
        eventBus.post(new GPSOn());
        eventBus.post(new GPSConnect("euro-4"));

        // nitrogen_bottle (Consume for Startup and refill before starting)
        eventBus.post(new NitrogenBottleTakeOut(20));
        eventBus.post(new NitrogenBottleRefill());

        // oxygen_bottle
        eventBus.post(new OxygenBottleRefill());

        // tcas
        eventBus.post(new TCASOn());
        eventBus.post(new TCASConnect("118.75"));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOff());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOff());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    // Assuming taxi means bringing the plane to takeoff-lane
    public void taxi() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(5));

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOn());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOff());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOn());
    }

    public void takeoff() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas (do not scan for altitude yet to prevent unnecessary alarm)
        eventBus.post(new TCASScan("trees"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOff());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void climbing() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas
        eventBus.post(new TCASDetermineAltitude("alt=400m"));
        eventBus.post(new TCASScan("clouds"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOff());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void rightTurn() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas
        eventBus.post(new TCASDetermineAltitude("alt=1000m"));
        eventBus.post(new TCASScan("plain sky"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOn());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void leftTurn() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas
        eventBus.post(new TCASDetermineAltitude("alt=1000m"));
        eventBus.post(new TCASScan("plain sky"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOn());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void descent() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas
        eventBus.post(new TCASDetermineAltitude("alt=400m"));
        eventBus.post(new TCASScan("clouds"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOn());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void landing() {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(10));

        // oxygen_bottle
        eventBus.post(new OxygenBottleTakeOut(2));

        // tcas (do not scan for altitude when landing to prevent unnecessary alarm)
        eventBus.post(new TCASScan("trees"));

        // turbulent_air_flow_sensor
        eventBus.post(new TurbulentAirFlowSensorBodyMeasure());
        eventBus.post(new TurbulentAirFlowSensorWingMeasure());

        // weather_radar
        eventBus.post(new WeatherRadarOn());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOn());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());

        // business_class_seat
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOn());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }

    public void shutdown() {
        //camera
        eventBus.post(new CameraBodyOff());
        eventBus.post(new CameraWingOff());

        // gps
        eventBus.post(new GPSOff());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleRefill());

        // oxygen_bottle
        eventBus.post(new OxygenBottleRefill());

        // tcas
        eventBus.post(new TCASOff());

        // weather_radar
        eventBus.post(new WeatherRadarOff());

        // exhaust_gas_temperature_sensor
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());

        // fuel_flow_sensor
        eventBus.post(new FuelFlowSensorMeasure());

        // right_navigation_light
        eventBus.post(new RightNavigationLightOff());

        // tail_navigation_light
        eventBus.post(new TailNavigationLightOff());

        // business_class_seat
        eventBus.post(new NonSmokingSignOff());
        eventBus.post(new SeatBeltSignOff());

        // crew_seat
        eventBus.post(new event.crew_seat.NonSmokingSignOff());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());

        // economy_class_seat
        eventBus.post(new event.economy_class_seat.NonSmokingSignOff());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());

        // ice_detector_probe
        eventBus.post(new IceDetectorProbeBodyDeactivate());
        eventBus.post(new IceDetectorProbeWingDeactivate());

        // taxi_light
        eventBus.post(new TaxiLightOff());
    }
}
