
package sorting_algorithms.class_problems;

public class Problem4 {

    public static int lowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int upperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int countInRange(int[] prices, int low, int high) {
        if (low > high) {
            return 0;
        }

        int lower = lowerBound(prices, low);
        int upper = upperBound(prices, high);

        System.out.println("Lower bound index " + lower);
        System.out.println("Upper bound index " + upper);

        return upper - lower;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int count = countInRange(prices, 150, 300);

        System.out.println("Count " + count);
    }
}
