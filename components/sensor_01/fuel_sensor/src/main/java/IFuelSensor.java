public interface IFuelSensor {
    boolean alarmReserve(int threshold);
    boolean alarmMajor(int threshold);
    boolean alarmCritical(int threshold);
    int measure();
}
