public interface IRightAileron {
    String version();

    int neutral();

    int fullUp();

    int fullDown();

    int up(int degree);

    int down(int degree);
}