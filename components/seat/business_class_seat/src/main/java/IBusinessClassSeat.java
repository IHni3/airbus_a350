public interface IBusinessClassSeat {
    String version();
    boolean nonSmokingSignOn();
    boolean nonSmokingSignOff();
    boolean seatBeltSignOn();
    boolean seatBeltSignOff();
    void readingLightOn();
    void readingLightOff();
    int upRight();
    int level1();
    int level2();
}
