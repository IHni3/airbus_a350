package event.air_conditioning;

public class AirConditioningCool {
	String airFlow;
	int temperature;

	public AirConditioningCool(String airFlow, int temperature) {
		this.airFlow = airFlow;
		this.temperature = temperature;
	}

	public String toString() {
		return "Event: AirConditioning - Cool";
	}
}
