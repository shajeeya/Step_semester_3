package constructors_and_java_keywords.assignment_problems;

public class Problem1 {

    static class FoodOrder {
        private final String studentName;
        private final String dishName;
        private boolean delivered;

        public FoodOrder(String studentName, String dishName) {

            if (studentName == null || studentName.trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be blank.");
            }

            if (dishName == null || dishName.trim().isEmpty()) {
                throw new IllegalArgumentException("Dish name cannot be blank.");
            }

            this.studentName = studentName.trim();
            this.dishName = dishName.trim();
            this.delivered = false;
        }

        public void markDelivered() {
            if (!delivered) {
                delivered = true;
                System.out.println("Order marked as delivered.");
            } else {
                System.out.println("Order was already delivered.");
            }
        }
    }

    static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {

        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}