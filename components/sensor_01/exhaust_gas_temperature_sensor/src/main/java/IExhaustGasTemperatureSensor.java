public interface IExhaustGasTemperatureSensor {
	String version();
	int measure();
	boolean alarmMajor(int threshold);
	boolean alarmCritical(int threshold);
}
