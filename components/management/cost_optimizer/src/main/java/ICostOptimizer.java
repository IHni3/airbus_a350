import java.util.List;

public interface ICostOptimizer {
    String version();

    boolean on();
    int add(CheckPoint checkPoint);
    int remove(CheckPoint checkPoint);
    int optimize(List<CheckPoint> checkPointList);
    boolean validate(int costIndex);
    boolean off();
}