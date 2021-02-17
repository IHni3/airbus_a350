public interface ICrewSeat {
    String version();
    int assign(CrewMember crewMember);
    boolean nonSmokingSignOn();
    boolean nonSmokingSignOff();
    boolean beltSignOn();
    boolean beltSignOff();
    boolean readingLightOn();
    boolean readingLightOff();
}
