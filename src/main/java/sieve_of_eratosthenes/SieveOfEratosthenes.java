package sieve_of_eratosthenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

public class SieveOfEratosthenes {
    public static int[] calculateSieveOfEratosthenes(int number) {
        int[] A = new int[number + 1];
        for (int p = 2; p <= number; p++) {
            A[p] = p;
        }//initializes all numbers from 0 to number as true

        for (int p = 2; p <= sqrt(number); p++) {
            if (A[p] != 0) {
                int j = p * p;
                while (j <= number) {
                    A[j] = 0;
                    j = j + p;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= number; p++) {
            if (A[p] != 0) {
                primes.add(A[p]);
            }
        }

        return primes.stream().mapToInt(Integer::intValue).toArray();
    }
}
