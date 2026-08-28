package oop.assignment_problems;

class Member {

    String name;
    int booksIssued;

    static int totalMembers = 0;

    Member(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        totalMembers++;
    }

    void displayDetails() {
        System.out.println(
            "Name: " + name +
            " | Books Issued: " + booksIssued
        );
    }

    static void displayTotalMembers() {
        System.out.println("Total Members: " + totalMembers);
    }
}

public class Problem4 {

    public static void main(String[] args) {

        Member member1 = new Member("Asha", 2);
        Member member2 = new Member("Bala", 1);
        Member member3 = new Member("Charan", 3);

        member1.displayDetails();
        member2.displayDetails();
        member3.displayDetails();

        Member.displayTotalMembers();
    }
}