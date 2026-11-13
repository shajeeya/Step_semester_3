package main.java.linked_list.class_problems;


public class Problem4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node findIntersection(Node a, Node b) {

        int lenA = 0;
        int lenB = 0;

        Node tempA = a;
        Node tempB = b;

        // Find lengths of both lists
        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }

        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }

        // Move the longer list ahead
        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }

        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        // Find the common node
        while (a != null && b != null) {
            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {

        // Common part of both lists
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        // First list: 4 -> 1 -> 8 -> 4 -> 5
        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = common;

        // Second list: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = common;

        Node result = findIntersection(a, b);

        if (result != null) {
            System.out.println("Intersection Node: " + result.data);
        } else {
            System.out.println("No Intersection");
        }
    }
}
