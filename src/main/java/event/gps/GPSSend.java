package event.gps;

public class GPSSend {
    private String request;

    public GPSSend(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Event: GPS - Send - " + request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
