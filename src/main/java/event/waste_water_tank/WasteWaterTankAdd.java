package event.waste_water_tank;

public class WasteWaterTankAdd {
    private int amount;

    public WasteWaterTankAdd(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Event: WasteWaterTank - Add amount";
    }
}
