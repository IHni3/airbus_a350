public interface IPotableWaterTank {
    boolean lock();
    boolean unlock();
    int takeOut(int amount);
    int refill();
    int refill(int amount);

    String version();
}
