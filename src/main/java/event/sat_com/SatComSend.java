package event.sat_com;

public class SatComSend {
    private String request;

    public SatComSend(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String toString() {
        return "Event: SatCom - Send";
    }
}
