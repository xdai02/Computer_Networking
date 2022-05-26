import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        DatagramSocket sock = new DatagramSocket(SERVER_PORT);
        System.out.println("【服务端】服务器启动完毕。");
        System.out.println("【服务端】等待客户端连接...");

        DatagramPacket packet = new DatagramPacket(new byte[100], 100);
        // 持续接收和响应信息
        while (true) {
            // 接收客户端发送的数据
            sock.receive(packet);
            System.out.print(
                "【服务端】客户端" + packet.getAddress() +
                ":" + packet.getPort() + "："
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