package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.gear.*;
import event.hydraulic_pump.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.APUFactory;
import factory.GearFactory;
import factory.HydraulicPumpFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> aPUPortList;
    private ArrayList<Object> gearPortList;
    private ArrayList<Object> hydraulicPumpPortList;


    public Body() {
        weatherRadarPortList = new ArrayList<>();
        aPUPortList = new ArrayList<>();
        gearPortList = new ArrayList<>();
        hydraulicPumpPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
            aPUPortList.add(APUFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
            gearPortList.add(GearFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
            hydraulicPumpPortList.add(HydraulicPumpFactory.build());
        }

    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------
    // --- APU --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(APUStart aPUStart) {
        LogEngine.instance.write("+ Body.receive(" + aPUStart.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method startMethod = aPUPortList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("startMethod = " + startMethod);

                boolean isStarted = (boolean) startMethod.invoke(aPUPortList.get(i));
                LogEngine.instance.write("isStarted = " + isStarted);

                PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
                FlightRecorder.instance.insert("Body", "APU (isStarted): " + isStarted);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUShutdown aPUShutdown) {
        LogEngine.instance.write("+ Body.receive(" + aPUShutdown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method shutdownMethod = aPUPortList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("shutdownMethod = " + shutdownMethod);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUIncreaseRPM aPUIncreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + aPUIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method increaseRPMMethod = aPUPortList.get(i).getClass().getDeclaredMethod("increaseRPM", Integer.TYPE);
                LogEngine.instance.write("increaseRPMMethod = " + increaseRPMMethod);

                int rpm = (int) increaseRPMMethod.invoke(aPUPortList.get(i), aPUIncreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    @Subscribe
    public void receive(APUDecreaseRPM aPUDecreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + aPUDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + aPUDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method decreaseRPMMethod = aPUPortList.get(i).getClass().getDeclaredMethod("decreaseRPM", Integer.TYPE);
                LogEngine.instance.write("decreaseRPMMethod = " + decreaseRPMMethod);

                int rpm = (int) decreaseRPMMethod.invoke(aPUPortList.get(i), aPUDecreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- Gear -------------------------------------------------------------------------------------------------------
/*
    @Subscribe
    public void receive(GearUp gearUp) {
        LogEngine.instance.write("+ Body.receive(" + gearUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method upMethod = gearPortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("upMethod = " + upMethod);

                boolean isDown = (boolean) upMethod.invoke(gearPortList.get(i));
                LogEngine.instance.write("isDown = " + isDown);

                PrimaryFlightDisplay.instance.isGearDown = isDown;
                FlightRecorder.instance.insert("Body", "Gear (isDown): " + isDown);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearDown gearDown) {
        LogEngine.instance.write("+ Body.receive(" + gearDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method downMethod = gearPortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("downMethod = " + downMethod);

                boolean isDown = (boolean) downMethod.invoke(gearPortList.get(i));
                LogEngine.instance.write("isDown = " + isDown);

                PrimaryFlightDisplay.instance.isGearDown = isDown;
                FlightRecorder.instance.insert("Body", "Gear (isDown): " + isDown);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearSetBrake gearSetBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method setBrakeMethod = gearPortList.get(i).getClass().getDeclaredMethod("setBrake",Integer.TYPE);
                LogEngine.instance.write("setBrakeMethod = " + setBrakeMethod);

                int percentage = (int) setBrakeMethod.invoke(gearPortList.get(i));
                LogEngine.instance.write("percentage = " + percentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = percentage;
                FlightRecorder.instance.insert("Body", "Gear (percentage): " + percentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
    }

    @Subscribe
    public void receive(GearSetBrakePercentage gearSetBrakePercentage) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrakePercentage.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrakePercentage.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method setBrakeMethod = gearPortList.get(i).getClass().getDeclaredMethod("setBrake", Integer.TYPE);
                LogEngine.instance.write("setBrakeMethod = " + setBrakeMethod);

                int percentage = (int) setBrakeMethod.invoke(gearPortList.get(i), gearSetBrakePercentage.getValue());
                LogEngine.instance.write("percentage = " + percentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = percentage;
                FlightRecorder.instance.insert("Body", "Gear (percentage): " + percentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
    }

    @Subscribe
    public void receive(GearReleaseBrake gearReleaseBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearReleaseBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearReleaseBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method releaseBrakeMethod = gearPortList.get(i).getClass().getDeclaredMethod("releaseBrake");
                LogEngine.instance.write("releaseBrakeMethod = " + releaseBrakeMethod);

                int percentage = (int) releaseBrakeMethod.invoke(gearPortList.get(i));
                LogEngine.instance.write("percentage = " + percentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = percentage;
                FlightRecorder.instance.insert("Body", "Gear (percentage): " + percentage);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- HydraulicPump ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpBodyCompress hydraulicPumpBodyCompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method compressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress");
                LogEngine.instance.write("compressMethod = " + compressMethod);

                int amount = (int) compressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = amount;
                FlightRecorder.instance.insert("Body", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyCompress hydraulicPumpBodyCompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method compressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("compress", Integer.TYPE);
                LogEngine.instance.write("compressMethod = " + compressMethod);

                int amount = (int) compressMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpBodyCompress.getValue());
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = amount;
                FlightRecorder.instance.insert("Body", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyDecompress hydraulicPumpBodyDecompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyDecompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method decompressMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("decompressMethod = " + decompressMethod);

                int amount = (int) decompressMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = amount;
                FlightRecorder.instance.insert("Body", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyRefillOil hydraulicPumpBodyRefillOil) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method refillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil");
                LogEngine.instance.write("refillOilMethod = " + refillOilMethod);

                int amount = (int) refillOilMethod.invoke(hydraulicPumpPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = amount;
                FlightRecorder.instance.insert("Body", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyRefillOil hydraulicPumpBodyRefillOil) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method refillOilMethod = hydraulicPumpPortList.get(i).getClass().getDeclaredMethod("refillOil",Integer.TYPE);
                LogEngine.instance.write("refillOilMethod = " + refillOilMethod);

                int amount = (int) refillOilMethod.invoke(hydraulicPumpPortList.get(i), hydraulicPumpBodyRefillOil.getValue());
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = amount;
                FlightRecorder.instance.insert("Body", "HydraulicPump (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }*/

    // ----------------------------------------------------------------------------------------------------------------


}