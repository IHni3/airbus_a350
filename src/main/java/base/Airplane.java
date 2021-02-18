package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.business_class_seat.NonSmokingSignOff;
import event.business_class_seat.NonSmokingSignOn;
import event.business_class_seat.SeatBeltSignOff;
import event.business_class_seat.SeatBeltSignOn;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fire_detector.FireDetectorBodyScan;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.ice_detector_probe.IceDetectorProbeBodyActivate;
import event.ice_detector_probe.IceDetectorProbeBodyDeactivate;
import event.ice_detector_probe.IceDetectorProbeWingActivate;
import event.ice_detector_probe.IceDetectorProbeWingDeactivate;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
import event.taxi_light.TaxiLightOff;
import event.taxi_light.TaxiLightOn;
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
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOff());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOn());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOff());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOn());
        eventBus.post(new TailNavigationLightOn());
        eventBus.post(new NonSmokingSignOn());
        eventBus.post(new SeatBeltSignOn());
        eventBus.post(new event.crew_seat.NonSmokingSignOn());
        eventBus.post(new event.crew_seat.SeatBeltSignOn());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOn());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOn());
        eventBus.post(new IceDetectorProbeBodyActivate());
        eventBus.post(new IceDetectorProbeWingActivate());
        eventBus.post(new TaxiLightOff());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
        eventBus.post(new ExhaustGasTemperatureSensorMeasure());
        eventBus.post(new FuelFlowSensorMeasure());
        eventBus.post(new RightNavigationLightOff());
        eventBus.post(new TailNavigationLightOff());
        eventBus.post(new NonSmokingSignOff());
        eventBus.post(new SeatBeltSignOff());
        eventBus.post(new event.crew_seat.NonSmokingSignOff());
        eventBus.post(new event.crew_seat.SeatBeltSignOff());
        eventBus.post(new event.economy_class_seat.NonSmokingSignOff());
        eventBus.post(new event.economy_class_seat.SeatBeltSignOff());
        eventBus.post(new IceDetectorProbeBodyDeactivate());
        eventBus.post(new IceDetectorProbeWingDeactivate());
        eventBus.post(new TaxiLightOff());
    }
}
