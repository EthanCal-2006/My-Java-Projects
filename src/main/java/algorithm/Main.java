package algorithm;

import java.util.Scanner;

import algorithm.AnagramChecker;

public class Main {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true)
		{
		    printMenu();
		    
		    int choice = getChoice(scanner, "Enter your choice", 1, 2);

		    if (choice == 1)
		    {
		        String word1 = getWord(scanner, "Enter the first word: ");
		        String word2 = getWord(scanner, "Enter the second word: ");

		        printAnagramResults(word1, word2);
		    }
		    else
		    {
		        break;
		    }

		}
		scanner.close();
	}
	
    private static void printAnagramResults(String word1, String word2) {
        if (AnagramChecker.isAnagram(word1, word2)) {
            System.out.println(String.format("%s and %s are anagrams.", word1, word2));
        } else {
            System.out.println(String.format("%s and %s are not anagrams.", word1, word2));
        }
    }

    private static String getWord(Scanner scanner, String message) {
        String word = null;

        while (word == null || word.isEmpty()) {
            System.out.println(message);
            printInputString();
            word = scanner.nextLine().trim();
        }

        return word;
    }

    private static int getChoice(Scanner scanner, String message, int lowerBound, int upperBound) {
        int choice = -1;

        while (choice < lowerBound || choice > upperBound) {
            System.out.println(String.format("%s (%d-%d):", message, lowerBound, upperBound));
            printInputString();
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        return choice;
    }

    private static void printMenu() {
        System.out.println("1. Check if two words are anagrams.");
        System.out.println("2. Exit.");
    }

    private static void printInputString() {
        System.out.print(">>> ");
    }
}