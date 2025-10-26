package gpt_improved_lift_manager;

public class Split {
    private final Lift lift;
    private final double eightyPercentMax;
    private final double sixtyPercentMax;
    private final double fortyPercentMax;

    public Split(Lift lift) {
        this.lift = lift;
        this.eightyPercentMax = roundToFive(lift.getOneRM() * 0.8);
        this.sixtyPercentMax = roundToFive(lift.getOneRM() * 0.6);
        this.fortyPercentMax = roundToFive(lift.getOneRM() * 0.4);
    }

    private double roundToFive(double value) {
        return value - (value % 5);
    }

    public Lift getLift() { return lift; }
    public double getEightyPercentMax() { return eightyPercentMax; }
    public double getSixtyPercentMax() { return sixtyPercentMax; }
    public double getFortyPercentMax() { return fortyPercentMax; }

    @Override
    public String toString() {
        return String.format("""
            ......................................................
            Split for %s:
            1 set of %.1f for 10 reps (warmup)
            2 sets of %.1f for 12 reps
            2 sets of %.1f for 8 reps
            ......................................................
            """, lift.getExerciseName(), fortyPercentMax, sixtyPercentMax, eightyPercentMax);
    }
}

