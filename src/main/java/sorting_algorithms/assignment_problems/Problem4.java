
package sorting_algorithms.assignment_problems;

public class Problem4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the list.
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half.
        Node secondHalf = reverse(slow.next);
        Node first = head;
        Node second = secondHalf;
        boolean palindrome = true;

        // Compare both halves.
        while (second != null) {
            if (first.data != second.data) {
                palindrome = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original list.
        slow.next = reverse(secondHalf);

        return palindrome;
    }

    private static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node first = new Node(1);
        first.next = new Node(2);
        first.next.next = new Node(3);
        first.next.next.next = new Node(2);
        first.next.next.next.next = new Node(1);

        System.out.println(isPalindrome(first));
        printList(first);

        Node second = new Node(1);
        second.next = new Node(2);
        second.next.next = new Node(3);
        second.next.next.next = new Node(2);

        System.out.println(isPalindrome(second));
        printList(second);

        Node third = new Node(1);
        third.next = new Node(2);
        third.next.next = new Node(2);
        third.next.next.next = new Node(1);

        System.out.println(isPalindrome(third));
        printList(third);
    }
}
