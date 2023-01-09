import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private final static String SERVER_ADDR = "127.0.0.1";
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        Socket sock = new Socket(SERVER_ADDR, SERVER_PORT);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(sock.getInputStream())
        );
        PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("[Client] Enter data: ");
            String msg = scanner.nextLine();
            writer.println(msg);
            String reply = reader.readLine();
            if (reply.equals("exit")) {
                break;
            } else {
                System.out.println("[Server] " + reply);
            }
        }

        sock.close();
        reader.close();
        writer.close();
        scanner.close();
    }
}
