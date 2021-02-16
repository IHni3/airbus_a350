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
import logging.LogEngine;
import recorder.FlightRecorder;

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




    abstract class ProcessEvent {
        private List<Object> ports;
        private String methodName;
        private String className;
        private String returnValueName;
        private String eventName;

        private void logEvent(String message) {
            LogEngine.instance.write(getClass().getName() + ".receive(" + message + ")");
            FlightRecorder.instance.insert(getClass().getName(), "receive(" + message + ")");
        }
        private void logMethod(Method methodName) {
            LogEngine.instance.write("onMethod = " + methodName);
        }

        public ProcessEvent(String eventName, List<Object> ports, String classname, String methodName, String returnValueName) {
            this.ports = ports;
            this.methodName = methodName;
            this.className = classname;
            this.returnValueName = returnValueName;
            this.eventName = eventName;
        }

        protected void process() {

            LogEngine.instance.write("+");
            logEvent(eventName);

            try {
                for (var port : ports) {
                    Method onMethod = port.getClass().getDeclaredMethod(methodName);
                    logMethod(onMethod);

                    Object returnValue = onInvokeMethod(port, onMethod);

                    LogEngine.instance.write(returnValueName + " = " + returnValue);
                    FlightRecorder.instance.insert(getClass().getName(), className + " (" + methodName + "): " + returnValue);
                    LogEngine.instance.write("+");

                }
            }
            catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        protected abstract Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException;
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

}