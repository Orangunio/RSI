import java.rmi.registry.*;
import java.util.Scanner;

public class RMIChatClient {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            Registry reg = LocateRegistry.getRegistry("10.28.207.24", 1099);
            ChatInterface chat = (ChatInterface) reg.lookup("ChatObject");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your name and press Enter: "); // [cite: 42, 88]
            String name = sc.nextLine();

            while(true) {
                System.out.print("> ");
                String msg = sc.nextLine();
                chat.wyslij("[" + name + "] " + msg); // [cite: 92]
                System.out.println("Ostatnio na czacie: " + chat.odczytaj());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}