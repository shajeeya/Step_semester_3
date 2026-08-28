package oop.assignment_problems;

public class Problem5 {

    static void classifyWordLengths(String review) {

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        String[] words = review.split("\\s+");

        for (String word : words) {

            int length = word.length();

            if (length >= 1 && length <= 4) {
                shortWords++;
            } else if (length >= 5 && length <= 8) {
                mediumWords++;
            } else {
                longWords++;
            }
        }

        System.out.println(
            "Short: " + shortWords +
            " | Medium: " + mediumWords +
            " | Long: " + longWords
        );
    }

    public static void main(String[] args) {

        String review = "This movie was absolutely fantastic and thrilling";

        classifyWordLengths(review);
    }
}