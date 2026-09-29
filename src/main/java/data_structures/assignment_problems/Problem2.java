package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {

    enum ParcelStatus {
        BOOKED,
        PICKED_UP,
        IN_TRANSIT,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    interface ShippingType {
        double calculateCharge(double weight);

        String getName();
    }

    static class StandardShipping implements ShippingType {

        @Override
        public double calculateCharge(double weight) {
            return 40 + (10 * weight);
        }

        @Override
        public String getName() {
            return "Standard";
        }
    }

    static class ExpressShipping implements ShippingType {

        @Override
        public double calculateCharge(double weight) {
            return 80 + (15 * weight);
        }

        @Override
        public String getName() {
            return "Express";
        }
    }

    static class FragileShipping implements ShippingType {

        private StandardShipping standardShipping =
                new StandardShipping();

        @Override
        public double calculateCharge(double weight) {
            return standardShipping.calculateCharge(weight) + 50;
        }

        @Override
        public String getName() {
            return "Fragile";
        }
    }

    interface NotificationChannel {
        void notify(String parcelId, ParcelStatus status);
    }

    static class SmsChannel implements NotificationChannel {

        @Override
        public void notify(String parcelId, ParcelStatus status) {
            System.out.println(
                    "[SMS] " + parcelId +
                    " is now " + status + "."
            );
        }
    }

    static class EmailChannel implements NotificationChannel {

        @Override
        public void notify(String parcelId, ParcelStatus status) {
            System.out.println(
                    "[Email] " + parcelId +
                    " is now " + status + "."
            );
        }
    }

    static class Customer {

        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Parcel {

        private String parcelId;
        private double weight;
        private ShippingType shippingType;
        private ParcelStatus status;

        private List<NotificationChannel> channels;

        public Parcel(String parcelId,
                      double weight,
                      ShippingType shippingType) {

            if (parcelId == null || parcelId.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Parcel ID cannot be empty."
                );
            }

            if (weight <= 0) {
                throw new IllegalArgumentException(
                        "Weight must be positive."
                );
            }

            this.parcelId = parcelId;
            this.weight = weight;
            this.shippingType = shippingType;
            this.status = ParcelStatus.BOOKED;
            this.channels = new ArrayList<>();
        }

        public void subscribe(NotificationChannel channel) {
            if (channel != null) {
                channels.add(channel);
            }
        }

        public double getCharge() {
            return shippingType.calculateCharge(weight);
        }

        public ParcelStatus getStatus() {
            return status;
        }

        public String getParcelId() {
            return parcelId;
        }

        public void notifyCurrentStatus() {
            notifyChannels();
        }

        public void changeStatus(ParcelStatus newStatus) {

            if (!isValidTransition(status, newStatus)) {
                System.out.println(
                        "Invalid transition: " +
                        status + " → " +
                        newStatus +
                        " is not allowed."
                );
                return;
            }

            status = newStatus;
            notifyChannels();
        }

        private boolean isValidTransition(
                ParcelStatus current,
                ParcelStatus next) {

            if (current == ParcelStatus.BOOKED
                    && next == ParcelStatus.PICKED_UP) {
                return true;
            }

            if (current == ParcelStatus.PICKED_UP
                    && next == ParcelStatus.IN_TRANSIT) {
                return true;
            }

            if (current == ParcelStatus.IN_TRANSIT
                    && next == ParcelStatus.OUT_FOR_DELIVERY) {
                return true;
            }

            if (current == ParcelStatus.OUT_FOR_DELIVERY
                    && next == ParcelStatus.DELIVERED) {
                return true;
            }

            return false;
        }

        private void notifyChannels() {

            for (NotificationChannel channel : channels) {
                channel.notify(parcelId, status);
            }
        }

        public void cancel() {

            if (status != ParcelStatus.BOOKED) {
                System.out.println(
                        "Cancellation failed: " +
                        parcelId +
                        " can be cancelled only while BOOKED."
                );
                return;
            }

            System.out.println(
                    "Parcel " + parcelId + " cancelled."
            );
        }
    }

    static class ParcelService {

        public Parcel bookParcel(
                Customer customer,
                String parcelId,
                double weight,
                ShippingType shippingType) {

            Parcel parcel = new Parcel(
                    parcelId,
                    weight,
                    shippingType
            );

            System.out.printf(
                    "Parcel %s booked (%s, %.0f kg).%n",
                    parcelId,
                    shippingType.getName(),
                    weight
            );

            System.out.printf(
                    "Charge: ₹%.2f%n",
                    parcel.getCharge()
            );

            return parcel;
        }
    }

    public static void main(String[] args) {

        Customer customer =
                new Customer("Student");

        ParcelService service =
                new ParcelService();

        ShippingType express =
                new ExpressShipping();

        Parcel parcel =
                service.bookParcel(
                        customer,
                        "P101",
                        2,
                        express
                );

        // Subscribe to SMS and Email
        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        // Notify subscribers about initial BOOKED status
        parcel.notifyCurrentStatus();

        // BOOKED → PICKED_UP
        parcel.changeStatus(
                ParcelStatus.PICKED_UP
        );

        // Cancellation attempt after pickup
        parcel.cancel();

        // PICKED_UP → IN_TRANSIT
        parcel.changeStatus(
                ParcelStatus.IN_TRANSIT
        );

        // Invalid: IN_TRANSIT → DELIVERED
        parcel.changeStatus(
                ParcelStatus.DELIVERED
        );
    }
}