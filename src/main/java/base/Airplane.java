package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureStaticPressure;
import event.pitot_tube.PitotTubeMeasureTotalPressure;
import event.radar_altimeter.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

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
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankRefill());
        eventBus.post(new PitotTubeClean());
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(1));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(10));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(4));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(2));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(2));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(1));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new FuelTankTakeOut(1));
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("ping"));
        eventBus.post(new RadarAltimeterReceive("ping"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
        eventBus.post(new FuelTankRefill());
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new PitotTubeClean());
        eventBus.post(new RadarAltimeterOff());
    }
}