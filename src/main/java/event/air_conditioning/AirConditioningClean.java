package event.air_conditioning;

public class AirConditioningClean {
	String airFlow;

	public AirConditioningClean(String environment) {
		this.airFlow = environment;
	}
	public String toString() {
		return "Event: AirConditioning - Clean";
	}
}
