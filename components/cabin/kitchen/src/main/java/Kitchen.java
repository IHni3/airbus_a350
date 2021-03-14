public class Kitchen {
    // static instance
    private static Kitchen instance = new Kitchen();
    // port
    //public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

    //private ArrayList<Trolley> trolleyList;
    private boolean isLocked;
    private boolean isFilled;

    // private constructor
    //private Kitchen() {
        //port = new Port();
    //}

    // static method getInstance
    public static Kitchen getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Kitchen // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerLock() {
        isLocked = true;
        return isLocked;
    }

    public boolean innerUnlock() {
        isLocked = false;
        return isLocked;
    }

/*    public double innerGetTotalWeightTrolleys() {
    }*/

/*    public void innerAddTrolley() {
        trolleyList.add(new Trolley());
    }


    // inner class port
    public class Port implements IKitchen {
        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public double getTotalWeightTrolleys() {
            return innerGetTotalWeightTrolleys();
        }

        public addTrolley() {
            innerAddTrolley();
        }


	}*/
}
