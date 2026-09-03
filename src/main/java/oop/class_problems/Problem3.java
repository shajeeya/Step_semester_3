package oop.class_problems;

public class Problem3 {

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(
                    name + " allotted to room " + roomNo
                );
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(
        HostelRoom[] rooms,
        String studentName
    ) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println(
                "No rooms available for " + studentName
            );
        }

        // The array contains references to the existing HostelRoom
        // objects. Passing it does not create copies of those rooms.
    }

    public static void main(String[] args) {

        HostelRoom[] availableRooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println(
            "Rooms: C-214 (2/3), C-507 (2/2)"
        );

        safeAllot(availableRooms, "Divya");

        HostelRoom[] fullRooms = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println(
            "Rooms: C-214 (3/3), C-507 (2/2)"
        );

        safeAllot(fullRooms, "Divya");
    }
}