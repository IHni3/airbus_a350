public interface IFuelSensor {
    String version();
    boolean alarmReserve(int threshold);
    boolean alarmMajor(int threshold);
    boolean alarmCritical(int threshold);
    int measure();
}
