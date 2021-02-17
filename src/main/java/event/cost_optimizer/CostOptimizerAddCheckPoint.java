package event.cost_optimizer;

import domains.CheckPoint;

public class CostOptimizerAddCheckPoint {

    private CheckPoint checkpoint;

    public CostOptimizerAddCheckPoint(CheckPoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    public CheckPoint getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerAddCheckPoint";
    }
}
