package event.cost_optimizer;

import domains.CheckPoint;

public class CostOptimizerRemoveCheckPoint {

    private CheckPoint checkpoint;

    public CostOptimizerRemoveCheckPoint(CheckPoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    public CheckPoint getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerRemoveCheckPoint";
    }
}
