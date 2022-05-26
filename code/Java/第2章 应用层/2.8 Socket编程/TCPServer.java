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
        System.out.println("【服务端】服务器启动完毕。");
        System.out.println("【服务端】等待客户端连接...");

        Socket clientSocket = sock.accept();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("【服务端】客户端连接到服务器");

        // 持续接收和响应信息
        while(true) {
            // 接收客户端发送的数据
            String data = reader.readLine();
            System.out.println("【服务端】接收数据：" + data);

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
