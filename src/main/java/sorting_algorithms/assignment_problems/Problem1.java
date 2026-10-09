
package sorting_algorithms.assignment_problems;

public class Problem1 {

    public static int[] footfallReport(int[] visitors, int[][] queries) {
        int[] prefix = new int[visitors.length + 1];

        for (int i = 0; i < visitors.length; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        int[] results = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            results[i] = prefix[end + 1] - prefix[start];
        }

        return results;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        int[] results = footfallReport(visitors, queries);

        System.out.print("Footfall report: [");
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i]);
            if (i < results.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
