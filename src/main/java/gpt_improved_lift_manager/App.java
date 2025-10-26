package gpt_improved_lift_manager;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final String[] ANSI_COLORS = {
            "\u001B[31m", "\u001B[32m", "\u001B[33m",
            "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"
    };

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        PrintManager printManager = new PrintManager();
        LiftManager liftManager = new LiftManager();
        SplitManager splitManager = new SplitManager();

        printManager.printOpeningStatements();
        printManager.printChoices();

        int choice;
        while ((choice = readInt(scnr)) != 0) {
            switch (choice) {
                case 1 -> showLifts(liftManager);
                case 2 -> addLift(scnr, liftManager);
                case 3 -> removeLift(scnr, liftManager);
                case 4 -> liftManager.writeLiftsToFile(liftManager.getLiftList(), "Lifts.txt");
                case 5 -> liftManager.extractAndAppendToLiftList("Lifts.txt", liftManager.getLiftList());
                case 6 -> splitManager.createSplitList(liftManager.getLiftList());
                case 7 -> splitManager.saveWorkoutToFile(
                        System.getProperty("user.home") + "\\Downloads\\workout.txt", splitManager.getSplitList()
                );
                case 8 -> clearLiftList(scnr, liftManager);
                default -> System.out.println("Invalid input. Try again.");
            }
            printManager.printChoices();
        }

        System.out.println("Thank you. Goodbye.");
        scnr.close();
    }

    private static void showLifts(LiftManager liftManager) {
        List<Lift> lifts = liftManager.getLiftList();
        for (int i = 0; i < lifts.size(); i++) {
            System.out.print(ANSI_COLORS[i % 2]);
            System.out.println("Lift " + (i + 1) + ":\n" + lifts.get(i));
        }
    }

    private static void addLift(Scanner scnr, LiftManager liftManager) {
        System.out.println(ANSI_COLORS[4] + "Adding a new lift...");
        System.out.print("Exercise name: ");
        String name = scnr.nextLine();

        System.out.print("Weight (lbs): ");
        double weight = readDouble(scnr);

        System.out.print("Reps performed: ");
        int reps = readInt(scnr);

        Lift lift = new Lift(reps, weight, name);
        liftManager.addNewLift(lift);
        System.out.println("Lift added.");
    }

    private static void removeLift(Scanner scnr, LiftManager liftManager) {
        List<Lift> lifts = liftManager.getLiftList();
        if (lifts.isEmpty()) {
            System.out.println("No lifts to remove.");
            return;
        }

        System.out.println("Choose a lift to remove:");
        for (int i = 0; i < lifts.size(); i++) {
            System.out.println((i + 1) + ": " + lifts.get(i).getExerciseName());
        }

        int index = readInt(scnr);
        if (index >= 1 && index <= lifts.size()) {
            liftManager.removeLift(index);
            System.out.println("Lift removed.");
        } else {
            System.out.println("Invalid lift number.");
        }
    }

    private static void clearLiftList(Scanner scnr, LiftManager liftManager) {
        System.out.println("Are you sure you want to clear your lift list? (y/n)");
        String input = scnr.nextLine().trim().toLowerCase();
        if ("y".equals(input)) {
            liftManager.clearList();
            System.out.println("Lift list cleared.");
        } else {
            System.out.println("Canceled.");
        }
    }

    private static int readInt(Scanner scnr) {
        while (true) {
            try {
                return Integer.parseInt(scnr.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again:");
            }
        }
    }

    private static double readDouble(Scanner scnr) {
        while (true) {
            try {
                return Double.parseDouble(scnr.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again:");
            }
        }
    }
}
