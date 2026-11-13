package main.java.linked_list.assignment_problems;


import java.util.Stack;

public class Problem3 {

    static int evaluate(String expr) {

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expr.length()) {

            char ch = expr.charAt(i);

            if (ch == ' ') {
                i++;
            }

            else if (Character.isDigit(ch)) {

                int num = 0;

                while (i < expr.length()
                        && Character.isDigit(expr.charAt(i))) {

                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                numbers.push(num);
            }

            else if (ch == '(') {
                operators.push(ch);
                i++;
            }

            else if (ch == ')') {

                while (!operators.isEmpty()
                        && operators.peek() != '(') {

                    calculate(numbers, operators);
                }

                operators.pop();
                i++;
            }

            else {

                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {

                    calculate(numbers, operators);
                }

                operators.push(ch);
                i++;
            }
        }

        while (!operators.isEmpty()) {
            calculate(numbers, operators);
        }

        return numbers.pop();
    }

    static int precedence(char op) {

        if (op == '*' || op == '/') {
            return 2;
        }

        return 1;
    }

    static void calculate(Stack<Integer> numbers,
                          Stack<Character> operators) {

        int b = numbers.pop();
        int a = numbers.pop();

        char op = operators.pop();

        int result = 0;

        switch (op) {

            case '+':
                result = a + b;
                break;

            case '-':
                result = a - b;
                break;

            case '*':
                result = a * b;
                break;

            case '/':
                result = a / b;
                break;
        }

        numbers.push(result);
    }

    public static void main(String[] args) {

        String expr1 = "2 * (3 + 4) - 10 / 5";
        String expr2 = "(8 - 2) * (1 + 2) / 4";

        System.out.println(evaluate(expr1));
        System.out.println(evaluate(expr2));
    }
}
