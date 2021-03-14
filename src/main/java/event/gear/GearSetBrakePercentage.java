package event.gear;

public class GearSetBrakePercentage {
	int value;

	public GearSetBrakePercentage(int value) {
		this.value = value;
	}

	public String toString() {
		return "Event: Gear - SetBrakePercentage";
	}

	public int getValue() {
		return value;
	}
}
