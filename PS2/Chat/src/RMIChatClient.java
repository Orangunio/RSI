import java.rmi.registry.*;
import java.util.Scanner;

public class RMIChatClient {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            Registry reg = LocateRegistry.getRegistry("192.168.7.5", 1099);
            ChatInterface chat = (ChatInterface) reg.lookup("ChatObject");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your name and press Enter: ");
            String name = sc.nextLine();
            String myPrefix = "[" + name + "]";
            Thread receiver = new Thread(() -> {
                String lastSeenMsg = "";
                while (true) {
                    try {
                        String currentMsg = chat.odczytaj();
                        if (!currentMsg.equals(lastSeenMsg) && !currentMsg.startsWith(myPrefix)) {
                            System.out.println("\nWiadomość od znajomego: " + currentMsg);
                            System.out.print("> ");
                            lastSeenMsg = currentMsg;
                        }
                        else if (currentMsg.startsWith(myPrefix)) {
                            lastSeenMsg = currentMsg;
                        }

                        Thread.sleep(500);
                    } catch (Exception e) {
                        break;
                    }
                }
            });
            receiver.setDaemon(true);
            receiver.start();
            System.out.println("Czat aktywny. Pisz wiadomości poniżej:");
            while (true) {
                System.out.print("> ");
                String msg = sc.nextLine();
                if (!msg.isEmpty()) {
                    chat.wyslij(myPrefix + " " + msg);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}