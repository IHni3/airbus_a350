package section;

import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

abstract class ProcessEvent {
    private List<Object> ports;
    private String methodName;
    private String className;
    private String returnValueName;
    private String eventName;
    private Class<?>[] parameterTypes;

    private void logEvent(String message) {
        LogEngine.instance.write(getClass().getName() + ".receive(" + message + ")");
        FlightRecorder.instance.insert(getClass().getName(), "receive(" + message + ")");
    }

    private void logMethod(Method methodName) {
        LogEngine.instance.write("onMethod = " + methodName);
    }

    public ProcessEvent(String eventName, List<Object> ports, String classname, String methodName, String returnValueName, Class<?>... parameterTypes) {
        this.returnValueName = returnValueName;
        construct(eventName,ports,classname,methodName, parameterTypes);
    }
    public ProcessEvent(String eventName, List<Object> ports, String classname, String methodName, Class<?>... parameterTypes) {
        construct(eventName,ports,classname,methodName, parameterTypes);
    }
    private void construct(String eventName, List<Object> ports, String classname, String methodName, Class<?>... parameterTypes) {
        this.ports = ports;
        this.methodName = methodName;
        this.className = classname;
        this.eventName = eventName;
        this.parameterTypes = parameterTypes;
    }

    protected void process() {

        LogEngine.instance.write("+");
        logEvent(eventName);

        try {
            for (var port : ports) {
                Method onMethod = port.getClass().getDeclaredMethod(methodName, parameterTypes);
                logMethod(onMethod);

                Object returnValue = onInvokeMethod(port, onMethod);

                if(!returnValueName.isBlank())
                    LogEngine.instance.write(returnValueName + " = " + returnValue);

                FlightRecorder.instance.insert(getClass().getName(), className + " (" + methodName + "): " + returnValue);
                LogEngine.instance.write("+");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract Object onInvokeMethod(Object port, Method method) throws InvocationTargetException, IllegalAccessException;
}
