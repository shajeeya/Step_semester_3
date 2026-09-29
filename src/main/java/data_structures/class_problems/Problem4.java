package data_structures.class_problems;

public class Problem4 {

    interface LeavePolicy {
        boolean canApplyLeave(int days);
    }

    static abstract class Employee implements LeavePolicy {
        private String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class FullTimeEmployee extends Employee {

        public FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean canApplyLeave(int days) {
            return days > 0 && days <= 20;
        }
    }

    static class PartTimeEmployee extends Employee {

        public PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean canApplyLeave(int days) {
            return days > 0 && days <= 10;
        }
    }

    static class ContractEmployee extends Employee {

        public ContractEmployee(String name) {
            super(name);
        }

        @Override
        public boolean canApplyLeave(int days) {
            return days > 0 && days <= 5;
        }
    }

    enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    static class LeaveRequest {
        private Employee employee;
        private String startDate;
        private String endDate;
        private Status status;

        public LeaveRequest(
                Employee employee,
                String startDate,
                String endDate) {

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = Status.PENDING;
        }

        public Employee getEmployee() {
            return employee;
        }

        public Status getStatus() {
            return status;
        }

        public void approve() {
            if (status == Status.PENDING) {
                status = Status.APPROVED;

                System.out.println(
                    "Leave request for " +
                    employee.getName() +
                    " approved. Status: Approved."
                );
            }
        }

        public void reject() {
            if (status == Status.PENDING) {
                status = Status.REJECTED;

                System.out.println(
                    "Leave request for " +
                    employee.getName() +
                    " rejected. Status: Rejected."
                );
            }
        }

        public void changeToPending() {
            if (status != Status.PENDING) {
                System.out.println(
                    "Cannot change status: " +
                    formatStatus(status) +
                    " request cannot revert to Pending."
                );
            }
        }

        private String formatStatus(Status status) {
            if (status == Status.APPROVED) {
                return "Approved";
            }

            if (status == Status.REJECTED) {
                return "Rejected";
            }

            return "Pending";
        }
    }

    static class LeaveManager {

        public LeaveRequest submitLeave(
                Employee employee,
                String startDate,
                String endDate) {

            LeaveRequest request =
                    new LeaveRequest(
                        employee,
                        startDate,
                        endDate
                    );

            System.out.println(
                "Leave request submitted by " +
                employee.getName() +
                " for " +
                startDate +
                " to " +
                endDate +
                ". Status: Pending."
            );

            return request;
        }

        public void approveRequest(
                LeaveRequest request) {

            request.approve();
        }

        public void rejectRequest(
                LeaveRequest request) {

            request.reject();
        }
    }

    public static void main(String[] args) {

        LeaveManager manager = new LeaveManager();

        Employee john =
                new FullTimeEmployee("John Doe");

        Employee jane =
                new PartTimeEmployee("Jane Smith");

        LeaveRequest johnRequest =
                manager.submitLeave(
                    john,
                    "2024-10-10",
                    "2024-10-12"
                );

        manager.approveRequest(johnRequest);

        LeaveRequest janeRequest =
                manager.submitLeave(
                    jane,
                    "2024-11-01",
                    "2024-11-05"
                );

        // Attempt to move an approved request back to pending
        johnRequest.changeToPending();
    }
}