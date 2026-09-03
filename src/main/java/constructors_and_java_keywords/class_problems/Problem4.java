package constructors_and_java_keywords.class_problems;

public final class Problem4 {

    static final class BoardingPenaltyCalculator {

        private final double minimumPenaltyPercent;

        public BoardingPenaltyCalculator(
            double minimumPenaltyPercent
        ) {
            if (minimumPenaltyPercent < 0) {
                throw new IllegalArgumentException(
                    "Penalty percentage cannot be negative"
                );
            }

            this.minimumPenaltyPercent =
                minimumPenaltyPercent;
        }

        public final double calculatePenalty(
            double ticketFare,
            int minutesLate
        ) {

            if (ticketFare < 0) {
                throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
                );
            }

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
                );
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            int firstTierMinutes =
                Math.min(minutesLate, 5);

            int secondTierMinutes =
                Math.min(
                    Math.max(minutesLate - 5, 0),
                    10
                );

            int thirdTierMinutes =
                Math.max(minutesLate - 15, 0);

            double tieredPenalty =
                ticketFare * 0.005 * firstTierMinutes
                + ticketFare * 0.01 * secondTierMinutes
                + ticketFare * 0.02 * thirdTierMinutes;

            double minimumFloor =
                ticketFare *
                minimumPenaltyPercent / 100.0;

            return Math.max(
                tieredPenalty,
                minimumFloor
            );
        }
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
            new BoardingPenaltyCalculator(1.0);

        System.out.println(
            calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
            calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
            calculator.calculatePenalty(1000, 16)
        );
    }
}