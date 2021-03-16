package event.fuel_tank;

public class FuelTankRefill {
    int amount;

    public FuelTankRefill() {
        this.amount = -1;
    }

    public FuelTankRefill(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return "Event: FuelTank - Refill (amount: " + amount + ")";
    }
}
