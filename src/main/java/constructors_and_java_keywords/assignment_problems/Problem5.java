package constructors_and_java_keywords.assignment_problems;

public class Problem5 {

    static class DeliveryAccount {

        private static String systemName;

        static {
            systemName = "Campus Delivery Reconciliation";
        }

        protected final String studentId;
        protected final double orderValue;

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        final double calculateSurgeFee(int delayMinutes) {

            if (delayMinutes < 0) {
                throw new IllegalArgumentException(
                        "Delay minutes cannot be negative."
                );
            }

            if (delayMinutes == 0) {
                return 0.0;
            }

            double surgeFee = 0.0;

            int firstTier = Math.min(delayMinutes, 5);
            surgeFee += orderValue * 0.005 * firstTier;

            if (delayMinutes > 5) {
                int secondTier = Math.min(delayMinutes - 5, 10);
                surgeFee += orderValue * 0.01 * secondTier;
            }

            if (delayMinutes > 15) {
                int thirdTier = delayMinutes - 15;
                surgeFee += orderValue * 0.02 * thirdTier;
            }

            return surgeFee;
        }

        void processAccount(
                DeliveryAccount account,
                double amount,
                int delayMinutes
        ) {
            double surgeFee = calculateSurgeFee(delayMinutes);

            System.out.println(
                    "Student: " + studentId
                    + " | Amount: " + amount
                    + " | Surge Fee: Rs " + surgeFee
            );
        }
    }

    static class Premium extends DeliveryAccount {

        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }

        void processPremiumAccount(
                double amount,
                int delayMinutes
        ) {
            double surgeFee = calculateSurgeFee(delayMinutes);

            System.out.println(
                    "Premium Student: " + studentId
                    + " | Amount: " + amount
                    + " | Surge Fee: Rs " + surgeFee
            );
        }
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray
    ) {

        if (accounts == null
                || amounts == null
                || delayMinutesArray == null) {

            System.out.println("Invalid batch: input arrays cannot be null.");
            return;
        }

        if (accounts.length != amounts.length
                || accounts.length != delayMinutesArray.length) {

            System.out.println(
                    "Invalid batch: array lengths do not match."
            );
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFee = 0.0;

        for (int i = 0; i < accounts.length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            try {

                if (account instanceof Premium) {

                    Premium premiumAccount = (Premium) account;

                    premiumAccount.processPremiumAccount(
                            amounts[i],
                            delayMinutesArray[i]
                    );

                    premiumCount++;

                } else {

                    account.processAccount(
                            account,
                            amounts[i],
                            delayMinutesArray[i]
                    );

                    regularCount++;
                }

                grandTotalSurgeFee +=
                        account.calculateSurgeFee(
                                delayMinutesArray[i]
                        );

                processed++;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Skipped invalid account at index " + i
                        + ": " + e.getMessage()
                );
            }
        }

        System.out.println();
        System.out.println(
                "Processed: " + processed
                + " | Null skipped: " + nullSkipped
                + " | Premium: " + premiumCount
                + " | Regular: " + regularCount
        );

        System.out.println(
                "Grand total surge fees = Rs "
                + grandTotalSurgeFee
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutesArray = {
            10,
            5,
            0
        };

        processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}