package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Spoiler.SpoilerDown;
import event.Spoiler.SpoilerFullUp;
import event.Spoiler.SpoilerNeutral;
import event.Spoiler.SpoilerUp;
import event.Subscriber;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.left_aileron.*;
import event.right_aileron.*;
import event.rudder.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Wing extends Subscriber {

    private List<Object> slatPortList;
    private List<Object> landingLightPortList;
    private List<Object> spoilerPortList;
    private List<Object> rightAileronPortList;
    private List<Object> leftAileronPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        spoilerPortList = new ArrayList<>();
        rightAileronPortList = new ArrayList<>();
        leftAileronPortList = new ArrayList<>();
        build();
    }

    public void build() {
        buildSlat();
        buildLandingLight();
        buildSpoiler();
        buildRightAileron();
        buildLeftAileron();
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
    private void buildSpoiler() {
        for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
            spoilerPortList.add(SpoilerFactory.build());
        }
    }

    private void buildRightAileron() {
        for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
            rightAileronPortList.add(RightAileronFactory.build());
        }
    }
    private void buildLeftAileron() {
        for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
            leftAileronPortList.add(LeftAileronFactory.build());
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

    // --- Spoiler --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(SpoilerNeutral event) {
        ProcessEvent p = new ProcessEvent(event.toString(), spoilerPortList, "Spoiler", "neutral", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeSpoiler(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SpoilerFullUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), spoilerPortList, "Spoiler", "fullup", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeSpoiler(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SpoilerUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), spoilerPortList, "Spoiler", "up", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeSpoiler(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(SpoilerDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), spoilerPortList, "Spoiler", "down", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeSpoiler(degree);
                return degree;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- Left_Aileron --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(LeftAileronNeutral event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Left_Aileron", "neutral", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeLeftAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LeftAileronFullUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Left_Aileron", "fullUp", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeLeftAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LeftAileronFullDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Left_Aileron", "fullDown", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeLeftAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LeftAileronDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Left_Aileron", "down", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeLeftAileron(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(LeftAileronUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Left_Aileron", "up", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeLeftAileron(degree);
                return degree;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------
    // --- Right_Aileron --------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(RightAileronNeutral event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Right_Aileron", "neutral", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRightAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RightAileronFullUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Right_Aileron", "fullUp", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRightAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RightAileronFullDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Right_Aileron", "fullDown", "index") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int index = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRightAileron(index);
                return index;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RightAileronDown event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Right_Aileron", "down", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRightAileron(degree);
                return degree;
            }
        };
        p.process();
    }
    @Subscribe
    public void receive(RightAileronUp event) {
        ProcessEvent p = new ProcessEvent(event.toString(), leftAileronPortList, "Right_Aileron", "up", "degree", int.class) {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port);
                PrimaryFlightDisplay.instance.setDegreeRightAileron(degree);
                return degree;
            }
        };
        p.process();
    }
    // ----------------------------------------------------------------------------------------------------------------
}