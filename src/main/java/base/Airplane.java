package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
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
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

public class Airplane implements IAirplane {
    private EventBus eventBus;
    private Body body;
    private Wing leftWing;
    private Wing rightWing;

    public Airplane () {
        eventBus = new EventBus("EB-A350");
    }

    public void addSubscriber (Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void build () {
        body = new Body();
        addSubscriber(body);

        leftWing = new Wing();
        addSubscriber(leftWing);

        rightWing = new Wing();
        addSubscriber(rightWing);
    }

    public void startup () {
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
    }

    // Assuming taxi means bringing the plane to takeoff-lane
    public void taxi () {
        // gps
        eventBus.post(new GPSSend("request data"));
        eventBus.post(new GPSReceive());

        // nitrogen_bottle
        eventBus.post(new NitrogenBottleTakeOut(5));

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff () {
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
    }

    public void climbing () {
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
    }

    public void rightTurn () {
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
    }

    public void leftTurn () {
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
    }

    public void descent () {
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
    }

    public void landing () {
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
    }

    public void shutdown () {
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
    }
}