public interface ILeftAileron {
    String version();

    int leftAileronNeutral();

    int leftAileronFullUp();

    int leftAileronFullDown();

    int leftAileronUp(int degree);

    int leftAileronDown(int degree);
}