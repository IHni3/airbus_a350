public interface ICamera {
    String version ();

    boolean on ();

    boolean off ();

    CameraType setType (String type);

    String record ();

    String zoomIn (double factor);

    String zoomOut (double factor);
}