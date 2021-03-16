public interface IOxygenBottle {
    String version();

    int takeOut(int amount);

    int refill();

    int refill(int amount);
}