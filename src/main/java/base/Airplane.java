package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
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
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOff());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOff());
        eventBus.post(new TailNavigationLightOff());
    }
}
