package random_numbers;

public class Main {
    public static void main(String[] args) {
        long seed = 12345L;
        long multiplier = 129321L;
        long increment = 21467839L;
        long modulus = (1L << 32);

        int numberOfRandomNumbers = 1_000_000;

        LinearCongruentialGenerator randomLCG = new LinearCongruentialGenerator(seed, multiplier, increment, modulus);

        for (int i = 0; i < numberOfRandomNumbers; i++) {
            int x = 2;
            System.out.println(randomLCG.next());
        }
    }
}
