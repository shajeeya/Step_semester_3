package oop.class_problems;

public class Problem4 {

    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }
    }

    static class SrmStudent {
        String name;
        String regNo;
        int attendance;

        static String university = "SRMIST";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;

            admissionCount++;

            this.regNo =
                "RA231100301" +
                String.format("%03d", admissionCount);
        }

        void printIdCard() {
            System.out.println(
                name + " | " + regNo
            );
        }

        static void printTotalAdmissions() {
            System.out.println(
                "Students admitted so far: " + admissionCount
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenSrmStudent ravi =
            new BrokenSrmStudent("Ravi", "RA231100301011", 82);

        BrokenSrmStudent meera =
            new BrokenSrmStudent("Meera", "RA231100301012", 74);

        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        // Static is wrong for name, regNo and attendance because
        // each student needs independent values.
        // They are instance fields in the corrected version.
        // university and admissionCount are shared by all students,
        // so they are correctly static.

        System.out.println("\nFixed version:");

        SrmStudent student1 =
            new SrmStudent("Ravi", 82);

        SrmStudent student2 =
            new SrmStudent("Meera", 74);

        student1.printIdCard();
        student2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}