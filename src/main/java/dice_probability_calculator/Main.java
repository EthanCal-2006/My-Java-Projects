package dice_probability_calculator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for target value
        System.out.print("What value must you equal or exceed for this roll?: ");
        int userValue = Integer.parseInt(scanner.nextLine());

        // Collect selected dice
        List<Integer> selectedDice = new ArrayList<>();

        System.out.print("Do you have a D4? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(4);

        System.out.print("Do you have a D6? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(6);

        System.out.print("Do you have a D8? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(8);

        System.out.print("Do you have a D10? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(10);

        System.out.print("Do you have a D12? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(12);

        System.out.print("Do you have a D20? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) selectedDice.add(20);

        // Compute total possible combinations
        int possibleOutcomes = 1;
        for (int sides : selectedDice) {
            possibleOutcomes *= sides;
        }

        // Handle case where no dice are selected
        if (selectedDice.isEmpty()) {
            System.out.println("No dice selected. Exiting.");
            return;
        }

        // Compute favorable outcomes
        int favorableOutcomes = countFavorableOutcomesBruteForce(selectedDice, userValue);

        // Output results
        System.out.println("\nTotal possible outcomes: " + possibleOutcomes);
        System.out.println("The total Favorable outcomes: " + favorableOutcomes);

        double probability = (double) favorableOutcomes / possibleOutcomes * 100;
        System.out.printf("The odds of tying or exceeding the threshold (" + userValue + ") is: %.2f%%\n", probability);
    }

    // Updated to accept List<Integer>
    public static int countFavorableOutcomesBruteForce(List<Integer> diceSides, int targetSum) {
        return countWays(diceSides, 0, 0, targetSum);
    }

    private static int countWays(List<Integer> diceSides, int index, int currentSum, int targetSum) {
        // Base case: all dice have been rolled
        if (index == diceSides.size()) {
            return currentSum >= targetSum ? 1 : 0;
        }

        int count = 0;
        int sides = diceSides.get(index);

        // Try all face values for current die
        for (int face = 1; face <= sides; face++) {
            count += countWays(diceSides, index + 1, currentSum + face, targetSum);
        }

        return count;
    }

}
