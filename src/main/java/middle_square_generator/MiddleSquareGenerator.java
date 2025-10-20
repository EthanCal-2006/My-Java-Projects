package middle_square_generator;

public class MiddleSquareGenerator {
    private long seed;

    public MiddleSquareGenerator(long seed) {
        this.seed = seed;
    }

    public long next() {
        long square = seed * seed;
        String squareString = String.format("%08d", square);
        seed = Long.parseLong(squareString.substring(2,6));
        return seed;
    }
}
