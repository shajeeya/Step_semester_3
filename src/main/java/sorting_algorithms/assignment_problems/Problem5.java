
package sorting_algorithms.assignment_problems;

public class Problem5 {

    static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
            } else if (q.exponent > p.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
            } else {
                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
                continue;
            }

            tail = tail.next;
        }

        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void printPolynomial(Node head) {
        boolean first = true;

        while (head != null) {
            int coefficient = head.coefficient;
            int exponent = head.exponent;

            if (!first) {
                System.out.print(coefficient >= 0 ? " + " : " - ");
            } else if (coefficient < 0) {
                System.out.print("-");
            }

            int absoluteCoefficient = Math.abs(coefficient);

            if (exponent == 0 || absoluteCoefficient != 1) {
                System.out.print(absoluteCoefficient);
            }

            if (exponent > 0) {
                System.out.print("x");

                if (exponent > 1) {
                    System.out.print("^" + exponent);
                }
            }

            first = false;
            head = head.next;
        }

        if (first) {
            System.out.print("0");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: 5x^3 + 4x^2 + 2
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        // 5x^2 - 3x + 1
        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        printPolynomial(addPolynomials(p, q));

        // Sample 2: 3x^2 + 1
        Node a = new Node(3, 2);
        a.next = new Node(1, 0);

        // -3x^2 + 4x
        Node b = new Node(-3, 2);
        b.next = new Node(4, 1);

        printPolynomial(addPolynomials(a, b));
    }
}
