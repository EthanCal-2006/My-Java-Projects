package lift_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SplitManager {
    private List<Split> splitList;

    public SplitManager() {
        this.splitList = new ArrayList<Split>();
    }

    public void createSplitList(List<Lift> liftList) {
        splitList.clear();
        Split newSplit;
        for (int i = 0; i < liftList.size(); i++) {
            newSplit = new Split(liftList.get(i));
            splitList.add(newSplit);
            System.out.println(splitList.get(i).toString());
        }
    }

    public List<Split> getSplitList() {
        return splitList;
    }

    public void saveWorkoutToFile(String filePath, List<Split> splitList) {
        if (filePath == null || splitList == null) {
            throw new IllegalArgumentException("filePath and splitList must not be null.");
        }

        Path path = Paths.get(filePath);

        // StringBuilder to hold the formatted workout data
        StringBuilder workoutData = new StringBuilder();

        for (Split split : splitList) {
            workoutData.append("......................................................\n");
            workoutData.append("Split for ").append(split.getLift().getExerciseName()).append(":\n");

            workoutData.append("1 set of ").append(split.getFortyPercentMax()).append(" for 10 reps (warmup)\n");
            workoutData.append("2 sets of ").append(split.getSixtyPercentMax()).append(" for 12 reps\n");
            workoutData.append("2 sets of ").append(split.getEightyPercentMax()).append(" for 8 reps\n");

            workoutData.append("......................................................\n\n");
        }

        // Write to file using BufferedWriter and UTF-8 encoding
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(workoutData.toString());
            System.out.println("Workout data has been saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}
