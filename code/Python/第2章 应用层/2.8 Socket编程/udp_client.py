import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    # 客户端持续与服务端交互
    while True:     
        msg = input("【客户端】输入数据：")
        sock.sendto(msg.encode("UTF8"), (SERVER_HOST, SERVER_PORT))
        reply, addr = sock.recvfrom(100)
        reply = reply.decode("UTF8")
        if reply == "exit":
            break
        else:
            print(reply)

    sock.close()

if __name__ == "__main__":
    main()
