public interface IRudder {
    String version();

    int rudderNeutral();
    int rudderFullLeft();
    int rudderFullRight();
    int rudderLeft(int degree);
    int rudderRight(int degree);
}