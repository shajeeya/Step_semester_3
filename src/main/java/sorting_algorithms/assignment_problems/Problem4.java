
package sorting_algorithms.assignment_problems;

public class Problem4 {

    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || low > high) {
            return 0;
        }

        int first = lowerBound(scores, low);
        int afterLast = upperBound(scores, high);

        return afterLast - first;
    }

    // Finds the first index whose score is >= target.
    private static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Finds the first index whose score is > target.
    private static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}
