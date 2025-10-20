package sieve_of_eratosthenes;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Please enter a number to find prime numbers for the Sieve: ");
        String lineToNumber = scnr.nextLine();
        int number = Integer.parseInt(lineToNumber);
        System.out.println("The prime numbers up to " + number + " are: " + Arrays.toString(SieveOfEratosthenes.calculateSieveOfEratosthenes(number)));
    }
}
