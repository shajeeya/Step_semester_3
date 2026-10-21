
package sorting_algorithms.class_problems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Problem2 {

    static class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Customer)) {
                return false;
            }

            Customer other = (Customer) obj;

            return id == other.id
                    && Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {
        Set<Customer> customers = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(customers.add(c1));
        System.out.println(customers.add(c2)
                ? "true" : "false (duplicate rejected)");

        customers.add(c3);

        System.out.println("unique count " + customers.size());
        System.out.println("contains: " + customers.contains(
                new Customer(101, "Asha")));
    }
}
