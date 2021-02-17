package event.fire_detector;

public class FireDetectorWingScan {
	String air;

	public FireDetectorWingScan(String air) {
		this.air = air;
	}

	public String getAir() {
		return air;
	}

	public String toString() {
		return "Event: FireDetector - Wing - Scan";
	}
}
