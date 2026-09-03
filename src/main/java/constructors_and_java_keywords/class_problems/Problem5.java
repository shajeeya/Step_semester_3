package constructors_and_java_keywords.class_problems;

public class Problem5 {

    static class BusTicketAccount {

        protected final String bookingId;
        protected final double ticketFare;
        protected double amountPaid;

        static int totalAccountsCreated;

        static {
            totalAccountsCreated = 0;
        }

        public BusTicketAccount(
            String bookingId,
            double ticketFare
        ) {
            if (bookingId == null ||
                bookingId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                    "Invalid booking ID"
                );
            }

            if (ticketFare < 0) {
                throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
                );
            }

            this.bookingId = bookingId.trim();
            this.ticketFare = ticketFare;

            totalAccountsCreated++;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public void pay(double amount) {

            if (amount <= 0) {
                throw new IllegalArgumentException(
                    "Payment must be positive"
                );
            }

            amountPaid += amount;
        }

        public final double calculatePenalty(
            int minutesLate
        ) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
                );
            }

            return ticketFare * 0.01 * minutesLate;
        }

        public double getDue() {
            return Math.max(
                0,
                ticketFare - amountPaid
            );
        }

        public void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate
        ) {

            if (account == null) {
                return;
            }

            if (amount > 0) {
                account.pay(amount);
            }

            double penalty =
                account.calculatePenalty(minutesLate);

            System.out.printf(
                "%s | Penalty: Rs %.2f%n",
                account.bookingId,
                penalty
            );
        }

        public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray
        ) {

            if (accounts == null ||
                amounts == null ||
                minutesLateArray == null) {

                throw new IllegalArgumentException(
                    "Batch arrays cannot be null"
                );
            }

            int length = Math.min(
                accounts.length,
                Math.min(
                    amounts.length,
                    minutesLateArray.length
                )
            );

            int processed = 0;
            int nullSkipped = 0;
            int sleeperCount = 0;
            int regularCount = 0;

            double grandTotalPenalties = 0.0;

            BusTicketAccount processor =
                new BusTicketAccount(
                    "PROCESSOR",
                    0
                );

            for (int i = 0; i < length; i++) {

                BusTicketAccount account =
                    accounts[i];

                if (account == null) {
                    nullSkipped++;
                    continue;
                }

                if (account instanceof SleeperAccount) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }

                processor.processAccount(
                    account,
                    amounts[i],
                    minutesLateArray[i]
                );

                grandTotalPenalties +=
                    account.calculatePenalty(
                        minutesLateArray[i]
                    );

                processed++;
            }

            System.out.println(
                processed +
                " processed | " +
                nullSkipped +
                " null skipped | " +
                sleeperCount +
                " sleeper | " +
                regularCount +
                " regular"
            );

            System.out.printf(
                "Grand total penalties = Rs %.2f%n",
                grandTotalPenalties
            );
        }
    }

    static class SleeperAccount
        extends BusTicketAccount {

        public SleeperAccount(
            String bookingId,
            double ticketFare
        ) {
            super(bookingId, ticketFare);
        }

        public final double calculateSleeperPenalty(int minutesLate) {
            if (minutesLate < 0) {
                throw new IllegalArgumentException("Minutes late cannot be negative");
            }
        
            return ticketFare * 0.005 * minutesLate;
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200,
            900,
            700
        };

        int[] minutesLateArray = {
            10,
            5,
            0
        };

        BusTicketAccount.processBatch(
            accounts,
            amounts,
            minutesLateArray
        );
    }
}