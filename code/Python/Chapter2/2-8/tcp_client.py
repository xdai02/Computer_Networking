import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((SERVER_HOST, SERVER_PORT))

    while True:     
        msg = input("[Client] Enter data: ")
        sock.send(msg.encode("UTF8"))
        reply = sock.recv(100).decode("UTF8")
        if reply == "exit":
            break
        else:
            print("[Server] " + reply)

    sock.close()

if __name__ == "__main__":
    main()