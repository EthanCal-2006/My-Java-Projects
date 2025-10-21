package lift_manager;

public class Lift {
    private double oneRM;
    private int reps;
    private double repsWeight;
    private String exerciseName;
    
    public Lift(int reps, double repsWeight, String exerciseName) {
        this.reps = reps;
        this.repsWeight = repsWeight;
        this.exerciseName = exerciseName;
        this.oneRM = this.repsWeight / (1.0278 - 0.0278 * this.reps);
        this.oneRM = Math.round(oneRM);
    }

    public Lift(double oneRM, int reps, double repsWeight, String exerciseName) {
        this.oneRM = oneRM;
        this.reps = reps;
        this.repsWeight = repsWeight;
        this.exerciseName = exerciseName;
    }



    public Lift() {
    }

    public double getOneRM() {
        return oneRM;
    }

    public void setOneRM(double oneRM) {
        this.oneRM = oneRM;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getRepsWeight() {
        return repsWeight;
    }

    public void setRepsWeight(double repsWeight) {
        this.repsWeight = repsWeight;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void calculateOneRM(int reps, double repsWeight) {
        this.oneRM = this.repsWeight / (1.0278 - 0.0278 * this.reps);
        this.oneRM = Math.round(oneRM);
    }

    @Override
    public String toString() {
        return "-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" + "Exercise name = " + exerciseName + "\n" + "One rep max = " + oneRM + "\n" + "Entered Reps = " + reps + "\n" + "Entered Reps weight = " + repsWeight + "\n" + "-=-=-=-=-=-=-=-=-=-=-=-=-";
    }

}
