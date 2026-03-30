import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 8: Booking History & Reporting
 * Demonstrates storing confirmed bookings and generating reports.
 *
 * @author Jaswanth
 * @version 8.0
 */
public class BookMyStayApp {

    // -------- Reservation Class --------
    static class Reservation {
        String guestName;
        String roomType;
        String roomId;

        public Reservation(String guestName, String roomType, String roomId) {
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
        }

        public void display() {
            System.out.println("Guest: " + guestName +
                    " | Room Type: " + roomType +
                    " | Room ID: " + roomId);
        }
    }

    // -------- Booking History --------
    static class BookingHistory {

        private List<Reservation> history = new ArrayList<>();

        // Add confirmed booking
        public void addReservation(Reservation reservation) {
            history.add(reservation);
        }

        // Get all reservations
        public List<Reservation> getAllReservations() {
            return history;
        }
    }

    // -------- Report Service --------
    static class BookingReportService {

        // Display all bookings
        public void displayAllBookings(List<Reservation> history) {
            System.out.println("\nBooking History:");
            for (Reservation r : history) {
                r.display();
            }
        }

        // Generate summary report
        public void generateSummary(List<Reservation> history) {
            Map<String, Integer> countMap = new HashMap<>();

            for (Reservation r : history) {
                countMap.put(r.roomType,
                        countMap.getOrDefault(r.roomType, 0) + 1);
            }

            System.out.println("\nBooking Summary Report:");
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue() + " bookings");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v8.0 ");
        System.out.println(" Booking History & Reports ");
        System.out.println("=====================================");

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("Alice", "Single Room", "SR101"));
        history.addReservation(new Reservation("Bob", "Double Room", "DR202"));
        history.addReservation(new Reservation("Charlie", "Suite Room", "SU301"));
        history.addReservation(new Reservation("David", "Single Room", "SR102"));

        // Reporting service
        BookingReportService reportService = new BookingReportService();

        // Display history
        reportService.displayAllBookings(history.getAllReservations());

        // Generate summary
        reportService.generateSummary(history.getAllReservations());
    }
}