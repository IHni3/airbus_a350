package event.nitrogen_bottle;

public class NitrogenBottleRefill {
    Integer amount = null;

    public NitrogenBottleRefill () {
    }

    public NitrogenBottleRefill (Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount () {
        return amount;
    }

    public void setAmount (Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString () {
        return "Event: NitrogenBottle - Refill - Amount: " + amount;
    }
}
