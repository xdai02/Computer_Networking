#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <errno.h>

#define SERVER_ADDR "127.0.0.1"
#define SERVER_PORT 8080

int main() {
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("Socket");
        exit(1);
    }

    struct sockaddr_in server_addr;
    memset(&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(SERVER_PORT);
    server_addr.sin_addr.s_addr = inet_addr(SERVER_ADDR);

    int ret = connect(sock, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (ret < 0) {
        perror("Connect");
        exit(1);
    }

    while (1) {
        char data[128] = {0};
        printf("[Client] Enter data: ");
        fgets(data, sizeof(data), stdin);
        data[strlen(data) - 1] = '\0';

        ret = send(sock, data, strlen(data), 0);

        char reply[128] = {0};
        recv(sock, reply, sizeof(reply), 0);
        if (strcmp(reply, "exit") == 0) {
            break;
        } else {
            printf("[Server] %s\n", reply);
        }
    }

    close(sock);
    return 0;
}