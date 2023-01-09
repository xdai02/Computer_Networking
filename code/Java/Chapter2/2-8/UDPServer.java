import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        DatagramSocket sock = new DatagramSocket(SERVER_PORT);
        System.out.println("[Server] Server started.");
        System.out.println("[Server] Waiting for connection...");

        DatagramPacket packet = new DatagramPacket(new byte[100], 100);

        while (true) {
            sock.receive(packet);
            System.out.print(
                "[Server] Client" + packet.getAddress() + ":" + packet.getPort() + ": "
            );
            String data = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println(data);

            if (data.equals("exit")) {
                packet.setData("exit".getBytes());
                sock.send(packet);
                break;
            } else {
                sock.send(packet);
            }
        }

        sock.close();
    }
}