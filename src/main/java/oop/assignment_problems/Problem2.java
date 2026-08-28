package oop.assignment_problems;

class Employee {

    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class ManagerEmployee extends Employee {

    String department;

    ManagerEmployee(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
    }
}

class InternEmployee extends Employee {

    int durationMonths;

    InternEmployee(String name, double salary, int durationMonths) {
        super(name, salary);
        this.durationMonths = durationMonths;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Internship Duration: " + durationMonths + " months");
    }
}

public class Problem2 {

    public static void main(String[] args) {

        Employee employee = new Employee("Arun", 40000);

        ManagerEmployee manager =
            new ManagerEmployee("Priya", 75000, "AI & ML");

        InternEmployee intern =
            new InternEmployee("Rahul", 20000, 6);

        System.out.println("Employee Details:");
        employee.displayDetails();

        System.out.println("\nManager Details:");
        manager.displayDetails();

        System.out.println("\nIntern Details:");
        intern.displayDetails();

        System.out.println("\nType Checks:");

        System.out.println(
            "Manager is Employee: " +
            (manager instanceof Employee)
        );

        System.out.println(
            "Intern is Employee: " +
            (intern instanceof Employee)
        );

        System.out.println(
            "Employee is Manager: " +
            (employee instanceof ManagerEmployee)
        );
    }
}