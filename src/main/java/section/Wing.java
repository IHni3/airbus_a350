package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.hydraulic_pump.*;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> enginePortList;
    private ArrayList<Object> hydraulicPumpPortList;

    public Wing() {
        enginePortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
            enginePortList.add(EngineFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }
    }

    // --- Engine -----------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(EngineStart engineStart) {
        LogEngine.instance.write("+ Wing.receive(" + engineStart.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method startMethod = enginePortList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("startMethod = " + startMethod);

                boolean isStarted = (boolean) startMethod.invoke(enginePortList.get(i));
                LogEngine.instance.write("isStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
                FlightRecorder.instance.insert("Wing", "Engine (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineStarted: " + PrimaryFlightDisplay.instance.isEngineStarted);
    }

    @Subscribe
    public void receive(EngineShutdown engineShutdown) {
        LogEngine.instance.write("+ Wing.receive(" + engineShutdown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method shutdownMethod = enginePortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                boolean isStarted = (boolean) shutdownMethod.invoke(enginePortList.get(i));
                LogEngine.instance.write("isStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
                FlightRecorder.instance.insert("Wing", "Engine (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineStarted: " + PrimaryFlightDisplay.instance.isEngineStarted);
    }

    @Subscribe
    public void receive(EngineIncreaseRPM engineIncreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method increaseRPMMethod = enginePortList.get(i).getClass().getDeclaredMethod("increaseRPM", Integer.TYPE);
                LogEngine.instance.write("increaseRPMMethod = " + increaseRPMMethod);

                int rpm = (int) increaseRPMMethod.invoke(enginePortList.get(i), engineIncreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmEngine = rpm;
                FlightRecorder.instance.insert("Wing", "Engine (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    @Subscribe
    public void receive(EngineDecreaseRPM engineDecreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method decreaseRPMMethod = enginePortList.get(i).getClass().getDeclaredMethod("decreaseRPM", Integer.TYPE);
                LogEngine.instance.write("decreaseRPMMethod = " + decreaseRPMMethod);

                int rpm = (int) decreaseRPMMethod.invoke(enginePortList.get(i),engineDecreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmEngine = rpm;
                FlightRecorder.instance.insert("Wing", "Engine (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- HydraulicPump ----------------------------------------------------------------------------------------------

  /*  @Subscribe
    public void receive(HydraulicPumpWingCompress hydraulicPumpWingCompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingCompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method compressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress");
                LogEngine.instance.write("compressMethod = " + compressMethod);

                int amount = (int) compressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount = amount;
                FlightRecorder.instance.insert("Wing", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hybraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hybraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpWingCompress hydraulicPumpWingCompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingCompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method compressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress", Integer.TYPE);
                LogEngine.instance.write("compressMethod = " + compressMethod);

                int amount = (int) compressMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpWingCompress.getValue());
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount = amount;
                FlightRecorder.instance.insert("Wing", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hybraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hybraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpWingDecompress hydraulicPumpWingDecompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingDecompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method decompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("decompressMethod = " + decompressMethod);

                int amount = (int) decompressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount = amount;
                FlightRecorder.instance.insert("Wing", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hybraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hybraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpWingRefillOil hydraulicPumpWingRefillOil) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method refillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil");
                LogEngine.instance.write("refillOilMethod = " + refillOilMethod);

                int amount = (int) refillOilMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount = amount;
                FlightRecorder.instance.insert("Wing", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hybraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hybraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpWingRefillOil hydraulicPumpWingRefillOil) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method refillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil", Integer.TYPE);
                LogEngine.instance.write("refillOilMethod = " + refillOilMethod);

                int amount = (int) refillOilMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpWingRefillOil.getValue());
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount = amount;
                FlightRecorder.instance.insert("Wing", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hybraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hybraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hybraulicPumpWingOilAmount);
    }
*/
    // ----------------------------------------------------------------------------------------------------------------


}