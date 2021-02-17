public class VHF {
    // static instance
    private static VHF instance = new VHF();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private boolean isOn;
    private String[] channelList;
    private String selectedChannel;

    // private constructor
    private VHF() {
        port = new Port();
    }

    // static method getInstance
    public static VHF getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "VHF // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public String[] innerSearch() {
        return channelList;
    }

    public String innerForwardChannel() {
        for (int i=0; channelList.length<i; i++) {
            if(channelList[i].equals(selectedChannel)){
                selectedChannel = channelList[i+1];
                return selectedChannel;
            }
        }
        return selectedChannel;
    }

    public String innerBackwardChannel() {
        for (int i=0; channelList.length<i; i++) {
            if(channelList[i].equals(selectedChannel)){
                selectedChannel = channelList[i-1];
                return selectedChannel;
            }
        }
        return selectedChannel;
    }

    public String innerSelectChannel(String channel) {
        for (int i=0; channelList.length<i; i++) {
            if(channelList[i].equals(channel)){
                return channelList[i];
            }
        }
        return "";
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }


    // inner class port
    public class Port implements IVHF {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public String[] search() {
            return innerSearch();
        }

        public String forwardChannel() {
            return innerForwardChannel();
        }

        public String backwardChannel() {
            return innerBackwardChannel();
        }

        public String selectChannel(String channel) {
            return innerSelectChannel(channel);
        }

        public boolean off() {
            return innerOff();
        }


	}
}
