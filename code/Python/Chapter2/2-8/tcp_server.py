import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((SERVER_HOST, SERVER_PORT))
    sock.listen()
    print("[Server] Server started.")
    print("[Server] Waiting for connection...")

    # Get client's socket and address
    client, client_addr = sock.accept()
    print("[Server] Client%s:%s Connected to server." % client_addr)

    while True:
        data = client.recv(100).decode("UTF8")
        print("[Server] Received: %s" % data)
        if data == "exit":
            client.send("exit".encode("UTF8"))
            break
        else:
            client.send(data.encode("UTF8"))
    
    sock.close()

if __name__ == "__main__":
    main()