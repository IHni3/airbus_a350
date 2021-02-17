package event.cost_optimizer;

import java.util.List;

public class CostOptimizerOptimize {

    List<Object> checkPointList;

    public CostOptimizerOptimize(List<Object> checkPointList) {
       this.checkPointList = checkPointList;
    }

    public List<Object> getCheckPointList() {
        return checkPointList;
    }

    public String toString() {
        return "Event: CostOptimizer - CostOptimizerOptimize";
    }
}
