package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureStaticPressure;
import event.pitot_tube.PitotTubeMeasureTotalPressure;
import event.pitot_tube.PitotTubeMeasureVelocity;
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
        //engine_oil_tank
        eventBus.post(new EngineOilTankIncreaseLevel());

        //fuel_tank
        eventBus.post(new FuelTankRefill());

        //pitot_tube
        eventBus.post(new PitotTubeClean());
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend(""));
        eventBus.post(new RadarAltimeterReceive(""));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void taxi() {
        //engine_oil_tank

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(1));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend(""));
        eventBus.post(new RadarAltimeterReceive(""));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        //engine_oil_tank
        eventBus.post(new EngineOilTankDecreaseLevel());

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(10));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        //engine_oil_tank
        eventBus.post(new EngineOilTankDecreaseLevel());

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(4));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpongpingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpongpingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        //engine_oil_tank

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(2));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpongpingpongpingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpongpingpongpingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        //engine_oil_tank

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(2));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpongpingpongpingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpongpingpongpingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        //engine_oil_tank

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(1));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpongpingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpongpingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        //engine_oil_tank

        //fuel_tank
        eventBus.post(new FuelTankTakeOut(1));

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());

        //radar_altimeter
        eventBus.post(new RadarAltimeterOn());
        eventBus.post(new RadarAltimeterSend("pingpong"));
        eventBus.post(new RadarAltimeterReceive("pingpong"));
        eventBus.post(new RadarAlitmeterMeasureAltitude());

        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        //engine_oil_tank
        eventBus.post(new EngineOilTankIncreaseLevel());

        //fuel_tank
        eventBus.post(new FuelTankRefill());

        //pitot_tube
        eventBus.post(new PitotTubeMeasureStaticPressure());
        eventBus.post(new PitotTubeMeasureTotalPressure());
        eventBus.post(new PitotTubeClean());

        //radar_altimeter
        eventBus.post(new RadarAltimeterSend(""));
        eventBus.post(new RadarAltimeterReceive(""));
        eventBus.post(new RadarAlitmeterMeasureAltitude());
        eventBus.post(new RadarAltimeterOff());

        // weather_radar
        eventBus.post(new WeatherRadarOff());
    }
}