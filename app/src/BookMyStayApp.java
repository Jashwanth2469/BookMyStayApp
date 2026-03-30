import java.util.LinkedList;
import java.util.Queue;

/**
 * BookMyStayApp
 *
 * Use Case 5: Booking Request Queue (FIFO)
 * Demonstrates fair handling of booking requests using Queue.
 *
 * @author Jaswanth
 * @version 5.0
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

        public void display() {
            System.out.println("Guest: " + guestName + " | Room: " + roomType);
        }
    }

    // -------- Booking Queue --------
    static class BookingQueue {
        private Queue<Reservation> queue;

        public BookingQueue() {
            queue = new LinkedList<>();
        }

        // Add request (enqueue)
        public void addRequest(Reservation reservation) {
            queue.add(reservation);
            System.out.println("Request added:");
            reservation.display();
        }

        // Display all requests (no processing)
        public void showAllRequests() {
            System.out.println("\nBooking Requests in Queue (FIFO Order):");
            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v5.0 ");
        System.out.println(" Booking Request Queue ");
        System.out.println("=====================================");

        // Initialize booking queue
        BookingQueue bookingQueue = new BookingQueue();

        // Simulate booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.showAllRequests();
    }
}