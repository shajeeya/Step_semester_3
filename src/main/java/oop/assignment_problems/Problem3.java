package oop.assignment_problems;

class ParkingSlot {

    int slotNumber;
    String vehicleNumber;

    ParkingSlot(int slotNumber, String vehicleNumber) {
        this.slotNumber = slotNumber;
        this.vehicleNumber = vehicleNumber;
    }
}

public class Problem3 {

    static int findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] == null) {
                return i;
            }
        }

        return -1;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNumber) {

        int index = findAvailableSlot(slots);

        if (index == -1) {
            System.out.println("No parking slot available.");
        } else {
            slots[index] = new ParkingSlot(index + 1, vehicleNumber);

            System.out.println(
                "Vehicle " + vehicleNumber +
                " allotted to slot " + (index + 1)
            );
        }
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = new ParkingSlot[5];

        slots[0] = new ParkingSlot(1, "TN01AB1234");
        slots[1] = new ParkingSlot(2, "TN02CD5678");

        safeAllot(slots, "TN03EF9012");
        safeAllot(slots, "TN04GH3456");
        safeAllot(slots, "TN05IJ7890");
        safeAllot(slots, "TN06KL1122");
    }
}