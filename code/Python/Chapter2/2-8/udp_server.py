import socket

SERVER_HOST = "127.0.0.1"
SERVER_PORT = 8080

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((SERVER_HOST, SERVER_PORT))
    print("[Server] Server started.")
    print("[Server] Waiting for connection...")

    while True:
        data, client_addr = sock.recvfrom(100)
        print("[Server] Client%s:%s: " % client_addr, end="：")
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