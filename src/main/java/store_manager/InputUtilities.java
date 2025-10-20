package store_manager;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class to handle user input in a centralized manner.
 * Provides reusable methods to read integers and strings from the console,
 * performs validation and error handling.
 * Use this class to avoid repetitive code in your application.
 */
public class InputUtilities {

    // reuse the same Scanner object for all input operations
    // close the Scanner when the class is no longer needed
    private Scanner scanner = new Scanner(System.in);

     /**
     * Reads an integer from the console with error handling.
     * @param prompt The message to prompt the user for input.
     * @return The valid integer entered by the user.
     * 
     * To use this method, create an instance of InputUtilities in your class and call the readInt() method with a prompt message.
     * For example: InputUtilities inputUtilities = new InputUtilities();
     * int number = inputUtilities.readInt("Enter an integer: ");
     */
    public  int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(prompt);
                result = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return result;
    }


    /**
     * Reads an integer from the console with error handling and validation.
     * @param prompt The message to prompt the user for input.
     * @param min The minimum value allowed.
     * @param max The maximum value allowed.
     * @return The valid integer entered by the user within the specified range.
     * To use this method, create an instance of InputUtilities in your class and call the readInt() method with a prompt message, minimum and maximum values.
     * For example: InputUtilities inputUtilities = new InputUtilities();
     * int number = inputUtilities.readInt("Enter an integer between 1 and 10: ", 1, 10);
     */
    public int readInt(String prompt, int min, int max) {
        // this is an example of overloading a method since it has the same name as the other readInt method but with different parameters
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(prompt);
                result = Integer.parseInt(scanner.nextLine());
                if (result >= min && result <= max) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return result;
    } 

    public boolean readBoolean(String prompt) {
        boolean result = false;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(prompt + " (true/false):");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                result = Boolean.parseBoolean(input);
                isValid = true;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
        return result;
    }


    public LocalDate readLocalDate(String prompt, String pattern) {
        LocalDate result = null;
        boolean isValid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        while (!isValid) {
            try {
                System.out.println(prompt + " (format: " + pattern + ")");
                result = LocalDate.parse(scanner.nextLine(), formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input. Please enter a date in the format " + pattern + ".");
            }
        }
        return result;
    }
}
