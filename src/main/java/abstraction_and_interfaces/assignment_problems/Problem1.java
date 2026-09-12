package abstraction_and_interfaces.assignment_problems;

public class Problem1 {

    static class RaceEntry {
        private String bibNumber;
        private double entryFee;
        private double balanceDue;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            if (entryFee <= 0) {
                throw new IllegalArgumentException("Entry fee must be positive");
            }

            this.bibNumber = bibNumber.trim();
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
        }

        public void pay(double amount) {
            if (amount > 0) {
                balanceDue -= amount;

                if (balanceDue < 0) {
                    balanceDue = 0;
                }
            }
        }

        public double getBalanceDue() {
            return balanceDue;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    static String registerBatch(String[] bibNumbers, double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bibNumber : bibNumbers) {
            try {
                new RaceEntry(bibNumber, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);

        System.out.println(r.getBalanceDue());

        String[] bibNumbers = {
            "BIB1",
            "B1",
            "BIB2"
        };

        System.out.println(
            registerBatch(bibNumbers, 80)
        );
    }
}