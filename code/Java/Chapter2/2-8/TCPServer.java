import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket sock = new ServerSocket(SERVER_PORT);
        System.out.println("[Server] Server started.");
        System.out.println("[Server] Waiting for connection...");

        Socket clientSocket = sock.accept();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("[Server] Client connected to server.");

        while(true) {
            String data = reader.readLine();
            System.out.println("[Server] Received:" + data);

            if(data.equals("exit")) {
                writer.println("exit");
                break;
            } else {
                writer.println(data);
            }
        }

        sock.close();
        clientSocket.close();
        reader.close();
        writer.close();
    }
}
