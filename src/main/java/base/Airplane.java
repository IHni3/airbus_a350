package base;

import com.google.common.eventbus.EventBus;
import domains.CheckPoint;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.CostOptimizerAddCheckPoint;
import event.cost_optimizer.CostOptimizerOff;
import event.cost_optimizer.CostOptimizerOn;
import event.cost_optimizer.CostOptimizerRemoveCheckPoint;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.route_management.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
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
        eventBus.post(new AntiCollisionLightOn());
        eventBus.post(new CargoCompartmentLightOn());
        eventBus.post(new CostOptimizerOn());
        eventBus.post(new RouteManagementOn());
        eventBus.post(new SlatNeutral());

        eventBus.post(new RouteManagementSetCostIndex(0));

        eventBus.post(new CostOptimizerAddCheckPoint(new CheckPoint(1,"name", "100", "100")));
        eventBus.post(new RouteManagementAdd(new CheckPoint(1,"name", "100", "100")));
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());

    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new CargoCompartmentLightDim());
        eventBus.post(new RouteManagementSetCostIndex(1));

    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new SlatUp(10));
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
        eventBus.post(new SlatDown(10));
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        eventBus.post(new LandingLightBodyOn());
        eventBus.post(new LandingLightWingOn());
        eventBus.post(new SlatFullDown());
        eventBus.post(new RouteManagementSetCostIndex(2));


    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
        eventBus.post(new AntiCollisionLightOff());
        eventBus.post(new CargoCompartmentLightOff());
        eventBus.post(new CostOptimizerOff());
        eventBus.post(new LandingLightBodyOff());
        eventBus.post(new LandingLightWingOff());
        eventBus.post(new RouteManagementOff());

        eventBus.post(new CostOptimizerRemoveCheckPoint(new CheckPoint(1,"name", "100", "100")));
        eventBus.post(new RouteManagementRemove(new CheckPoint(1,"name", "100", "100")));
    }
}