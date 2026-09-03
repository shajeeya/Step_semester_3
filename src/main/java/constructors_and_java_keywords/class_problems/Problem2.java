package constructors_and_java_keywords.class_problems;

public class Problem2 {

    static class FareSplitter {

        private final String tripId;
        private final double totalFare;
        private final int passengerCount;

        public FareSplitter(
            String tripId,
            double totalFare,
            int passengerCount
        ) {
            if (totalFare < 0) {
                throw new IllegalArgumentException(
                    "Fare cannot be negative"
                );
            }

            if (passengerCount <= 0) {
                throw new IllegalArgumentException(
                    "Passenger count must be positive"
                );
            }

            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 2);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 2);
        }

        public double[] fareBreakdown() {

            double[] shares = new double[passengerCount];

            if (totalFare == 0) {
                return shares;
            }

            long totalCents =
                Math.round(totalFare * 100);

            long baseCents =
                totalCents / passengerCount;

            long remainder =
                totalCents % passengerCount;

            for (int i = 0; i < passengerCount; i++) {
                shares[i] = baseCents / 100.0;
            }

            // Remaining cents go to the last share.
            shares[passengerCount - 1] +=
                remainder / 100.0;

            return shares;
        }

        public boolean isConfirmationOverdue(
            int confirmed,
            int expected
        ) {
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {

        FareSplitter splitter =
            new FareSplitter("TRIP001", 100000, 3);

        double[] breakdown =
            splitter.fareBreakdown();

        for (double share : breakdown) {
            System.out.printf("%.2f ", share);
        }

        System.out.println();

        FareSplitter provisional =
            new FareSplitter("TRIP003");

        double[] provisionalBreakdown =
            provisional.fareBreakdown();

        for (double share : provisionalBreakdown) {
            System.out.printf("%.1f ", share);
        }

        System.out.println();
    }
}