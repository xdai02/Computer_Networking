#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <errno.h>

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
    server_addr.sin_addr.s_addr = INADDR_ANY;  // 本主机的任意IP地址

    int ret = bind(sock, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (ret < 0) {
        perror("Bind");
        exit(1);
    }

    ret = listen(sock, 5);
    if (ret < 0) {
        perror("Listen");
        exit(1);
    }

    printf("【服务端】服务器启动完毕。\n");
    printf("【服务端】等待客户端连接...\n");

    struct sockaddr_in client_addr;
    socklen_t client_size = sizeof(client_addr);
    // 当有客户端连接后，获取客户端的socket和地址
    int client = accept(sock, (struct sockaddr *)&client_addr, &client_size);
    if (client < 0) {
        perror("Client");
        exit(1);
    }
    printf("【服务端】客户端连接到服务器\n");

	// 持续接收和响应信息
    while (1) {
        char data[128] = {0};
        recv(client, data, sizeof(data), 0);
        printf("【服务端】接收数据：%s\n", data);

        if (strcmp(data, "exit") == 0) {
            send(client, "exit", strlen("exit"), 0);
            break;
        } else {
            send(client, data, strlen(data), 0);
        }
    }

    close(client);
    close(sock);
    return 0;
}