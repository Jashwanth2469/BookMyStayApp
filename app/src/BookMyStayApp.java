import java.io.*;
import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 12: Data Persistence & System Recovery
 * Demonstrates serialization and deserialization of system state.
 *
 * @author Jaswanth
 * @version 12.0
 */
public class BookMyStayApp {

    // -------- Reservation --------
    static class Reservation implements Serializable {
        String guestName;
        String roomType;
        String roomId;

        public Reservation(String guestName, String roomType, String roomId) {
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
        }

        public void display() {
            System.out.println(guestName + " | " + roomType + " | " + roomId);
        }
    }

    // -------- System State --------
    static class SystemState implements Serializable {
        Map<String, Integer> inventory;
        List<Reservation> bookings;

        public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
            this.inventory = inventory;
            this.bookings = bookings;
        }
    }

    // -------- Persistence Service --------
    static class PersistenceService {

        private static final String FILE_NAME = "system_state.dat";

        // Save state
        public void save(SystemState state) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                oos.writeObject(state);
                System.out.println("\nState saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving state: " + e.getMessage());
            }
        }

        // Load state
        public SystemState load() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                SystemState state = (SystemState) ois.readObject();
                System.out.println("\nState loaded successfully!");
                return state;
            } catch (Exception e) {
                System.out.println("No previous state found. Starting fresh...");
                return null;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v12.0 ");
        System.out.println(" Data Persistence & Recovery ");
        System.out.println("=====================================");

        PersistenceService persistence = new PersistenceService();

        // Try loading previous state
        SystemState state = persistence.load();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state != null) {
            // Restore state
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\nRecovered Inventory: " + inventory);
            System.out.println("\nRecovered Bookings:");
            for (Reservation r : bookings) {
                r.display();
            }

        } else {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);

            bookings = new ArrayList<>();
            bookings.add(new Reservation("Alice", "Single Room", "SR101"));
            bookings.add(new Reservation("Bob", "Double Room", "DR201"));

            System.out.println("\nNew system initialized.");
        }

        // Save state before exit
        persistence.save(new SystemState(inventory, bookings));
    }
}