package abstraction_and_interfaces.assignment_problems;

public class Problem4 {

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

        public String getBibNumber() {
            return bibNumber;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber
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
            return "Runner Entry | Bib: " + getBibNumber()
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
            return "Relay Team | Bib: " + getBibNumber()
                    + " | Team Size: " + teamSize
                    + " | Balance: " + getBalanceDue();
        }
    }

    static String announceAll(RaceEntry[] entries) {

        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {

            report.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;

                report.append(
                        " [Team size via downcast: "
                                + relay.getTeamSize()
                                + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        RunnerEntry runner =
                new RunnerEntry("BIB2001", 120, "Open 10K");

        runner.pay(30);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {
            runner,
            relay
        };

        System.out.println(announceAll(fleet));
    }
}