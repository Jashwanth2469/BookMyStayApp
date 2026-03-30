import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 9: Error Handling & Validation
 * Demonstrates input validation, custom exceptions, and fail-fast design.
 *
 * @author Jaswanth
 * @version 9.0
 */
public class BookMyStayApp {

    // -------- Custom Exception --------
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // -------- Inventory --------
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 0);
        }

        public boolean isValidRoomType(String roomType) {
            return inventory.containsKey(roomType);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void decreaseAvailability(String roomType) throws InvalidBookingException {
            int current = getAvailability(roomType);

            if (current <= 0) {
                throw new InvalidBookingException("No availability for " + roomType);
            }

            inventory.put(roomType, current - 1);
        }
    }

    // -------- Validator --------
    static class BookingValidator {

        public static void validate(String roomType, RoomInventory inventory)
                throws InvalidBookingException {

            // Validate room type
            if (!inventory.isValidRoomType(roomType)) {
                throw new InvalidBookingException("Invalid room type: " + roomType);
            }

            // Validate availability
            if (inventory.getAvailability(roomType) <= 0) {
                throw new InvalidBookingException("Room not available: " + roomType);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v9.0 ");
        System.out.println(" Error Handling & Validation ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();

        // Test inputs (one valid, two invalid)
        String[] testRequests = {
                "Single Room",     // valid
                "Suite Room",      // invalid (no availability)
                "Luxury Room"      // invalid (wrong type)
        };

        for (String roomType : testRequests) {

            System.out.println("\nProcessing request for: " + roomType);

            try {
                // Validate first (fail-fast)
                BookingValidator.validate(roomType, inventory);

                // Only if valid → update inventory
                inventory.decreaseAvailability(roomType);

                System.out.println("Booking successful for " + roomType);

            } catch (InvalidBookingException e) {
                // Graceful failure
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        System.out.println("\nSystem continues running safely...");
    }
}