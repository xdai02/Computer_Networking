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

        // 客户端持续与服务端交互
        while (true) {
            System.out.print("【客户端】输入数据：");
            String msg = scanner.nextLine();
            writer.println(msg);
            String reply = reader.readLine();
            if (reply.equals("exit")) {
                break;
            } else {
                System.out.println("【服务端】" + reply);
            }
        }

        sock.close();
        reader.close();
        writer.close();
        scanner.close();
    }
}
