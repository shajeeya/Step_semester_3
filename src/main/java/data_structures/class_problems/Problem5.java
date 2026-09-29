package data_structures.class_problems;

import java.util.ArrayList;

public class Problem5 {

    static class FoodItem {
        private String name;
        private double price;

        public FoodItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    static class LineItem {
        private FoodItem foodItem;
        private int quantity;

        public LineItem(FoodItem foodItem, int quantity) {
            this.foodItem = foodItem;
            this.quantity = quantity;
        }

        public double getTotal() {
            return foodItem.getPrice() * quantity;
        }

        public String getName() {
            return foodItem.getName();
        }

        public int getQuantity() {
            return quantity;
        }
    }

    static class Restaurant {
        private String name;

        public Restaurant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
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

        public void notifyCustomer(String message) {
            System.out.println("Notification: " + message);
        }
    }

    interface IPaymentMethod {
        boolean pay(double amount);
        String getName();
    }

    static class CreditCardPayment implements IPaymentMethod {

        @Override
        public boolean pay(double amount) {
            return true;
        }

        @Override
        public String getName() {
            return "Credit Card";
        }
    }

    static class DigitalWalletPayment implements IPaymentMethod {

        @Override
        public boolean pay(double amount) {
            return false;
        }

        @Override
        public String getName() {
            return "Digital Wallet";
        }
    }

    static class CashOnDelivery implements IPaymentMethod {

        @Override
        public boolean pay(double amount) {
            return true;
        }

        @Override
        public String getName() {
            return "Cash on Delivery";
        }
    }

    static class Order {
        private static int orderCounter = 122;

        private int orderId;
        private Customer customer;
        private Restaurant restaurant;
        private ArrayList<LineItem> items;
        private String status;

        public Order(Customer customer, Restaurant restaurant) {
            this.customer = customer;
            this.restaurant = restaurant;
            this.items = new ArrayList<>();
            this.status = "Created";
            this.orderId = ++orderCounter;

            System.out.println("Order created.");
        }

        public void addItem(FoodItem foodItem, int quantity) {

            if (quantity <= 0) {
                return;
            }

            LineItem item = new LineItem(foodItem, quantity);
            items.add(item);

            System.out.println(
                "Added " +
                foodItem.getName() +
                " (Qty " +
                quantity +
                ")"
            );
        }

        private double calculateTotal() {
            double total = 0;

            for (LineItem item : items) {
                total += item.getTotal();
            }

            return total;
        }

        public void placeOrder(IPaymentMethod paymentMethod) {

            if (items.isEmpty()) {
                System.out.println(
                    "Cannot place order: Order must contain at least one item."
                );
                return;
            }

            status = "Pending Payment";

            System.out.println("Order placed successfully.");

            boolean paymentSuccessful =
                    paymentMethod.pay(calculateTotal());

            if (paymentSuccessful) {

                status = "Paid";

                System.out.println(
                    "Payment via " +
                    paymentMethod.getName() +
                    " successful."
                );

                System.out.println(
                    "Order status: " + status
                );

                customer.notifyCustomer(
                    "Order #" +
                    orderId +
                    " placed and paid."
                );

            } else {

                status = "Pending Payment";

                System.out.println(
                    "Payment via " +
                    paymentMethod.getName() +
                    " failed."
                );

                System.out.println(
                    "Order status: " + status
                );

                customer.notifyCustomer(
                    "Order #" +
                    orderId +
                    " placed, awaiting payment."
                );
            }
        }
    }

    public static void main(String[] args) {

        Customer customer =
                new Customer("John Doe");

        Restaurant restaurant =
                new Restaurant("Food Corner");

        FoodItem pizza =
                new FoodItem("Pizza", 200.0);

        FoodItem soda =
                new FoodItem("Soda", 50.0);

        FoodItem burger =
                new FoodItem("Burger", 150.0);

        // First order
        Order order1 =
                new Order(customer, restaurant);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        // Separate empty order to demonstrate failure
        Order emptyOrder =
                new Order(customer, restaurant);

        emptyOrder.placeOrder(
            new CreditCardPayment()
        );

        // Successful payment
        order1.placeOrder(
            new CreditCardPayment()
        );

        // Second order with failed payment
        Order order2 =
                new Order(customer, restaurant);

        order2.addItem(burger, 1);

        order2.placeOrder(
            new DigitalWalletPayment()
        );
    }
}