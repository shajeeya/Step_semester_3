package oop.assignment_problems;

class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay = employee.salary;

        if (employee instanceof ManagerEmployee) {
            // Manager's salary is already stored in the Employee object
            pay = employee.salary;
        }

        String slotDetails;

        if (slot == null) {
            slotDetails = "no parking assigned";
        } else {
            slotDetails = "slot " + slot.slotNumber;
        }

        return name + " | Pay: Rs " + pay +
               " | " + slotDetails;
    }
}

public class Problem5 {

    public static void main(String[] args) {

        // Employee objects
        ManagerEmployee manager =
            new ManagerEmployee("Divya", 78000, "AI & ML");

        Employee employee =
            new Employee("Karan", 40000);

        InternEmployee intern =
            new InternEmployee("Meera", 10000, 6);


        // Parking slots
        ParkingSlot slotA1 =
            new ParkingSlot(1, "TN01AB1234");

        ParkingSlot slotA2 =
            new ParkingSlot(2, "TN02CD5678");


        // Parking is allotted only to two employees
        CompanyEmployeeRecord record1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                manager,
                slotA1
            );

        CompanyEmployeeRecord record2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                employee,
                slotA2
            );

        // Meera intentionally has no parking slot
        CompanyEmployeeRecord record3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                intern,
                null
            );


        // Display all records
        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        // Display total records
        System.out.println(
            "Total records: " +
            CompanyEmployeeRecord.totalRecords
        );
    }
}