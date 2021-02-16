public interface ISlat {
    String version();

    int neutral();
    int fullDown();
    int down(int degree);
    int up(int degree);
}