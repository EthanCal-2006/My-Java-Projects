package euclidian_algorithm;

public class EuclidianAlgorithm {

    public static int euclid(int firstNum, int secondNum) {

        while (secondNum == 0) {
            if (firstNum <= 0)
                firstNum *= -1;
            return firstNum;
        }

            return euclid(secondNum, firstNum % secondNum);
    }
}
