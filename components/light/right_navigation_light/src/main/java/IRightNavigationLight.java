public interface IRightNavigationLight {
   	LightType setLightType(String type);
   	Position setPosition(String position);
   	boolean on();
   	boolean off();
}
