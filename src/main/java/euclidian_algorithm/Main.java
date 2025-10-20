//Ethan Calhoun
package euclidian_algorithm;

import org.w3c.dom.ls.LSInput;

import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int userChoice = 1;

        while (true) {
            System.out.println("Enter 1 to check GCD of 2 numbers, enter 2 to exit: ");
            userChoice = scnr.nextInt();
            if (!((userChoice == 1) || (userChoice == 2))) {
                System.out.println("Invalid number. Please type 1 or 2 only.");
            continue;
            }
            if (userChoice == 2) {
                System.out.println("Goodbye.");
                break;
            }
            else if (userChoice == 1)
                System.out.println("Enter the first number: ");
                int firstNum = scnr.nextInt();

                System.out.println("Enter the second number: ");
                int secondNum = scnr.nextInt();

                int gcd = EuclidianAlgorithm.euclid(firstNum, secondNum);
                System.out.println("The GCD of " + firstNum + " and " + secondNum + " is: " + gcd);
        }
    }
}