import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private final static String SERVER_ADDR = "127.0.0.1";
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        DatagramSocket sock = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(new byte[100], 100);
        packet.setAddress(InetAddress.getByName(SERVER_ADDR));
        packet.setPort(SERVER_PORT);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("[Client] Enter data: ");
            String msg = scanner.nextLine();
            byte[] buffer = msg.getBytes();
            packet.setData(buffer);
            sock.send(packet);

            sock.receive(packet);
            String reply = new String(packet.getData(), packet.getOffset(), packet.getLength());
            if (reply.equals("exit")) {
                break;
            } else {
                System.out.println("[Server] " + reply);
            }
        }

        sock.close();
        scanner.close();
    }
}
