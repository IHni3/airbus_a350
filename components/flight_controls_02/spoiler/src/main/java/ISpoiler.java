public interface ISpoiler {
    String version();

    int neutral();
    int fullUp();
    int up(int degree);
    int down(int degree);
}