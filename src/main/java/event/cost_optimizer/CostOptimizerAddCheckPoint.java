package event.cost_optimizer;

public class CostOptimizerAddCheckPoint {

    private Object checkpoint;

    public CostOptimizerAddCheckPoint(Object checkpoint) {
        this.checkpoint = checkpoint;
    }
    public Object getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerAddCheckPoint";
    }
}
