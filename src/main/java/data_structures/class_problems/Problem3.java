package data_structures.class_problems;

import java.time.LocalDate;
import java.util.ArrayList;

public class Problem3 {

    static abstract class Room {
        private String roomNumber;
        private boolean available;

        public Room(String roomNumber) {
            this.roomNumber = roomNumber;
            this.available = true;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public abstract double calculatePrice(long nights);
    }

    static class StandardRoom extends Room {

        public StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(long nights) {
            return nights * 150.0;
        }
    }

    static class DeluxeRoom extends Room {

        public DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(long nights) {
            return nights * 200.0;
        }
    }

    static class SuiteRoom extends Room {

        public SuiteRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(long nights) {
            return nights * 300.0;
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

    static class Reservation {
        private Room room;
        private Customer customer;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private boolean active;

        public Reservation(
                Room room,
                Customer customer,
                LocalDate checkIn,
                LocalDate checkOut) {

            this.room = room;
            this.customer = customer;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.active = true;
        }

        public Room getRoom() {
            return room;
        }

        public LocalDate getCheckIn() {
            return checkIn;
        }

        public LocalDate getCheckOut() {
            return checkOut;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            active = false;
            room.setAvailable(true);
        }

        public double getTotalPrice() {
            long nights = checkIn.until(checkOut).getDays();
            return room.calculatePrice(nights);
        }
    }

    static class BookingManager {

        private ArrayList<Reservation> reservations =
                new ArrayList<>();

        public Reservation bookRoom(
                Room room,
                Customer customer,
                LocalDate checkIn,
                LocalDate checkOut) {

            if (!isAvailable(room, checkIn, checkOut)) {
                System.out.println(
                    "Booking failed: " +
                    room.getRoomNumber() +
                    " is not available for " +
                    checkIn + " to " + checkOut
                );
                return null;
            }

            if (!checkIn.isBefore(checkOut)) {
                System.out.println(
                    "Invalid booking dates."
                );
                return null;
            }

            Reservation reservation =
                    new Reservation(
                        room,
                        customer,
                        checkIn,
                        checkOut
                    );

            reservations.add(reservation);
            room.setAvailable(false);

            System.out.printf(
                "%s booked from %s to %s. Total price: $%.2f%n",
                room.getRoomNumber(),
                checkIn,
                checkOut,
                reservation.getTotalPrice()
            );

            return reservation;
        }

        private boolean isAvailable(
                Room room,
                LocalDate checkIn,
                LocalDate checkOut) {

            for (Reservation reservation : reservations) {

                if (reservation.getRoom() == room &&
                    reservation.isActive()) {

                    boolean overlaps =
                        checkIn.isBefore(reservation.getCheckOut()) &&
                        checkOut.isAfter(reservation.getCheckIn());

                    if (overlaps) {
                        return false;
                    }
                }
            }

            return true;
        }

        public void cancelReservation(
                Reservation reservation,
                LocalDate cancellationDeadline) {

            if (reservation == null ||
                !reservation.isActive()) {

                return;
            }

            if (LocalDate.now().isBefore(cancellationDeadline)) {
                reservation.cancel();

                System.out.println(
                    "Reservation for " +
                    reservation.getRoom().getRoomNumber() +
                    " cancelled successfully."
                );
            } else {
                System.out.println(
                    "Cancellation deadline has passed."
                );
            }
        }
    }

    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        Customer customer1 =
                new Customer("John Doe");

        Customer customer2 =
                new Customer("Jane Smith");

        Room deluxe =
                new DeluxeRoom("Deluxe Room 101");

        Room standard =
                new StandardRoom("Standard Room 205");

        Reservation reservation1 =
                manager.bookRoom(
                    deluxe,
                    customer1,
                    LocalDate.of(2024, 12, 1),
                    LocalDate.of(2024, 12, 5)
                );

        Reservation reservation2 =
                manager.bookRoom(
                    standard,
                    customer2,
                    LocalDate.of(2024, 12, 3),
                    LocalDate.of(2024, 12, 7)
                );

        manager.bookRoom(
            deluxe,
            customer2,
            LocalDate.of(2024, 12, 3),
            LocalDate.of(2024, 12, 7)
        );

        manager.cancelReservation(
            reservation1,
            LocalDate.of(2026, 12, 31)
        );
    }
}