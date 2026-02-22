import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        Menu menu = new Menu();
        FileStore store = new FileStore();
        Invoice invoice = new Invoice(menu, store);
        CafeteriaSystem sys = new CafeteriaSystem(menu, invoice);
        menu.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        menu.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        menu.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout("student", order);
        sys.checkout("staff", order);
    }
}
