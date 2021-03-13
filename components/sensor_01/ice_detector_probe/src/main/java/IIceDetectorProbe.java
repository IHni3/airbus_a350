public interface IIceDetectorProbe {
    String version();
    boolean detect();
    boolean detect(String surface);
    boolean detect(String surface, String pattern);
    boolean deactivate();
    boolean activate();
}
