package middle_square_generator;

public class Main {
    public static void main(String[] args) {
        long seed = 12345L;
        long numOfRandom = 1_000_000;

        MiddleSquareGenerator msg = new MiddleSquareGenerator(seed);

        for (int i = 0; i < numOfRandom; i++) {
            System.out.println(msg.next());
        }
    }
}
