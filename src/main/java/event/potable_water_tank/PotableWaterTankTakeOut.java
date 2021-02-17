package event.potable_water_tank;

public class PotableWaterTankTakeOut {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PotableWaterTankTakeOut(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Event: PotableWaterTank - TakeOut Amount";
    }
}
