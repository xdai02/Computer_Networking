import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((SERVER_HOST, SERVER_PORT))
    sock.listen()
    print("【服务端】服务器启动完毕。")
    print("【服务端】等待客户端连接...")

    # 当有客户端连接后，获取客户端的socket和地址
    client, client_addr = sock.accept()
    print("【服务端】客户端%s（port: %s）连接到服务器" % client_addr)

    # 持续接收和响应信息
    while True:
        # 接收客户端发送的数据
        data = client.recv(100).decode("UTF8")
        print("【服务端】接收数据：%s" % data)
        if data == "exit":
            client.send("exit".encode("UTF8"))
            break
        else:
            client.send(("【服务端】%s" % data).encode("UTF8"))
    
    sock.close()

if __name__ == "__main__":
    main()
