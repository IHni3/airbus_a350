public interface IIceDetectorProbe {
    boolean detect();
    boolean detect(String surface);
    boolean detect(String surface, String pattern);
    boolean deactivate();
    boolean activate();
}
