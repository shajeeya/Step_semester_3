package abstraction_and_interfaces.assignment_problems;

public class Problem3 {

    static class RaceEntry {
        private String bibNumber;
        private double entryFee;
        private double balanceDue;
        private double[] lateFeeHistory;
        private int historyCount;

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
            this.lateFeeHistory = new double[10];
            this.historyCount = 0;
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

        protected void applyLateFee(double amount) {
            balanceDue += amount;

            if (historyCount < lateFeeHistory.length) {
                lateFeeHistory[historyCount] = amount;
                historyCount++;
            }
        }

        public double[] getLateFeeHistory() {
            double[] history = new double[historyCount];

            for (int i = 0; i < historyCount; i++) {
                history[i] = lateFeeHistory[i];
            }

            return history;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }

        public String getCategory() {
            return category;
        }
    }

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(r.getLateFeeHistory()[0]);
    }
}