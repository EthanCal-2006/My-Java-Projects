package gpt_improved_lift_manager;

public class Lift {
    private String exerciseName;
    private int reps;
    private double repsWeight;
    private double oneRM;

    public Lift(int reps, double repsWeight, String exerciseName) {
        this.reps = reps;
        this.repsWeight = repsWeight;
        this.exerciseName = exerciseName;
        calculateOneRM();
    }

    public Lift(double oneRM, int reps, double repsWeight, String exerciseName) {
        this.oneRM = oneRM;
        this.reps = reps;
        this.repsWeight = repsWeight;
        this.exerciseName = exerciseName;
    }

    public Lift() {}

    public void calculateOneRM() {
        this.oneRM = Math.round(this.repsWeight / (1.0278 - 0.0278 * this.reps));
    }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; calculateOneRM(); }

    public double getRepsWeight() { return repsWeight; }
    public void setRepsWeight(double repsWeight) { this.repsWeight = repsWeight; calculateOneRM(); }

    public double getOneRM() { return oneRM; }
    public void setOneRM(double oneRM) { this.oneRM = oneRM; }

    @Override
    public String toString() {
        return String.format(
                "-=-=-=-=-=-=-=-=-=-=-=-=-\nExercise: %s\nOne Rep Max: %.1f\nReps: %d\nWeight: %.1f\n-=-=-=-=-=-=-=-=-=-=-=-=-",
                exerciseName, oneRM, reps, repsWeight
        );
    }
}
