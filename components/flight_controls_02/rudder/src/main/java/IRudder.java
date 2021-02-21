public interface IRudder {
    String version();

    int neutral();
    int fullLeft();
    int fullRight();
    int left(int degree);
    int right(int degree);
}