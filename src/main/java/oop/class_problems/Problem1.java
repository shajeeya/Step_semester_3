package oop.class_problems;

public class Problem1 {

    static class SrmStudent {
        String name;
        String regNo;
        int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        void addAttendanceUpdate(int newAttendance) {
            attendance = newAttendance;
        }

        // classAverage belongs to the class as a whole,
        // so it is static. isEligible belongs to one student,
        // so it is an instance method.
        static double classAverage(SrmStudent[] students) {
            int total = 0;

            for (SrmStudent student : students) {
                total += student.attendance;
            }

            return (double) total / students.length;
        }
    }

    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301011", 82),
            new SrmStudent("Anitha", "RA231100301012", 68),
            new SrmStudent("Karthik", "RA231100301013", 91),
            new SrmStudent("Meera", "RA231100301014", 74),
            new SrmStudent("Suresh", "RA231100301015", 60)
        };

        for (SrmStudent student : students) {
            System.out.println(
                student.name + " - " +
                student.attendance + "% - " +
                (student.isEligible() ? "Eligible" : "Detained")
            );
        }

        System.out.printf(
            "Class average: %.1f%%%n",
            SrmStudent.classAverage(students)
        );
    }
}