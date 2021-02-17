package event.cost_optimizer;

public class CostOptimizerRemoveCheckPoint {

    private Object checkpoint;

    public CostOptimizerRemoveCheckPoint(Object checkpoint) {
        this.checkpoint = checkpoint;
    }
    public Object getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerRemoveCheckPoint";
    }
}
