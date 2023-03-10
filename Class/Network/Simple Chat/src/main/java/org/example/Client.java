package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    String ip;
    int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(ip, port)) {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Thread inputThread = new Thread(() -> {
                try {
                    while(!Thread.interrupted()) {
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            inputThread.start();

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 6000);
        client.start();
    }
}
