import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Demonstrates FIFO processing, unique room allocation using Set,
 * and inventory synchronization.
 *
 * @author Jaswanth
 * @version 6.0
 */
public class BookMyStayApp {

    // -------- Reservation Class --------
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // -------- Inventory Service --------
    static class RoomInventory {
        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 2);
            inventory.put("Suite Room", 1);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void decreaseAvailability(String roomType) {
            inventory.put(roomType, getAvailability(roomType) - 1);
        }

        public void displayInventory() {
            System.out.println("\nUpdated Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    // -------- Booking Service --------
    static class BookingService {

        private Queue<Reservation> queue;
        private Set<String> allocatedRoomIds;
        private HashMap<String, Set<String>> roomAllocations;

        public BookingService(Queue<Reservation> queue) {
            this.queue = queue;
            this.allocatedRoomIds = new HashSet<>();
            this.roomAllocations = new HashMap<>();
        }

        // Process bookings
        public void processBookings(RoomInventory inventory) {

            while (!queue.isEmpty()) {

                Reservation request = queue.poll(); // FIFO
                String roomType = request.roomType;

                System.out.println("\nProcessing booking for " + request.guestName);

                // Check availability
                if (inventory.getAvailability(roomType) > 0) {

                    // Generate unique room ID
                    String roomId;
                    do {
                        roomId = roomType.substring(0, 2).toUpperCase() + new Random().nextInt(1000);
                    } while (allocatedRoomIds.contains(roomId));

                    // Store unique ID
                    allocatedRoomIds.add(roomId);

                    // Map room type to allocated IDs
                    roomAllocations.putIfAbsent(roomType, new HashSet<>());
                    roomAllocations.get(roomType).add(roomId);

                    // Update inventory (atomic operation)
                    inventory.decreaseAvailability(roomType);

                    System.out.println("Booking Confirmed!");
                    System.out.println("Guest: " + request.guestName);
                    System.out.println("Room Type: " + roomType);
                    System.out.println("Room ID: " + roomId);

                } else {
                    System.out.println("Booking Failed! No availability for " + roomType);
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v6.0 ");
        System.out.println(" Room Allocation ");
        System.out.println("=====================================");

        // Initialize queue (FIFO)
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Double Room"));
        queue.add(new Reservation("Charlie", "Suite Room"));
        queue.add(new Reservation("David", "Suite Room")); // should fail

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Booking service
        BookingService service = new BookingService(queue);

        // Process bookings
        service.processBookings(inventory);

        // Show updated inventory
        inventory.displayInventory();
    }
}