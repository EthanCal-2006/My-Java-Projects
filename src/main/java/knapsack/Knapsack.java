package knapsack;

import java.util.List;

public class Knapsack {

    static class Item{
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static int knapsack(List<Item> items, int weightLimit) {
        int n = items.size();
        int maxValue = 0;

        int totalCombinations = (int) Math.pow(2, n);
        //* totalCombinations = 2^n
        //* also could use totalCombinations = (1 << n) via binary shifting

        for(int i = 0; i < totalCombinations; i++) {//*iterate thru each combination
            int currWeight = 0;
            int currValue = 0;

            for (int j = 0; j < n; j++) {//* iterate thru each item
                if ((i & (1 << j)) != 0) {//* bitwise AND; only true if both i and (1 << j) are 1
                    currWeight += items.get(j).weight;
                    currValue += items.get(j).value;
                }
            }

            if (currWeight <= weightLimit) {
                maxValue = Math.max(maxValue, currValue);
            }

        }//outerloop
        return maxValue;
    }
}
