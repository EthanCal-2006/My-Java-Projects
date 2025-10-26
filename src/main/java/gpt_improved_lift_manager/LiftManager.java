package gpt_improved_lift_manager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class LiftManager {
    private final List<Lift> liftList = new ArrayList<>();

    public List<Lift> getLiftList() { return liftList; }
    public void addNewLift(Lift lift) { liftList.add(lift); }
    public void removeLift(int index) { liftList.remove(index - 1); }
    public void clearList() { liftList.clear(); }

    public void writeLiftsToFile(List<Lift> lifts, String filePath) {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Lift lift : lifts) writer.write(lift.toString() + System.lineSeparator());
            System.out.println("Lifts saved to " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public void extractAndAppendToLiftList(String filePath, List<Lift> lifts) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String name = null;
            double oneRM = 0, weight = 0;
            int reps = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Exercise:")) name = line.split(":")[1].trim();
                else if (line.startsWith("One Rep Max:")) oneRM = Double.parseDouble(line.split(":")[1].trim());
                else if (line.startsWith("Reps:")) reps = Integer.parseInt(line.split(":")[1].trim());
                else if (line.startsWith("Weight:")) weight = Double.parseDouble(line.split(":")[1].trim());
                else if (line.startsWith("-=-=-=-=-=-=-=-=-=-=-=-=-") && name != null) {
                    lifts.add(new Lift(oneRM, reps, weight, name));
                    name = null; // reset for next block
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
