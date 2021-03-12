package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.pitot_tube.PitotTubeMeasureVelocity;
import factory.PitotTubeFactory;
import factory.RadarAltimeterFactory;
import factory.WeatherRadarFactory;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> engineOilTankPortList;
    private ArrayList<Object> fuelTankPortList;

    public Wing(){
        engineOilTankPortList = new ArrayList<>();
        fuelTankPortList = new ArrayList<>();

        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
            engineOilTankPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
            fuelTankPortList.add(PitotTubeFactory.build());
        }
    }

    //EngineOilTank
    @Subscribe
    public void receive(EngineOilTankDecreaseLevel engineOilTankDecreaseLevel) {
        System.out.println(engineOilTankDecreaseLevel);
    }

    @Subscribe
    public void receive(EngineOilTankIncreaseLevel engineOilTankIncreaseLevel) {
        System.out.println(engineOilTankIncreaseLevel);
    }

    //Fuel Tank
    @Subscribe
    public void receive(FuelTankRefill fuelTankRefill) {
        System.out.println(fuelTankRefill);
    }

    @Subscribe
    public void receive(FuelTankTakeOut fuelTankTakeOut) {
        System.out.println(fuelTankTakeOut);
    }
}