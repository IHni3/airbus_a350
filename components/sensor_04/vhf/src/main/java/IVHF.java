public interface IVHF {
    boolean on();
    String[] search();
    String forwardChannel();
    String backwardChannel();
    String selectChannel(String channel);
    boolean off();

    String version();
}
