package abstraction_and_interfaces.assignment_problems;

public class Problem5 {

    static class RaceEntry {

        private static int entriesIssued = 0;

        private final String entryCode;
        private double balanceDue;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            if (entryFee <= 0) {
                throw new IllegalArgumentException("Entry fee must be positive");
            }

            entriesIssued++;

            this.entryCode = "ENTRY-" + entriesIssued;
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

        public void pay(double amount, String mode) {
            pay(amount);
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public String getEntryCode() {
            return entryCode;
        }

        public static boolean isValidDiscountCode(String code) {

            if (code == null || code.length() != 5) {
                return false;
            }

            if (code.charAt(0) != 'M') {
                return false;
            }

            if (!Character.isDigit(code.charAt(1))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(2))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(3))) {
                return false;
            }

            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }

            return true;
        }

        public static int getEntriesIssued() {
            return entriesIssued;
        }

        public String announce() {
            return "Race Entry | Code: " + entryCode
                    + " | Balance: " + balanceDue;
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
        public String announce() {
            return "Runner Entry | Code: " + getEntryCode()
                    + " | Category: " + category
                    + " | Balance: " + getBalanceDue();
        }
    }

    static class RelayTeamEntry extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
                String bibNumber,
                double entryFee,
                int teamSize) {

            super(bibNumber, entryFee);

            if (teamSize <= 0) {
                throw new IllegalArgumentException(
                        "Team size must be positive");
            }

            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public String announce() {
            return "Relay Team | Code: " + getEntryCode()
                    + " | Team Size: " + teamSize
                    + " | Balance: " + getBalanceDue();
        }
    }

    static String settleNight(RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relayTeams = 0;
        int individualEntries = 0;

        if (entries == null) {
            return "0 processed | 0 null skipped | "
                    + "0 relay teams | 0 individual entries";
        }

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relayTeams++;
            } else {
                individualEntries++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relayTeams + " relay teams | "
                + individualEntries + " individual entries";
    }

    public static void main(String[] args) {

        RaceEntry entry =
                new RaceEntry("BIB5001", 100);

        System.out.println(entry.getEntryCode());

        System.out.println(
                RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("M12A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("m123A")
        );

        entry.pay(30);
        entry.pay(20, "UPI");

        System.out.println(entry.getBalanceDue());

        RaceEntry[] entries = {
            new RunnerEntry("BIB2001", 80, "Open 10K"),
            null,
            new RelayTeamEntry("BIB4001", 300, 4)
        };

        System.out.println(
                settleNight(entries)
        );
    }
}