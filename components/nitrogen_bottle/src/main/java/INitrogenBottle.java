public interface INitrogenBottle {
    String version();

    int takeOut(int amount);

    int refill();

    int refill(int amount);
}