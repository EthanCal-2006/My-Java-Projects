package kill_circle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a number of people to be in the kill circle: ");
        String inToInt = scnr.nextLine();
        int numPeople = Integer.parseInt(inToInt);
        boolean[] people = new boolean[numPeople];

        boolean killTime = false;
        Arrays.fill(people, true); // everyone is alive initially
        int index = 0;

        while (!hasOnlyOneTrue(people)) {
            if (people[index]) {
                if (killTime) {
                    people[index] = false; // Kill this person
                    killTime = false;
                } else {
                    killTime = true; // Skip this person, next one gets killed
                }
            }

            index = (index + 1) % numPeople; // Go to the next person in circle
        }

        int survivor = indexOfOnlyTrue(people);
        System.out.println("Index of last survivor: " + survivor);
    }

    public static boolean hasOnlyOneTrue(boolean[] arr) {
        int trueCount = 0;

        for (boolean value : arr) {
            if (value) {
                trueCount++;
                if (trueCount > 1) {
                    return false; // Early exit if more than one true
                }
            }
        }

        return trueCount == 1;
    }

    public static int indexOfOnlyTrue(boolean[] arr) {
        int trueIndex = -1;
        int trueCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                trueCount++;
                if (trueCount > 1) {
                    return -1; // More than one true found
                }
                trueIndex = i; // Store the index of the true value
            }
        }

        return trueCount == 1 ? trueIndex : -1;
    }
}
