
package sorting_algorithms.assignment_problems;

public class Problem2 {

    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long windowSum = 0;

        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            windowSum += costs[right];

            while (windowSum > budget && left <= right) {
                windowSum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        long budget = 8;

        int[] result = longestStreak(costs, budget);

        System.out.println(
            "Longest streak: (" + result[0] + ", " + result[1] + ")"
        );

        int[] costs2 = {9, 10};
        long budget2 = 8;

        int[] result2 = longestStreak(costs2, budget2);

        System.out.println(
            "Longest streak: (" + result2[0] + ", " + result2[1] + ")"
        );
    }
}
