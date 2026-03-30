import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 * Demonstrates handling concurrent booking using synchronization.
 *
 * @author Jaswanth
 * @version 11.0
 */
public class BookMyStayApp {

    // -------- Reservation --------
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // -------- Inventory (Shared Resource) --------
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1); // only 1 to show race condition prevention
        }

        // Synchronized method (critical section)
        public synchronized boolean bookRoom(String roomType, String guestName) {

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {
                System.out.println(guestName + " is booking " + roomType);

                // simulate delay (to show concurrency issue if not synchronized)
                try { Thread.sleep(100); } catch (Exception e) {}

                inventory.put(roomType, available - 1);

                System.out.println("Booking SUCCESS for " + guestName);
                return true;
            } else {
                System.out.println("Booking FAILED for " + guestName + " (No rooms left)");
                return false;
            }
        }
    }

    // -------- Booking Processor (Thread) --------
    static class BookingProcessor extends Thread {

        private Reservation reservation;
        private RoomInventory inventory;

        public BookingProcessor(Reservation reservation, RoomInventory inventory) {
            this.reservation = reservation;
            this.inventory = inventory;
        }

        @Override
        public void run() {
            inventory.bookRoom(reservation.roomType, reservation.guestName);
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v11.0 ");
        System.out.println(" Concurrent Booking Simulation ");
        System.out.println("=====================================");

        // Shared inventory
        RoomInventory inventory = new RoomInventory();

        // Simulate multiple guests booking at same time
        Thread t1 = new BookingProcessor(new Reservation("Alice", "Single Room"), inventory);
        Thread t2 = new BookingProcessor(new Reservation("Bob", "Single Room"), inventory);
        Thread t3 = new BookingProcessor(new Reservation("Charlie", "Single Room"), inventory);

        // Start threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();
    }
}