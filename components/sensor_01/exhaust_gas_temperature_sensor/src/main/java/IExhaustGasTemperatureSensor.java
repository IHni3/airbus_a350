public interface IExhaustGasTemperatureSensor {
	String version();
	boolean alarmMajor(int threshold);
	boolean alarmCritical(int threshold);
}
