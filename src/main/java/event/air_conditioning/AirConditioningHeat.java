package event.air_conditioning;

public class AirConditioningHeat {
	String airFlow;
	int temperature;

	public AirConditioningHeat(String airFlow, int temperature) {
		this.airFlow = airFlow;
		this.temperature = temperature;
	}

	public String toString() {
		return "Event: AirConditioning - Heat";
	}
}
