package event.vhf;

public class VHFSelectChannel {
    private String channel;

    public VHFSelectChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String toString() {
        return "Event: VHF - Select Channel channel";
    }
}
