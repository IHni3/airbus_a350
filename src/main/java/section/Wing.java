package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.SlatFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Wing extends Subscriber {

    private List<Object> slatPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        build();
    }

    public void build() {
        buildSlat();
    }

    private void buildSlat() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            slatPortList.add(SlatFactory.build());
        }
    }


    // --- Slat -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatDown event) {

        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "down", "degree") {
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
        ProcessEvent p = new ProcessEvent(event.toString(), slatPortList, "Slat", "up", "degree") {
            @Override
            protected Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException {
                int degree = (int) method.invoke(port, event.getDegree());
                PrimaryFlightDisplay.instance.setSlatDegree(degree);
                return degree;
            }
        };
        p.process();
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- AntiCollisionLight -----------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------

    // --- CargoCompartmentLight --------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- LandingLight -----------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- CostOptimizer ----------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------


    // --- RouteManagement --------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------

}