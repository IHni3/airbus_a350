public interface IGear {
    String version();
//    GearType setGearType(String type);
    boolean down();
    boolean up();
    int setBreak();
    int setBreak(int percentage);
    int releaseBreak();

}
