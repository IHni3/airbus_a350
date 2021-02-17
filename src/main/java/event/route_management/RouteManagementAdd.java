package event.route_management;

public class RouteManagementAdd {

    private Object checkpoint;

    public RouteManagementAdd(Object checkpoint) {
        this.checkpoint = checkpoint;
    }
    public Object getCheckpoint() {
        return checkpoint;
    }

    public String toString() {
        return "Event: RouteManagement - RouteManagementAdd";
    }
}
