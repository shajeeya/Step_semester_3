package main.java.linked_list.class_problems;


public class Problem5 {

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

    static void reorderList(Node head) {

        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;

        // Find the middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list
        Node secondHalf = slow.next;
        slow.next = null;

        // Reverse the second half
        secondHalf = reverse(secondHalf);

        // Merge both halves alternately
        Node first = head;

        while (secondHalf != null) {

            Node nextFirst = first.next;
            Node nextSecond = secondHalf.next;

            first.next = secondHalf;
            secondHalf.next = nextFirst;

            first = nextFirst;
            secondHalf = nextSecond;
        }
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        display(head);

        reorderList(head);

        System.out.println("Reordered List:");
        display(head);
    }
}
