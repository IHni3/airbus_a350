package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.LandingLightFactory;
import factory.SlatFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Wing extends Subscriber {

    private List<Object> slatPortList;
    private List<Object> landingLightPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        build();
    }

    public void build() {
        buildSlat();
        buildLandingLight();
    }

    private void buildSlat() {
        for (int i = 0; i < Configuration.instance.numberOfSlats; i++) {
            slatPortList.add(SlatFactory.build());
        }
    }
    private void buildLandingLight() {
        for (int i = 0; i < Configuration.instance.numberOfLandingLightsBody; i++) {
            landingLightPortList.add(LandingLightFactory.build());
        }
    }


    // --- Slat -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatDown event) {

        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "down", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port, event.getDegree());
                PrimaryFlightDisplay.instance.setSlatDegree(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SlatFullDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "fullDown", "degree") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setSlatDegree(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SlatNeutral event) {
        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "neutral", "degree") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setSlatDegree(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SlatUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "up", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port, event.getDegree());
                PrimaryFlightDisplay.instance.setSlatDegree(degree);
                return degree;
            }
        };
        p.process();
    }


    // --- LandingLight -----------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(LandingLightBodyOn event) {
        ProcessEvent p = new ProcessEvent(event.toString(), landingLightPortList, "LandingLight", "on", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setLandingLightWingEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LandingLightBodyOff event) {
        ProcessEvent p = new ProcessEvent(event.toString(), landingLightPortList, "LandingLight", "off", "isOn") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                boolean isOn = (boolean) method.invoke(port);
                PrimaryFlightDisplay.instance.setLandingLightWingEnabled(isOn);
                return isOn;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------

}