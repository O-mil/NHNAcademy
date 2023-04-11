package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    Thread thread;
    String host;
    int port;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.interrupted()) {
                System.out.println("연결 대기 중");
                Handler handler = new Handler(serverSocket.accept());
                System.out.println("연결 되었습니다.");
                handler.start();
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("연결 종료");
    }

    public static void main(String[] args) {
        Server server = new Server("localhost", 6000);
        server.start();
    }
}
