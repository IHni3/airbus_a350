package event.oxygen_bottle;

public class OxygenBottleTakeOut {
    private int amount;

    public OxygenBottleTakeOut (int amount) {
        this.amount = amount;
    }

    public void setAmount (int amount) {
        this.amount = amount;
    }

    public int getAmount () {
        return amount;
    }

    @Override
    public String toString () {
        return "Event: OxygenBottle - TakeOut " + amount;
    }
}
