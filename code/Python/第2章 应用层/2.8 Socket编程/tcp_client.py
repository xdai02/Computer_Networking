import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((SERVER_HOST, SERVER_PORT))

    # 客户端持续与服务端交互
    while True:     
        msg = input("【客户端】输入数据：")
        sock.send(msg.encode("UTF8"))
        reply = sock.recv(100).decode("UTF8")
        if reply == "exit":
            break
        else:
            print(reply)

    sock.close()

if __name__ == "__main__":
    main()