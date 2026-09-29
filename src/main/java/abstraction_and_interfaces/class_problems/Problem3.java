package abstraction_and_interfaces.class_problems;

public class Problem3 {

    static abstract class StaffMember {
        private double baseSalary;
        protected double bonusRate;

        public StaffMember(double baseSalary) {
            this(baseSalary, 0.10);
        }

        public StaffMember(double baseSalary, double bonusRate) {
            if (baseSalary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative");
            }

            this.baseSalary = baseSalary;
            this.bonusRate = bonusRate;
        }

        public double getSalary() {
            return baseSalary;
        }

        public void setSalary(double baseSalary) {
            if (baseSalary >= 0) {
                this.baseSalary = baseSalary;
            }
        }

        public abstract double calculateBonus();
    }

    interface Auditable {
        String auditRecord();
    }

    static class TeamLead extends StaffMember implements Auditable {
        private int teamSize;

        public TeamLead(double baseSalary, int teamSize) {
            super(baseSalary);
            this.teamSize = teamSize;
        }

        public TeamLead(double baseSalary, double bonusRate, int teamSize) {
            super(baseSalary, bonusRate);
            this.teamSize = teamSize;
        }

        @Override
        public double calculateBonus() {
            return getSalary() * bonusRate;
        }

        @Override
        public String auditRecord() {
            return "TeamLead audit: " + teamSize
                    + " team members, salary $" + getSalary();
        }
    }

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            Auditable auditable = (Auditable) s;
            return auditable.auditRecord();
        }

        return "No audit required";
    }

    public static void main(String[] args) {

        TeamLead t = new TeamLead(60000, 5);

        System.out.println(t.calculateBonus());
        System.out.println(t.auditRecord());

        t.setSalary(-5000);
        System.out.println(t.getSalary());

        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println(t2.calculateBonus());

        StaffMember ref = t; // Upcasting

        System.out.println(getAuditIfApplicable(ref));
    }
}