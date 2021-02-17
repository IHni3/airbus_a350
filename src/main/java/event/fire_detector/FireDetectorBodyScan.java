package event.fire_detector;

public class FireDetectorBodyScan {
	String air;

	public FireDetectorBodyScan(String air) {
		this.air = air;
	}

	public String getAir() {
		return air;
	}

	public String toString() {
		return "Event: FireDetector - Body - Scan";
	}
}
