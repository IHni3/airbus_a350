public interface IEconomyClassSeat {
    String version();
    int assign(Passenger passenger);
    boolean nonSmokingSignOn();
    boolean nonSmokingSignOff();
    boolean seatBeltSignOn();
    boolean seatBeltSignOff();
    void upRight();
    int level1();
}
