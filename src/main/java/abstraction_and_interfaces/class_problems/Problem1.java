package abstraction_and_interfaces.class_problems;

public class Problem1 {

    static class EventTicket {
        private String attendeeId;
        private double basePrice;
        private double balanceDue;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid attendee ID");
            }

            if (basePrice <= 0) {
                throw new IllegalArgumentException("Base price must be positive");
            }

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
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

        public void printTicket() {
            System.out.println(
                "Standard Event Ticket | Balance Due: " + balanceDue
            );
        }
    }

    static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        @Override
        public void printTicket() {
            System.out.println(
                "Workshop Ticket | Track: " + track
                + " | Balance Due: " + getBalanceDue()
            );
        }
    }

    static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String attendeeId : attendeeIds) {
            try {
                new EventTicket(attendeeId, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] attendeeIds = {
            "STU1", "ST1", "STU2", " ", "STU3"
        };

        System.out.println(registerBatch(attendeeIds, 500));
    }
}