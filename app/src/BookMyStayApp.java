import java.util.HashMap;
import java.util.Map;

/**
 * BookMyStayApp
 *
 * Use Case 3: Centralized Room Inventory Management
 * Demonstrates use of HashMap for managing room availability.
 *
 * @author Jaswanth
 * @version 3.1
 */
public class BookMyStayApp {

    // Inventory class (Encapsulation of inventory logic)
    static class RoomInventory {

        private HashMap<String, Integer> inventory;

        // Constructor to initialize inventory
        public RoomInventory() {
            inventory = new HashMap<>();

            // Initialize room availability
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        // Method to get availability
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Method to update availability
        public void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }

        // Display full inventory
        public void displayInventory() {
            System.out.println("Current Room Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v3.1 ");
        System.out.println(" Centralized Inventory ");
        System.out.println("=====================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability for Double Room:");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        System.out.println("\nUpdating availability for Double Room...");
        inventory.updateAvailability("Double Room", 2);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}