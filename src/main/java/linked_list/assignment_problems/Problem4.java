package main.java.linked_list.assignment_problems;


class BusBay {

    int[] bus;
    int front;
    int size;
    int capacity;

    BusBay(int capacity) {
        this.capacity = capacity;
        bus = new int[capacity];
        front = 0;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    boolean insertFront(int value) {

        if (isFull()) {
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        bus[front] = value;
        size++;

        return true;
    }

    boolean insertLast(int value) {

        if (isFull()) {
            return false;
        }

        int rear = (front + size) % capacity;
        bus[rear] = value;
        size++;

        return true;
    }

    boolean deleteFront() {

        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;

        return true;
    }

    boolean deleteLast() {

        if (isEmpty()) {
            return false;
        }

        size--;

        return true;
    }

    int getFront() {

        if (isEmpty()) {
            return -1;
        }

        return bus[front];
    }

    int getRear() {

        if (isEmpty()) {
            return -1;
        }

        int rear = (front + size - 1) % capacity;

        return bus[rear];
    }
}

public class Problem4 {

    public static void main(String[] args) {

        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));
        System.out.println(b.insertLast(102));
        System.out.println(b.insertFront(100));
        System.out.println(b.insertFront(99));
        System.out.println(b.getRear());
        System.out.println(b.isFull());
        System.out.println(b.deleteLast());
        System.out.println(b.insertFront(98));
        System.out.println(b.getFront());
    }
}
