
package sorting_algorithms.assignment_problems;

import java.util.HashMap;
import java.util.Map;

public class Problem3 {

    public static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixFrequency = new HashMap<>();

        // An empty prefix has sum 0.
        prefixFrequency.put(0L, 1);

        long prefixSum = 0;
        long count = 0;

        for (int transaction : transactions) {
            prefixSum += transaction;

            // Earlier prefix sums that produce a period totaling k.
            count += prefixFrequency.getOrDefault(prefixSum - k, 0);

            // Record this prefix sum.
            prefixFrequency.put(
                prefixSum,
                prefixFrequency.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        long k = 7;

        System.out.println(
            "Matching periods: " + countPeriods(transactions, k)
        );

        int[] transactions2 = {1, 2, 3};
        long k2 = 10;

        System.out.println(
            "Matching periods: " + countPeriods(transactions2, k2)
        );
    }
}
