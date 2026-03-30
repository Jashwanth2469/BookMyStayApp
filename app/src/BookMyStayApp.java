/**
 * BookMyStayApp
 *
 * Use Case 2: Basic Room Types & Static Availability
 * Demonstrates abstraction, inheritance, polymorphism, and static availability.
 *
 * @author Jaswanth
 * @version 2.1
 */
public class BookMyStayApp {

    // Abstract Room class
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
            System.out.println("Price per night: ₹" + price);
        }
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1500);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2500);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v2.1 ");
        System.out.println(" Room Availability ");
        System.out.println("=====================================");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        single.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("-------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("-------------------------------------");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}