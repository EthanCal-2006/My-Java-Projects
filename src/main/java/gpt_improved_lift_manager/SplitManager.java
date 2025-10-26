package gpt_improved_lift_manager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SplitManager {
    private final List<Split> splitList = new ArrayList<>();

    public void createSplitList(List<Lift> liftList) {
        splitList.clear();
        for (Lift lift : liftList) {
            splitList.add(new Split(lift));
            System.out.println(splitList.get(splitList.size() - 1));
        }
    }

    public List<Split> getSplitList() { return splitList; }

    public void saveWorkoutToFile(String filePath, List<Split> splits) {
        if (filePath == null || splits == null) throw new IllegalArgumentException("Arguments must not be null");
        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Split split : splits) writer.write(split.toString() + System.lineSeparator());
            System.out.println("Workout saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
