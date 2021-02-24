package event.nitrogen_bottle;

public class NitrogenBottleTakeOut {
    private int amount;

    public NitrogenBottleTakeOut(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event: NitrogenBottle - TakeOut " + amount;
    }
}
