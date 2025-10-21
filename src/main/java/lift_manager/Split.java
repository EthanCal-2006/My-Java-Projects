package lift_manager;

public class Split {
    private Lift lift;
    private double eightyPercentMax;
    private double sixtyPercentMax;
    private double fortyPercentMax;

    public Split(Lift lift) {
        this.lift = lift;
        this.eightyPercentMax = this.lift.getOneRM() * 0.8;
        this.eightyPercentMax = this.eightyPercentMax - (this.eightyPercentMax % 5);
        this.sixtyPercentMax = this.lift.getOneRM() * 0.6;
        this.sixtyPercentMax = this.sixtyPercentMax - (this.sixtyPercentMax % 5);
        this.fortyPercentMax = this.lift.getOneRM() * 0.4;
        this.fortyPercentMax = this.fortyPercentMax - (this.fortyPercentMax % 5);
    }

    public Split(String exerciseName, double fortyPercentMax, double sixtyPercentMax, double eightyPercentMax) {
        this.lift.setExerciseName(exerciseName);
        this.eightyPercentMax = this.lift.getOneRM() * 0.8;
        this.eightyPercentMax = this.eightyPercentMax - (this.eightyPercentMax % 5);
        this.sixtyPercentMax = this.lift.getOneRM() * 0.6;
        this.sixtyPercentMax = this.sixtyPercentMax - (this.sixtyPercentMax % 5);
        this.fortyPercentMax = this.lift.getOneRM() * 0.4;
        this.fortyPercentMax = this.fortyPercentMax - (this.fortyPercentMax % 5);
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public double getEightyPercentMax() {
        return eightyPercentMax;
    }

    public double getSixtyPercentMax() {
        return sixtyPercentMax;
    }

    public double getFortyPercentMax() {
        return fortyPercentMax;
    }

    @Override
    public String toString() {
        return 
        "......................................................\n" + 
        "Split for " + this.lift.getExerciseName() + ":\n"
        + "1 set of " + this.fortyPercentMax + " for 10 reps (warmup)\n"
        + "2 sets of " + this.sixtyPercentMax + " for 12 reps\n"
        + "2 sets of " + this.eightyPercentMax + " for 8 reps\n"
        +".....................................................\n";
    }
    
}
