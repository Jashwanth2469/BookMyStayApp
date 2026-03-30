import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * Demonstrates safe cancellation using Stack (LIFO) and inventory restoration.
 *
 * @author Jaswanth
 * @version 10.0
 */
public class BookMyStayApp {

    // -------- Reservation --------
    static class Reservation {
        String reservationId;
        String roomType;
        String roomId;

        public Reservation(String reservationId, String roomType, String roomId) {
            this.reservationId = reservationId;
            this.roomType = roomType;
            this.roomId = roomId;
        }
    }

    // -------- Inventory --------
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
        }

        public void increaseAvailability(String roomType) {
            inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
        }

        public void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    // -------- Cancellation Service --------
    static class CancellationService {

        private Map<String, Reservation> confirmedBookings = new HashMap<>();
        private Stack<String> rollbackStack = new Stack<>();

        // Add confirmed booking (simulate UC6)
        public void addBooking(Reservation r) {
            confirmedBookings.put(r.reservationId, r);
        }

        // Cancel booking
        public void cancelBooking(String reservationId, RoomInventory inventory) {

            System.out.println("\nProcessing cancellation for: " + reservationId);

            // Validate reservation exists
            if (!confirmedBookings.containsKey(reservationId)) {
                System.out.println("Cancellation failed: Reservation not found!");
                return;
            }

            Reservation r = confirmedBookings.get(reservationId);

            // Push room ID to rollback stack (LIFO)
            rollbackStack.push(r.roomId);

            // Restore inventory
            inventory.increaseAvailability(r.roomType);

            // Remove booking
            confirmedBookings.remove(reservationId);

            System.out.println("Cancellation successful!");
            System.out.println("Released Room ID: " + r.roomId);
        }

        // Display rollback stack
        public void displayRollbackStack() {
            System.out.println("\nRollback Stack (Recent Releases): " + rollbackStack);
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v10.0 ");
        System.out.println(" Cancellation & Rollback ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate confirmed bookings
        service.addBooking(new Reservation("R1", "Single Room", "SR101"));
        service.addBooking(new Reservation("R2", "Double Room", "DR201"));

        // Perform cancellations
        service.cancelBooking("R1", inventory);
        service.cancelBooking("R3", inventory); // invalid case

        // Show inventory after rollback
        inventory.displayInventory();

        // Show rollback stack
        service.displayRollbackStack();
    }
}