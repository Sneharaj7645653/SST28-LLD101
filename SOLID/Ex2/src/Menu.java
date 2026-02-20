import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    private static Map<String, MenuItem> menu ;

    public Menu() {
            menu = new LinkedHashMap<>();
    }
    public Map<String, MenuItem> getMenu() {
        return menu;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }
}
