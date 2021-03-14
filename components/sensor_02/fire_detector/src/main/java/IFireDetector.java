public interface IFireDetector {
    String version();
    boolean scan(String air);
    boolean alarm();
}
