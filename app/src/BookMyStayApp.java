import java.util.*;

/**
 * BookMyStayApp
 *
 * Use Case 7: Add-On Service Selection
 * Demonstrates mapping of reservation IDs to multiple services
 * using Map and List (One-to-Many relationship).
 *
 * @author Jaswanth
 * @version 7.0
 */
public class BookMyStayApp {

    // -------- Add-On Service --------
    static class AddOnService {
        String name;
        double cost;

        public AddOnService(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        public void display() {
            System.out.println(name + " - ₹" + cost);
        }
    }

    // -------- Add-On Service Manager --------
    static class AddOnServiceManager {

        // Map: Reservation ID -> List of Services
        private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

        // Add service to a reservation
        public void addService(String reservationId, AddOnService service) {
            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);

            System.out.println("Service added to Reservation " + reservationId + ": " + service.name);
        }

        // Display services for a reservation
        public void displayServices(String reservationId) {
            List<AddOnService> services = serviceMap.get(reservationId);

            if (services == null || services.isEmpty()) {
                System.out.println("No add-on services for Reservation " + reservationId);
                return;
            }

            System.out.println("\nServices for Reservation " + reservationId + ":");
            for (AddOnService s : services) {
                s.display();
            }
        }

        // Calculate total cost
        public double calculateTotalCost(String reservationId) {
            List<AddOnService> services = serviceMap.get(reservationId);
            double total = 0;

            if (services != null) {
                for (AddOnService s : services) {
                    total += s.cost;
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - v7.0 ");
        System.out.println(" Add-On Services ");
        System.out.println("=====================================");

        // Example reservation ID (from UC6)
        String reservationId = "SR101";

        // Create service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 800));
        manager.addService(reservationId, new AddOnService("Extra Bed", 300));

        // Display services
        manager.displayServices(reservationId);

        // Calculate total cost
        double total = manager.calculateTotalCost(reservationId);
        System.out.println("\nTotal Add-On Cost: ₹" + total);
    }
}