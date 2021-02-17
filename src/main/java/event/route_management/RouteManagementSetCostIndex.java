package event.route_management;

public class RouteManagementSetCostIndex {

    private int costIndex;

    public RouteManagementSetCostIndex(int costIndex) {
        this.costIndex = costIndex;
    }
    public int getCostIndex() {
        return costIndex;
    }

    public String toString() {
        return "Event: RouteManagement - RouteManagementSetCostIndex";
    }
}
