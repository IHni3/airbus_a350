public interface ILeftAileron {
    String version();

    int neutral();

    int fullUp();

    int fullDown();

    int up(int degree);

    int down(int degree);
}