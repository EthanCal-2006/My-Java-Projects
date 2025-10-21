package lift_manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LiftManager {
    private List<Lift> liftList;

    public LiftManager() {
        liftList = new ArrayList<Lift>();
        //initializeList(); //testing purposes
    }

    public void initializeList() {
        Lift lift1 = new Lift(5, 100, "Squat");
        Lift lift2 = new Lift(10, 50, "Bicep Curls");
        Lift lift3 = new Lift(15, 150, "Bench press");

        liftList.add(lift1);
        liftList.add(lift2);
        liftList.add(lift3);
    }

    public List<Lift> getLiftList() {
        return liftList;
    }

    public void addNewLift(Lift newLift) {
        liftList.add(newLift);
    }

    public void removeLift(int removeChoiceIndex) {
        liftList.remove(removeChoiceIndex - 1);
    }

    public void clearList() {
        liftList.clear();
    }

    public void writeLiftsToFile(List<Lift> liftList, String filePath) {
        if (liftList == null || filePath == null) {
            throw new IllegalArgumentException("liftList and filePath must not be null.");
        }

        Path path = Paths.get(filePath);
        System.out.println("Writing to: " + path.toAbsolutePath());

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Lift lift : liftList) {
                writer.write(lift.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public void extractAndAppendToLiftList(String filePath, List<Lift> liftList) {
        StringBuilder currentBlock = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Loop through each line of the file
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check if the line is a block delimiter
                if (line.equals("-=-=-=-=-=-=-=-=-=-=-=-=-")) {
                    // If we have a block, process it
                    if (currentBlock.length() > 0) {
                        appendLiftFromBlock(currentBlock.toString(), liftList);
                        currentBlock.setLength(0); // Reset for next block
                    }
                } else {
                    // Add line to the current block
                    currentBlock.append(line).append("\n");
                }
            }

            // Process the last block if exists
            if (currentBlock.length() > 0) {
                appendLiftFromBlock(currentBlock.toString(), liftList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to extract the attributes from a single exercise block and append to liftList
    public void appendLiftFromBlock(String block, List<Lift> liftList) {
        String name = "";
        double oneRepMax = 0.0;
        int enteredReps = 0;
        double enteredRepsWeight = 0.0;

        // Split the block into lines
        String[] lines = block.split("\n");

        // Extract relevant attributes from each line
        for (String line : lines) {
            if (line.startsWith("Exercise name =")) {
                name = line.split("= ")[1].trim();
            } else if (line.startsWith("One rep max =")) {
                oneRepMax = Double.parseDouble(line.split("= ")[1].trim());
            } else if (line.startsWith("Entered Reps =")) {
                enteredReps = Integer.parseInt(line.split("= ")[1].trim());
            } else if (line.startsWith("Entered Reps weight =")) {
                enteredRepsWeight = Double.parseDouble(line.split("= ")[1].trim());
            }
        }

        // Create a new Lift object and append it to the liftList
        Lift lift = new Lift(oneRepMax, enteredReps, enteredRepsWeight, name);
        liftList.add(lift);
    }

}
