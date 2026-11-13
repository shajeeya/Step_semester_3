package main.java.linked_list.class_problems;


public class Problem2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        // Find the middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        Node secondHalf = reverse(slow.next);

        Node firstHalf = head;
        Node temp = secondHalf;

        // Compare both halves
        while (temp != null) {
            if (firstHalf.data != temp.data) {
                return false;
            }

            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        return true;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);

        System.out.println(isPalindrome(head));
    }
}
