package tower_of_hanoi;

public class HanoiMovesChecker {

    public static long getNumberOfMoves(int numberOfDisks)
    {
        long result = 0;

        result = (long) Math.pow(2, numberOfDisks) - 1;

        return result;
    }

    public static long getNumberOfMovesForDisk(int diskNumber)
    {
        long result = 0;

        result = (long) Math.pow(2, diskNumber - 1);

        return result;
    }
    
}
