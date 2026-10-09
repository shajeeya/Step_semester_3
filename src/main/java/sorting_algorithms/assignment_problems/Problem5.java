
package sorting_algorithms.assignment_problems;

import java.util.ArrayList;
import java.util.List;

public class Problem5 {

    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            // Traverse the top row from left to right.
            for (int col = left; col <= right; col++) {
                result.add(grid[top][col]);
            }
            top++;

            // Traverse the right column from top to bottom.
            for (int row = top; row <= bottom; row++) {
                result.add(grid[row][right]);
            }
            right--;

            // Traverse the bottom row from right to left.
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(grid[bottom][col]);
                }
                bottom--;
            }

            // Traverse the left column from bottom to top.
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(grid[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println("Audit route: " + auditRoute(grid));
    }
}
