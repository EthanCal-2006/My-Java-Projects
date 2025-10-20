package tower_of_hanoi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class tests {

    @Test
    void test1Disk() {
        int numDisks = 1;
        long expected = 1;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void test2Disks() {
        int numDisks = 2;
        long expected = 3;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void test3Disks() {
        int numDisks = 3;
        long expected = 7;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void test4Disks() {
        int numDisks = 4;
        long expected = 15;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void test5Disks() {
        int numDisks = 5;
        long expected = 31;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void test6Disks() {
        int numDisks = 6;
        long expected = 63;

        long actual = HanoiMovesChecker.getNumberOfMoves(numDisks);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk1() {
        int diskNum = 1;
        long expected = 1;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk2() {
        int diskNum = 2;
        long expected = 2;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk3() {
        int diskNum = 3;
        long expected = 4;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk4() {
        int diskNum = 4;
        long expected = 8;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk5() {
        int diskNum = 5;
        long expected = 16;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }

    @Test
    void testDisk6() {
        int diskNum = 6;
        long expected = 32;

        long actual = HanoiMovesChecker.getNumberOfMovesForDisk(diskNum);

        assertEquals(expected, actual);
    }
}
