package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
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
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
    }
}