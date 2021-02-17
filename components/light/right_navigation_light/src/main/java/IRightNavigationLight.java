public interface IRightNavigationLight {
	String version();
	LightType setLightType(String type);
   	Position setPosition(String position);
   	boolean on();
   	boolean off();
}
