package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.pitot_tube.PitotTubeMeasureVelocity;
import factory.*;
import logging.LogEngine;
import org.checkerframework.checker.units.qual.A;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
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
            engineOilTankPortList.add(EngineOilTankFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
            fuelTankPortList.add(FuelTankFactory.build());
        }
    }

    //engine_oil_tank
    @Subscribe
    public void receive(EngineOilTankDecreaseLevel engineOilTankDecreaseLevel) {
        System.out.println(engineOilTankDecreaseLevel);
        LogEngine.instance.write("+ Body.receive(" + engineOilTankDecreaseLevel.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + engineOilTankDecreaseLevel.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
                Method decreaseLevelMethod = engineOilTankPortList.get(i).getClass().getDeclaredMethod("decreaseLevel", int.class);
                LogEngine.instance.write("decreaseLevelMethod = " + decreaseLevelMethod);

                int level = (int) decreaseLevelMethod.invoke(engineOilTankPortList.get(i), 1);
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelEngineOilTank = level;
                FlightRecorder.instance.insert("Body", "EngineOilTank (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelEngineOilTank): " + PrimaryFlightDisplay.instance.levelEngineOilTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelEngineOilTank: " + PrimaryFlightDisplay.instance.levelEngineOilTank);
    }

    @Subscribe
    public void receive(EngineOilTankIncreaseLevel engineOilTankIncreaseLevel) {
        System.out.println(engineOilTankIncreaseLevel);
        LogEngine.instance.write("+ Body.receive(" + engineOilTankIncreaseLevel.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + engineOilTankIncreaseLevel.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++) {
                Method increaseLevelMethod = engineOilTankPortList.get(i).getClass().getDeclaredMethod("increaseLevel", int.class);
                LogEngine.instance.write("decreaseLevelMethod = " + increaseLevelMethod);

                int level = (int) increaseLevelMethod.invoke(engineOilTankPortList.get(i), 10);
                LogEngine.instance.write("level = " + level);

                PrimaryFlightDisplay.instance.levelEngineOilTank = level;
                FlightRecorder.instance.insert("Body", "EngineOilTank (level): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (levelEngineOilTank): " + PrimaryFlightDisplay.instance.levelEngineOilTank);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "levelEngineOilTank: " + PrimaryFlightDisplay.instance.levelEngineOilTank);
    }

    //fuel_tank
    @Subscribe
    public void receive(FuelTankRefill fuelTankRefill) {
        System.out.println(fuelTankRefill);
        LogEngine.instance.write("+ Body.receive(" + fuelTankRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fuelTankRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
                int level = 0;
                if(fuelTankRefill.getAmount() == -1){
                    Method refillMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("refill");
                    LogEngine.instance.write("refillMethod = " + refillMethod);
                    level = (int) refillMethod.invoke(fuelTankPortList.get(i));
                }else{
                    Method refillMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("refill", int.class);
                    LogEngine.instance.write("refillMethod = " + refillMethod);
                    level = (int) refillMethod.invoke(fuelTankPortList.get(i), fuelTankRefill.getAmount());
                }

                LogEngine.instance.write("amount = " + level);

                PrimaryFlightDisplay.instance.amountOfFuel = level;
                FlightRecorder.instance.insert("Body", "Fuel Tank (amount): " + level);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (amountOfFuel): " + PrimaryFlightDisplay.instance.amountOfFuel);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "amountOfFuel: " + PrimaryFlightDisplay.instance.amountOfFuel);
    }

    @Subscribe
    public void receive(FuelTankTakeOut fuelTankTakeOut) {
        System.out.println(fuelTankTakeOut);
        LogEngine.instance.write("+ Body.receive(" + fuelTankTakeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + fuelTankTakeOut.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++) {
                Method takeOutMethod = fuelTankPortList.get(i).getClass().getDeclaredMethod("takeOut", int.class);
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);
                int amount = (int) takeOutMethod.invoke(fuelTankPortList.get(i), fuelTankTakeOut.getAmount());

                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountOfFuel = amount;
                FlightRecorder.instance.insert("Body", "Fuel Tank (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (amountOfFuel): " + PrimaryFlightDisplay.instance.amountOfFuel);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "amountOfFuel: " + PrimaryFlightDisplay.instance.amountOfFuel);
    }
}