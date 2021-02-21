package section;

import com.google.common.eventbus.Subscribe;
import event.Subscriber;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.pitot_tube.PitotTubeMeasureVelocity;

public class Wing extends Subscriber {
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