package event.cost_optimizer;

public class CostOptimizerValidate {

    private int costIndex;

    public CostOptimizerValidate(int costIndex) {
        this.costIndex = costIndex;
    }
    public int getCostIndex() {
        return costIndex;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerValidate";
    }
}
