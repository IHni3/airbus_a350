public interface IHydraulicPump {
    String version();
    int compress();
    int compress(int amount);
    int decompress();
    int refillOil();
    int refillOil(int amount);

}
