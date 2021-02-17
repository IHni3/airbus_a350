package event.engine;

public class EngineIncreaseRPM {
	int value;

	public EngineIncreaseRPM(int value) {
		this.value = value;
	}
	public String toString() {
		return "Event: Engine - IncreaseRPM";
	}
}
