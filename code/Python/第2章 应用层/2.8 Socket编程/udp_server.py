import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((SERVER_HOST, SERVER_PORT))
    print("【服务端】服务器启动完毕。")
    print("【服务端】等待客户端连接...")

    # 持续接收和响应信息
    while True:
        # 接收客户端发送的数据
        data, client_addr = sock.recvfrom(100)
        print("【服务端】客户端%s:%s：" % client_addr, end="：")
        data = data.decode("UTF8")
        print(data)
        if data == "exit":
            sock.sendto("exit".encode("UTF8"))
            break
        else:
            sock.sendto(data.encode("UTF8"), client_addr)
    
    sock.close()

if __name__ == "__main__":
    main()
