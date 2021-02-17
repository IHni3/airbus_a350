package event.apu;

public class APUIncreaseRPM {
	int value;

	public APUIncreaseRPM(int value) {
		this.value = value;
	}

	public String toString() {
		return "Event: APU - IncreaseRPM";
	}
}
