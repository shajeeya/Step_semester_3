
package sorting_algorithms.class_problems;

public class Problem5 {

    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || k <= 0 || k > sales.length) {
            throw new IllegalArgumentException("Invalid window size.");
        }

        int windowSum = 0;

        // Calculate the sum of the first window.
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;

        // Slide the window one position at a time.
        for (int i = k; i < sales.length; i++) {
            windowSum = windowSum - sales[i - k] + sales[i];

            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println("Maximum subarray sum: "
                + maxSumSubarray(sales, k));
    }
}
