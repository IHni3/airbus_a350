public interface IWasteWaterTank {
    boolean lock();
    boolean unlock();
    int add(int amount);
    int pumpOut();

    String version();
}
