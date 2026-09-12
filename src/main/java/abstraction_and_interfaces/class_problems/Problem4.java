package abstraction_and_interfaces.class_problems;

public class Problem4 {

    static class EventTicket {
        private double balanceDue;

        public EventTicket(double basePrice) {
            this.balanceDue = basePrice;
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public String printTicket() {
            return "Standard | Balance: " + balanceDue;
        }
    }

    static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(double basePrice, String track) {
            super(basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public String printTicket() {
            return "Workshop | Track: " + track
                    + " | Balance: " + getBalanceDue();
        }
    }

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder report = new StringBuilder();

        for (EventTicket ticket : tickets) {

            report.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshopTicket = (WorkshopTicket) ticket;

                report.append(" [Track via downcast: ")
                      .append(workshopTicket.getTrack())
                      .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        EventTicket plain = new EventTicket(500);

        WorkshopTicket workshop =
                new WorkshopTicket(1200, "AI/ML");

        EventTicket[] tickets = {
            plain,
            workshop
        };

        System.out.println(batchPrint(tickets));
    }
}