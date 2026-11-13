package main.java.linked_list.class_problems;

public class Problem1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node removeNthFromEnd(Node head, int n) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both pointers together
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete the required node
        slow.next = slow.next.next;

        return dummy.next;
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

        head = removeNthFromEnd(head, 2);

        System.out.println("After Removing:");
        display(head);

        // Test removing the head
        Node head2 = new Node(1);
        head2 = removeNthFromEnd(head2, 1);

        System.out.println("After Removing Head:");
        display(head2);
    }
}
