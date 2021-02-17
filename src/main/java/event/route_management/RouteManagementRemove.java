package event.route_management;

import domains.CheckPoint;

public class RouteManagementRemove {

    private CheckPoint checkpoint;

    public RouteManagementRemove(CheckPoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    public CheckPoint getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: RouteManagement - RouteManagementRemove";
    }
}
