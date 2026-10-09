
package sorting_algorithms.class_problems;

public class Problem2 {

    public static void warehouseSummary(int[][] grid) {
        int total = 0;
        int max = -1;
        int maxRow = -1;
        int maxCol = -1;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                total += grid[row][col];

                if (grid[row][col] > max) {
                    max = grid[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        System.out.println("Total items: " + total);
        System.out.println(
            "Max bin coordinates: (" + maxRow + ", " + maxCol + ")"
        );
        System.out.println("Maximum items: " + max);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}
