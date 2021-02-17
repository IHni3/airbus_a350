package event.apu;

public class APUDecreaseRPM {
	int value;

	public APUDecreaseRPM(int value) {
		this.value = value;
	}
	public String toString() {
		return "Event: APU - DecreaseRPM";
	}
}
