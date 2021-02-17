package event.potable_water_tank;

public class PotableWaterTankRefillAmount {
    private int amount;

    public PotableWaterTankRefillAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Event: PotableWaterTank - Refill Amount";
    }
}
