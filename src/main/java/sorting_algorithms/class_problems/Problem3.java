
package sorting_algorithms.class_problems;

public class Problem3 {

    public static void main(String[] args) {
        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        // Calculate total marks for each student.
        for (int i = 0; i < students.length; i++) {
            int total = 0;

            for (int j = 0; j < subjects.length; j++) {
                if (marks[i][j] != -1) {
                    total += marks[i][j];
                }
            }

            System.out.println("Total " + students[i] + " " + total);
        }

        // Find the topper for each subject.
        for (int j = 0; j < subjects.length; j++) {
            int highestMark = -1;
            String topper = "";

            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] > highestMark) {
                    highestMark = marks[i][j];
                    topper = students[i];
                }
            }

            System.out.println("Topper " + subjects[j] + " "
                    + topper + " " + highestMark);
        }
    }
}
