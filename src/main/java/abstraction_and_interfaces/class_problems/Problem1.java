package abstraction_and_interfaces.class_problems;

public class Problem1 {

    static abstract class Payment {
        private static int transactionCounter = 1000;
        private final int transactionId;
        protected double amount;

        public Payment(double amount) {
            this.amount = amount;
            transactionId = ++transactionCounter;
        }

        public final int getTransactionId() {
            return transactionId;
        }

        public abstract void processPayment();

        public void processPayment(double amount) {
            this.amount = amount;
            processPayment();
        }

        public String getPaymentInfo() {
            return "Transaction ID: " + transactionId
                    + " | Amount: " + amount;
        }
    }

    static class CreditCardPayment extends Payment {

        public CreditCardPayment(double amount) {
            super(amount);
        }

        @Override
        public void processPayment() {
            System.out.println("Credit Card Payment Processed | "
                    + getPaymentInfo());
        }
    }

    static class UpiPayment extends Payment {

        public UpiPayment(double amount) {
            super(amount);
        }

        @Override
        public void processPayment() {
            System.out.println("UPI Payment Processed | "
                    + getPaymentInfo());
        }
    }

    public static void main(String[] args) {

        Payment payment1 = new CreditCardPayment(1500);
        Payment payment2 = new UpiPayment(800);

        payment1.processPayment();
        payment2.processPayment();

        payment1.processPayment(2000);

        System.out.println(payment1.getPaymentInfo());
        System.out.println(payment2.getPaymentInfo());
    }
}