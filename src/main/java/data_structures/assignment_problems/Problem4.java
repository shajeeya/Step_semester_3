package data_structures.assignment_problems;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem4 {

    interface CreditPolicy {
        int getCreditLimit();
        String getType();
    }

    static class RegularPolicy implements CreditPolicy {

        @Override
        public int getCreditLimit() {
            return 24;
        }

        @Override
        public String getType() {
            return "Regular";
        }
    }

    static class HonorsPolicy implements CreditPolicy {

        @Override
        public int getCreditLimit() {
            return 28;
        }

        @Override
        public String getType() {
            return "Honors";
        }
    }

    static class ExchangePolicy implements CreditPolicy {

        @Override
        public int getCreditLimit() {
            return 20;
        }

        @Override
        public String getType() {
            return "Exchange";
        }
    }

    static class Student {

        private String name;
        private int currentCredits;
        private CreditPolicy creditPolicy;

        public Student(
                String name,
                int currentCredits,
                CreditPolicy creditPolicy) {

            this.name = name;
            this.currentCredits = currentCredits;
            this.creditPolicy = creditPolicy;
        }

        public String getName() {
            return name;
        }

        public int getCurrentCredits() {
            return currentCredits;
        }

        public CreditPolicy getCreditPolicy() {
            return creditPolicy;
        }

        public boolean canAddCredits(int credits) {
            return currentCredits + credits
                    <= creditPolicy.getCreditLimit();
        }

        public void addCredits(int credits) {
            currentCredits += credits;
        }

        public void removeCredits(int credits) {
            currentCredits -= credits;
        }
    }

    static class Elective {

        private String name;
        private int credits;
        private int capacity;

        private List<Student> enrolledStudents;

        private Queue<Student> waitlist;

        public Elective(
                String name,
                int credits,
                int capacity) {

            this.name = name;
            this.credits = credits;
            this.capacity = capacity;

            enrolledStudents = new LinkedList<>();
            waitlist = new LinkedList<>();
        }

        public String getName() {
            return name;
        }

        public int getCredits() {
            return credits;
        }

        public boolean isFull() {
            return enrolledStudents.size() >= capacity;
        }

        public boolean isEnrolled(Student student) {
            return enrolledStudents.contains(student);
        }

        public boolean isWaitlisted(Student student) {
            return waitlist.contains(student);
        }

        public boolean canJoin(Student student) {
            return !isEnrolled(student)
                    && !isWaitlisted(student);
        }

        public boolean enroll(Student student) {

            if (isFull()) {
                return false;
            }

            enrolledStudents.add(student);
            student.addCredits(credits);

            System.out.println(
                    student.getName() +
                    " enrolled in " +
                    name +
                    " (credits: " +
                    student.getCurrentCredits() +
                    "/" +
                    student.getCreditPolicy().getCreditLimit() +
                    ")."
            );

            return true;
        }

        public int addToWaitlist(Student student) {

            waitlist.add(student);

            return waitlist.size();
        }

        public Student drop(Student student) {

            if (!enrolledStudents.remove(student)) {
                return null;
            }

            student.removeCredits(credits);

            System.out.println(
                    student.getName() +
                    " dropped " +
                    name +
                    " (credits: " +
                    student.getCurrentCredits() +
                    "/" +
                    student.getCreditPolicy().getCreditLimit() +
                    ")."
            );

            return student;
        }

        public Student getNextWaitlistedStudent() {
            return waitlist.poll();
        }
    }

    static class EnrollmentService {

        public void enroll(
                Student student,
                Elective elective) {

            // Credit limit must be checked first.
            if (!student.canAddCredits(elective.getCredits())) {

                System.out.println(
                        "Enrollment failed: " +
                        student.getName() +
                        " would exceed the " +
                        student.getCreditPolicy().getType() +
                        " credit limit (" +
                        (student.getCurrentCredits()
                                + elective.getCredits()) +
                        "/" +
                        student.getCreditPolicy().getCreditLimit() +
                        ")."
                );

                return;
            }

            // Prevent duplicate enrollment/waitlisting.
            if (!elective.canJoin(student)) {

                System.out.println(
                        "Enrollment failed: " +
                        student.getName() +
                        " is already enrolled or waitlisted."
                );

                return;
            }

            if (!elective.isFull()) {

                elective.enroll(student);

            } else {

                System.out.println(
                        elective.getName() +
                        " is full."
                );

                int position =
                        elective.addToWaitlist(student);

                System.out.println(
                        student.getName() +
                        " added to waitlist (position " +
                        position +
                        ")."
                );
            }
        }

        public void drop(
                Student student,
                Elective elective) {

            Student dropped =
                    elective.drop(student);

            if (dropped == null) {
                return;
            }

            promoteNextStudent(elective);
        }

        private void promoteNextStudent(
                Elective elective) {

            Student next;

            while ((next =
                    elective.getNextWaitlistedStudent()) != null) {

                // Credit limit is checked again during promotion.
                if (next.canAddCredits(elective.getCredits())) {

                    elective.enroll(next);

                    System.out.println(
                            next.getName() +
                            " promoted from waitlist and enrolled in " +
                            elective.getName() +
                            " (credits: " +
                            next.getCurrentCredits() +
                            "/" +
                            next.getCreditPolicy().getCreditLimit() +
                            ")."
                    );

                    return;

                } else {

                    System.out.println(
                            next.getName() +
                            " cannot be promoted because the " +
                            next.getCreditPolicy().getType() +
                            " credit limit would be exceeded."
                    );
                }
            }
        }
    }

    public static void main(String[] args) {

        Elective cloudComputing =
                new Elective(
                        "Cloud Computing",
                        4,
                        2
                );

        Student asha =
                new Student(
                        "Asha",
                        20,
                        new RegularPolicy()
                );

        Student ravi =
                new Student(
                        "Ravi",
                        22,
                        new HonorsPolicy()
                );

        Student neha =
                new Student(
                        "Neha",
                        12,
                        new ExchangePolicy()
                );

        Student kiran =
                new Student(
                        "Kiran",
                        22,
                        new RegularPolicy()
                );

        EnrollmentService service =
                new EnrollmentService();

        service.enroll(asha, cloudComputing);

        service.enroll(ravi, cloudComputing);

        service.enroll(neha, cloudComputing);

        service.enroll(kiran, cloudComputing);

        service.drop(asha, cloudComputing);
    }
}