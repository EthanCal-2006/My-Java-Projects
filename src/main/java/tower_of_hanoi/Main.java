package tower_of_hanoi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true)
		{
		    printMenu();

		    int choice = getIntInput(scanner, "Enter your choice");
		        
		    while (choice > 3)
		    {
		        System.out.println("Invalid input. Please try again.");
		        choice = getIntInput(scanner, "Enter your choice");
		    }

		    if (choice == 1)
		    {
		        int num = getIntInput(scanner, "Enter the number of disks");
		        printResultsForTower(num);
		    }
		    else if (choice == 2)
		    {
		        int num = getIntInput(scanner, "Enter the disk number");
		        printResultsForDisk(num);
		    }
		    else
		    {
		        break;
		    }
		}
		scanner.close();
	}
	
	
	static void printResultsForTower(int numDisks) {
	    System.out.printf("Number of moves for tower with %d disks: %d%n",
                numDisks, HanoiMovesChecker.getNumberOfMoves(numDisks));
	}

	static void printResultsForDisk(int diskNum) {
	    System.out.printf("Number of moves for disk %d: %d%n", diskNum,
                HanoiMovesChecker.getNumberOfMovesForDisk(diskNum));
	}

	static int getIntInput(Scanner scanner, String message) {
	    int choice = -1;

	    while (choice < 0) {
	        System.out.println(message);
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

	static void printMenu() {
	    System.out.println("1. Check number of moves for tower with n disks.");
	    System.out.println("2. Check number of moves for disk n.");
	    System.out.println("3. Exit.");
	}

	static void printInputString() {
	    System.out.print(">>> ");
	}

}
