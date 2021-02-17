package event.route_management;

public class RouteManagementRemove {

    private Object checkpoint;

    public RouteManagementRemove(Object checkpoint) {
        this.checkpoint = checkpoint;
    }
    public Object getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: RouteManagement - RouteManagementRemove";
    }
}
