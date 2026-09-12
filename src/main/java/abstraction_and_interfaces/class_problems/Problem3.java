package abstraction_and_interfaces.class_problems;

public class Problem3 {

    static class EventTicket {
        private double balanceDue;
        private double[] lateFeeHistory;
        private int historyCount;

        public EventTicket(double basePrice) {
            if (basePrice <= 0) {
                throw new IllegalArgumentException("Base price must be positive");
            }

            this.balanceDue = basePrice;
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
            if (amount > 0) {
                balanceDue += amount;

                if (historyCount < lateFeeHistory.length) {
                    lateFeeHistory[historyCount] = amount;
                    historyCount++;
                }
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

    static class WorkshopTicket extends EventTicket {

        public WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        for (double fee : history) {
            System.out.println(fee);
        }

        history[0] = 999;

        double[] actualHistory = w.getLateFeeHistory();

        for (double fee : actualHistory) {
            System.out.println(fee);
        }
    }
}