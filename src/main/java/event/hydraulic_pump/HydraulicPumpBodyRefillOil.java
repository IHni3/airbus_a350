package event.hydraulic_pump;

public class HydraulicPumpBodyRefillOil {
	int value;

	public HydraulicPumpBodyRefillOil(int value) {
		this.value = value;
	}
	public String toString() {
		return "Event: HydraulicPump - Body - RefillOil";
	}

	public int getValue() {
		return value;
	}
}
