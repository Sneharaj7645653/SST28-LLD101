import java.util.*;

public class CafeteriaSystem {
    private final Menu menu;
    private final Invoice invoice;

    CafeteriaSystem(Menu menu, Invoice invoice) {
        this.menu = menu;
        this.invoice = invoice;
    }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
            String inv = invoice.generateInvoice(customerType, lines);
            System.out.println(inv);
    }

}
