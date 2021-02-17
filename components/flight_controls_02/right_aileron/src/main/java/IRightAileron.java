public interface IRightAileron {
    String version();

    int rightAileronNeutral();

    int rightAileronFullUp();

    int rightAileronFullDown();

    int rightAileronUp(int degree);

    int rightAileronDown(int degree);
}