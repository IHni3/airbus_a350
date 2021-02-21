public interface ISpoiler {
    String version();

    int spoilerNeutral();
    int spoilerFullUp();
    int spoilerUp(int degree);
    int spoilerDown(int degree);
}