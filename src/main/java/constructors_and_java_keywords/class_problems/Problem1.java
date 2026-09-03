package constructors_and_java_keywords.class_problems;

public class Problem1 {

    static class BusTicket {
        private final String passengerName;
        private final String destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {

            if (!isValidName(passengerName)) {
                throw new IllegalArgumentException("Invalid passenger name");
            }

            if (destination == null || destination.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid destination");
            }

            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }

        private static boolean isValidName(String name) {
            if (name == null || name.trim().isEmpty()) {
                return false;
            }

            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);

                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                    return false;
                }
            }

            return true;
        }

        public void markCheckedIn() {
            if (checkedIn) {
                throw new IllegalStateException(
                    "Passenger is already checked in"
                );
            }

            checkedIn = true;
        }

        public static void processBatch(String[][] rawBookings) {

            int valid = 0;
            int rejected = 0;
            int duplicates = 0;

            String[][] accepted = new String[rawBookings.length][2];

            for (String[] booking : rawBookings) {

                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                String name = booking[0];
                String destination = booking[1];

                try {
                    BusTicket ticket =
                        new BusTicket(name, destination);

                    boolean duplicate = false;

                    for (int i = 0; i < valid; i++) {
                        if (accepted[i][0].equals(ticket.passengerName)
                                && accepted[i][1].equals(ticket.destination)) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        duplicates++;
                    } else {
                        accepted[valid][0] = ticket.passengerName;
                        accepted[valid][1] = ticket.destination;
                        valid++;
                    }

                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            System.out.println(
                "Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates
            );
        }
    }

    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}