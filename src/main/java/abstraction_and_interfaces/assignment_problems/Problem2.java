package abstraction_and_interfaces.assignment_problems;

public class Problem2 {

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

        public void printEntry() {
            System.out.println(
                "Race Entry | Balance Due: " + balanceDue
            );
        }
    }

    static class WorkshopEntry extends RaceEntry {
        private String track;

        public WorkshopEntry(
                String bibNumber,
                double entryFee,
                String track) {

            super(bibNumber, entryFee);
            this.track = track;
        }

        protected String getTrack() {
            return track;
        }

        @Override
        public void printEntry() {
            System.out.println(
                "Workshop Entry | Track: " + track
                + " | Balance Due: " + getBalanceDue()
            );
        }
    }

    static class PremiumWorkshopEntry extends WorkshopEntry {
        private double kitFee;

        public PremiumWorkshopEntry(
                String bibNumber,
                double entryFee,
                String track,
                double kitFee) {

            super(bibNumber, entryFee, track);
            this.kitFee = kitFee;
        }

        @Override
        public void printEntry() {
            System.out.println(
                "Premium Workshop Entry | Track: " + getTrack()
                + " | Kit Fee: " + kitFee
                + " | Balance Due: " + getBalanceDue()
            );
        }
    }

    static class HackathonEntry extends RaceEntry {
        private String teamName;

        public HackathonEntry(
                String bibNumber,
                double entryFee,
                String teamName) {

            super(bibNumber, entryFee);
            this.teamName = teamName;
        }

        @Override
        public void printEntry() {
            System.out.println(
                "Hackathon Entry | Team: " + teamName
                + " | Balance Due: " + getBalanceDue()
            );
        }
    }

    static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof PremiumWorkshopEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof HackathonEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof WorkshopEntry) {
            return "Second generation";
        }

        return "Standard Race Entry";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries) {
            if (entry != null) {
                total += entry.getBalanceDue();
            }
        }

        return total;
    }

    public static void main(String[] args) {

        RaceEntry standard =
                new RaceEntry("BIB1001", 500);

        WorkshopEntry workshop =
                new WorkshopEntry(
                    "BIB1002", 1200, "AI/ML"
                );

        PremiumWorkshopEntry premium =
                new PremiumWorkshopEntry(
                    "BIB1003", 2000, "Cloud Native", 300
                );

        HackathonEntry hackathon =
                new HackathonEntry(
                    "BIB1004", 800, "Byte Force"
                );

        standard.printEntry();
        workshop.printEntry();
        premium.printEntry();
        hackathon.printEntry();

        System.out.println(
            classifyGeneration(premium)
        );

        System.out.println(
            classifyGeneration(hackathon)
        );

        RaceEntry[] entries = {
            standard,
            workshop,
            premium,
            hackathon
        };

        System.out.println(
            getTotalBalanceDue(entries)
        );
    }
}