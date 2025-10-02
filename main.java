import java.util.*;

public class main {
    static final int CHAIRS = 6; 
    static String[] shop = new String[CHAIRS]; // this is an array to hold client
    static int vipCount = 0;   // numbering VIP clients
    static int ordCount = 0;   // numbering ORD clients

    // This is for printing the table header
    static void printHeader() {
        System.out.println("+--------------------------------------------------------------------------------+");
        System.out.printf("%-5s | %-12s | %-60s |\n", "X", "Events", "State of the Shop");
        System.out.println("+--------------------------------------------------------------------------------+");
    }

    // This is the state of the shop after each event
    static void printState(int x, String event) {
        StringBuilder state = new StringBuilder("[ ");
        for (int i = 0; i < CHAIRS; i++) {
            if (shop[i] == null) state.append("----");
            else state.append(shop[i]);
            if (i < CHAIRS - 1) state.append(" : ");
        }
        state.append(" ]");

        System.out.printf("%-5d | %-12s | %-60s |\n", x, event, state.toString());
    }

    // Add VIP client (higher priority → shift ORD clients to the right)
    static String addVIP() {
        vipCount++;
        String client = "VIP" + vipCount;

        // If the shop is full, the last client will leave
        if (shop[CHAIRS - 1] != null) {
            shop[CHAIRS - 1] = null;
        }

        // This will shift everyone to the right
        for (int i = CHAIRS - 1; i > 0; i--) {
            shop[i] = shop[i - 1];
        }
        shop[0] = client;

        return "++" + client;
    }

    // This adds an  ORD client (normal → goes to the first empty seat if available)
    static String addORD() {
        ordCount++;
        String client = "ORD" + ordCount;

        // If the shop is full, ORD client cannot enter
        if (shop[CHAIRS - 1] != null) {
            return "++" + client + " (Rejected)";
        }

        // Always find the first empty seat from the left
        for (int i = 0; i < CHAIRS; i++) {
            if (shop[i] == null) {
                shop[i] = client;
                break;
            }
        }
        return "++" + client;
    }

    // Finish current client (remove the first client)
    static String finishClient() {
        if (shop[0] == null) {
            return "--NONE"; 
        }
        String leaving = shop[0];
        for (int i = 0; i < CHAIRS - 1; i++) {
            shop[i] = shop[i + 1];
        }
        shop[CHAIRS - 1] = null;
        return "--" + leaving;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        printHeader();

        // Simulate 20 steps
        for (int step = 1; step <= 20; step++) {
            sc.nextLine(); 
            int x = rand.nextInt(3); 
            String event;

            if (x == 0) {
                event = finishClient();
            } else if (x == 1) {
                event = addVIP();
            } else {
                event = addORD();
            }

            printState(x, event);
        }
    }
}
