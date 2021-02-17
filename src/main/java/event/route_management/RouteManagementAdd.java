package event.route_management;

import domains.CheckPoint;

public class RouteManagementAdd {

    private CheckPoint checkpoint;

    public RouteManagementAdd(CheckPoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    public CheckPoint getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: RouteManagement - RouteManagementAdd";
    }
}
