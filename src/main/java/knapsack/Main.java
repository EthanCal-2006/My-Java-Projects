package knapsack;

import java.util.ArrayList;
import java.util.List;

import static knapsack.Knapsack.knapsack;

public class Main {
    static void main() {
        List<Knapsack.Item> items = new ArrayList<>();
        items.add(new Knapsack.Item(2,3));
        items.add(new Knapsack.Item(3,4));
        items.add(new Knapsack.Item(4,5));
        items.add(new Knapsack.Item(5,8));
        items.add(new Knapsack.Item(1,1));
        items.add(new Knapsack.Item(6,9));
        items.add(new Knapsack.Item(3,6));
        items.add(new Knapsack.Item(7,10));
        items.add(new Knapsack.Item(4,7));
        items.add(new Knapsack.Item(8,12));
        items.add(new Knapsack.Item(5,8));
        items.add(new Knapsack.Item(3,5));
        items.add(new Knapsack.Item(9,14));
        items.add(new Knapsack.Item(6,10));

        int weightLimit = 15;
        int result = knapsack(items, weightLimit);
        System.out.println("There are a total of " + items.size() + " in the knapsack");
        System.out.println("Max value in knapsack: " + result);


    }

}
