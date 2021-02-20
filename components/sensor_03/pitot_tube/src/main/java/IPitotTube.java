public interface IPitotTube {
	String version();
    int measureStaticPressure();
    int measureTotalPressure();
    int measureVelocity();
    void clean();
}
