import java.util.HashMap;
import java.util.Map;

/**
 * BookMyStayApp
 *
 * Use Case 4: Room Search & Availability Check
 * Demonstrates read-only access to inventory and filtering available rooms.
 *
 * @author Jaswanth
 * @version 4.0
 */
public class BookMyStayApp {

    // -------- Abstract Room Class --------
    static abstract class Room {
        String type;
        int beds;
        double price;

        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Room Type: " + type);
            System.out.println("Beds: " + beds);
            System.out.println("Price: ₹" + price);
        }
    }

    // -------- Concrete Room Types --------
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1500);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2500);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // -------- Inventory Class --------
    static class RoomInventory {
        private HashMap<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 0); // unavailable
            inventory.put("Suite Room", 2);
        }

        // Read-only method
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public Map<String, Integer> getAllAvailability() {
            return inventory;
        }
    }

    // -------- Search Service --------
    static class SearchService {

        public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {

            System.out.println("Available Rooms:\n");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.type);

                // Show only available rooms
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                    System.out.println("---------------------------");
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v4.0 ");
        System.out.println(" Room Search ");
        System.out.println("=====================================");

        // Initialize inventory (state holder)
        RoomInventory inventory = new RoomInventory();

        // Room domain objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Search service (read-only)
        SearchService searchService = new SearchService();

        // Perform search
        searchService.searchAvailableRooms(inventory, rooms);
    }
}