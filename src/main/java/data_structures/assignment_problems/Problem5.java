package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.List;

public class Problem5 {

    interface PricingPlan {
        double getDiscountedPrice(double price);

        String getPlanName();
    }

    static class DayScholarPlan implements PricingPlan {

        @Override
        public double getDiscountedPrice(double price) {
            return price;
        }

        @Override
        public String getPlanName() {
            return "Day Scholar";
        }
    }

    static class HostellerPlan implements PricingPlan {

        @Override
        public double getDiscountedPrice(double price) {
            return price * 0.90;
        }

        @Override
        public String getPlanName() {
            return "Hosteller";
        }
    }

    static class StaffPlan implements PricingPlan {

        @Override
        public double getDiscountedPrice(double price) {
            return price * 0.80;
        }

        @Override
        public String getPlanName() {
            return "Staff";
        }
    }

    static class Transaction {

        private String description;
        private double amount;

        public Transaction(String description, double amount) {
            this.description = description;
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return description + ": " +
                    (amount >= 0 ? "+" : "") +
                    String.format("%.2f", amount);
        }
    }

    static class SmartCard {

        private String cardId;
        private PricingPlan pricingPlan;
        private double balance;
        private boolean blocked;

        private List<Transaction> transactions;

        public SmartCard(
                String cardId,
                PricingPlan pricingPlan) {

            this.cardId = cardId;
            this.pricingPlan = pricingPlan;
            this.balance = 0;
            this.blocked = false;
            this.transactions = new ArrayList<>();
        }

        public void block() {
            blocked = true;
            System.out.println("Card " + cardId + " is blocked.");
        }

        public void unblock() {
            blocked = false;
            System.out.println("Card " + cardId + " is unblocked.");
        }

        public void topUp(double amount) {

            if (blocked) {
                System.out.println(
                        "Top-up failed: card is blocked."
                );
                return;
            }

            if (amount < 100) {
                System.out.println(
                        "Top-up failed: minimum top-up is ₹100."
                );
                return;
            }

            if (balance + amount > 5000) {
                System.out.println(
                        "Top-up failed: maximum balance is ₹5000."
                );
                return;
            }

            balance += amount;

            transactions.add(
                    new Transaction("Top-up", amount)
            );

            System.out.printf(
                    "Top-up successful: +₹%.2f, Balance: ₹%.2f%n",
                    amount,
                    balance
            );
        }

        public void purchase(
                String item,
                double originalPrice) {

            if (blocked) {
                System.out.println(
                        "Purchase failed: card is blocked."
                );
                return;
            }

            double chargedPrice =
                    pricingPlan.getDiscountedPrice(originalPrice);

            if (balance < chargedPrice) {
                System.out.printf(
                        "Purchase failed: %s requires ₹%.2f, " +
                        "but balance is only ₹%.2f.%n",
                        item,
                        chargedPrice,
                        balance
                );
                return;
            }

            balance -= chargedPrice;

            transactions.add(
                    new Transaction(
                            item,
                            -chargedPrice
                    )
            );

            System.out.printf(
                    "Purchase successful: %s -₹%.2f, Balance: ₹%.2f%n",
                    item,
                    chargedPrice,
                    balance
            );
        }

        public void refund(
                String item,
                double amount) {

            if (blocked) {
                System.out.println(
                        "Refund failed: card is blocked."
                );
                return;
            }

            for (Transaction transaction : transactions) {

                if (transaction.toString()
                        .startsWith("Refund for " + item)) {

                    System.out.println(
                            "Refund failed: refund already processed."
                    );
                    return;
                }
            }

            if (amount <= 0) {
                System.out.println(
                        "Refund failed: invalid refund amount."
                );
                return;
            }

            if (balance + amount > 5000) {
                System.out.println(
                        "Refund failed: maximum balance is ₹5000."
                );
                return;
            }

            balance += amount;

            transactions.add(
                    new Transaction(
                            "Refund for " + item,
                            amount
                    )
            );

            System.out.printf(
                    "Refund successful: +₹%.2f, Balance: ₹%.2f%n",
                    amount,
                    balance
            );
        }

        public void printMiniStatement() {

            System.out.println("\nMini-Statement for " + cardId);
            System.out.println(
                    "Plan: " + pricingPlan.getPlanName()
            );

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

            System.out.printf(
                    "Balance: ₹%.2f%n",
                    balance
            );
        }
    }

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard(
                        "C-2045",
                        new HostellerPlan()
                );

        card.topUp(500);

        card.purchase(
                "Veg Thali",
                120
        );

        card.purchase(
                "Coffee",
                60
        );

        card.purchase(
                "Large Meal",
                400
        );

        card.refund(
                "Veg Thali",
                108
        );

        card.refund(
                "Veg Thali",
                108
        );

        card.printMiniStatement();
    }
}