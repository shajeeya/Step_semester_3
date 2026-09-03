package oop.class_problems;

public class Problem5 {

    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println(
                    "Rejected: payment must be positive"
                );
                return;
            }

            amountPaid += amount;
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds) {
            this.roomNo = roomNo;
            this.beds = beds;
        }

        boolean allot(String studentName) {
            if (occupied < beds) {
                occupied++;

                System.out.println(
                    studentName +
                    " allotted to room " +
                    roomNo
                );

                return true;
            }

            return false;
        }
    }

    static class SrmStudent {
        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(
            String name,
            String regNo,
            HostelFeeAccount feeAccount
        ) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;

            totalStudents++;
        }

        String fullStatus() {

            String roomNumber;

            if (room == null) {
                roomNumber = "unallotted";
            } else {
                roomNumber = room.roomNo;
            }

            return name +
                " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " +
                roomNumber;
        }
    }

    public static void main(String[] args) {

        HostelRoom room214 =
            new HostelRoom("C-214", 1);

        HostelRoom room507 =
            new HostelRoom("C-507", 1);

        SrmStudent ravi =
            new SrmStudent(
                "Ravi",
                "RA231100301011",
                new HostelFeeAccount(
                    "RA231100301011",
                    200000
                )
            );

        SrmStudent anitha =
            new SrmStudent(
                "Anitha",
                "RA231100301012",
                new HostelFeeAccount(
                    "RA231100301012",
                    200000
                )
            );

        SrmStudent karthik =
            new SrmStudent(
                "Karthik",
                "RA231100301013",
                new HostelFeeAccount(
                    "RA231100301013",
                    200000
                )
            );

        ravi.room = room214;
        room214.allot(ravi.name);

        anitha.room = room507;
        room507.allot(anitha.name);

        // Karthik intentionally remains unallotted.

        ravi.feeAccount.pay(60000);
        anitha.feeAccount.pay(20000);

        // Rejected payment.
        karthik.feeAccount.pay(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " +
            SrmStudent.totalStudents
        );
    }
}