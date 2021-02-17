package event.oxygen_sensor;

public class OxygenSensorMeasure {
	String airFlow;

	public OxygenSensorMeasure(String airFlow) {
		this.airFlow = airFlow;
	}

	public String getAirFlow() {
		return airFlow;
	}

	public String toString() {
		return "Event: OxygenSensor - Measure";
	}
}
