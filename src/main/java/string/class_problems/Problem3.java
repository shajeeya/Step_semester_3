package string.class_problems;

public class Problem3 {

    static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println(
            "Person\tHeight(m)\tWeight(kg)\tBMI\tStatus"
        );

        System.out.println(
            "------------------------------------------------------"
        );

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            String status = getBmiStatus(bmi);

            System.out.printf(
                "%d\t%.2f\t\t%.2f\t\t%.2f\t%s%n",
                i + 1,
                heights[i],
                weights[i],
                bmi,
                status
            );
        }
    }

    public static void main(String[] args) {

        double[] heights = {
            1.75, 1.60, 1.68, 1.80, 1.55,
            1.72, 1.65, 1.78, 1.62, 1.70
        };

        double[] weights = {
            70, 90, 65, 95, 45,
            80, 72, 60, 85, 68
        };

        printWellnessReport(heights, weights);
    }
}