package main.java.linked_list.assignment_problems;


public class Problem1 {

    static int[] priceStreak(int[] price) {

        int n = price.length;
        int[] result = new int[n];
        int[] stack = new int[n];

        int top = -1;

        for (int i = 0; i < n; i++) {

            // Remove previous days with price <= today's price
            while (top != -1 && price[stack[top]] <= price[i]) {
                top--;
            }

            // Calculate the streak
            if (top == -1) {
                result[i] = i + 1;
            } else {
                result[i] = i - stack[top];
            }

            // Push today's index
            stack[++top] = i;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] price = {100, 80, 60, 70, 60, 75, 85};

        int[] result = priceStreak(price);

        System.out.print("Price Streak: ");

        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}
