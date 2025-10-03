import java.util.*;

public class main {
    static final int CHAIRS = 6; 
    static String[] shop = new String[CHAIRS]; // array for chairs
    static int vipCount = 0;   // VIP numbering
    static int ordCount = 0;   // ORD numbering
    static Random rand = new Random();

    // This will print the current state of the shop
    static String getState() {
        StringBuilder state = new StringBuilder("[ ");
        for (int i = 0; i < CHAIRS; i++) {
            if (shop[i] == null) state.append("----");
            else state.append(shop[i]);
            if (i < CHAIRS - 1) state.append(" : ");
        }
        state.append(" ]");
        return state.toString();
    }

    // This will add VIP client (it will always takes first chair, shift others right)
    static String addVIP() {
        vipCount++;
        String client = "VIP" + vipCount;

        // If the shop is full → reject
        if (shop[CHAIRS - 1] != null) {
            return "+-" + client;
        }

        // This will shift everyone right
        for (int i = CHAIRS - 1; i > 0; i--) {
            shop[i] = shop[i - 1];
        }
        shop[0] = client;
        return "++" + client;
    }

    // This adds an ORD client (goes to first empty seat)
    static String addORD() {
        ordCount++;
        String client = "ORD" + ordCount;

        // If the shop is full → reject
        if (shop[CHAIRS - 1] != null) {
            return "+-" + client;
        }

        // Find the first empty chair
        for (int i = 0; i < CHAIRS; i++) {
            if (shop[i] == null) {
                shop[i] = client;
                return "++" + client;
            }
        }
        return "+-" + client; // fallback
    }

    // Finish the client (then remove first chair)
    static String finishClient() {
        String leaving = shop[0];
        for (int i = 0; i < CHAIRS - 1; i++) {
            shop[i] = shop[i + 1];
        }
        shop[CHAIRS - 1] = null;
        return "--" + leaving;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Print header
        System.out.printf("%-5s %-8s %-20s %-60s\n", "X", "", "Events", "State of the Shop");
        System.out.println("------------------------------------------------------------------------------------------");

        while (true) {
            String input = sc.nextLine();
            if (!input.equals("")) break; // if not ENTER → stop

            int x;
            while (true) {
                x = rand.nextInt(4); // 0–3
                if (x == 0 && shop[0] == null) continue; // skip if there is  no client
                break;
            }

            String event;
            if (x == 0) {
                event = finishClient();
            } else if (x == 1) {
                event = addVIP();
            } else { // x == 2 or 3
                event = addORD();
            }

            // Format: X -----> Event     State
            System.out.printf("%-5d -----> %-18s %-60s\n", x, event, getState());
        }

        System.out.println("Simulation ended.");
    }
}
