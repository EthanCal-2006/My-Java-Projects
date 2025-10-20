package random_numbers;

public class LinearCongruentialGenerator {

    private long seed;
    private final long multiplier;
    private final long increment;
    private final long modulus;

    public LinearCongruentialGenerator(long seed, long multiplier, long increment, long modulus) {
        this.seed = seed;
        this.multiplier = multiplier;
        this.increment = increment;
        this.modulus = modulus;
    }

    public long next() {
        seed = (multiplier * seed + increment) % modulus;
        return seed;
    }
}
