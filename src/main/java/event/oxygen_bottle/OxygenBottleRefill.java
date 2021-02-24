package event.oxygen_bottle;

public class OxygenBottleRefill {
    Integer amount = null;

    public OxygenBottleRefill() {
    }

    public OxygenBottleRefill(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event: OxygenBottle - Refill - Amount: " + amount;
    }
}
