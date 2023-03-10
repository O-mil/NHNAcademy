package org.example;


import org.apache.commons.cli.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc {

    public static void main(String[] args) {
        Options options = new Options();

        Option server = Option.builder("l")
                .argName("port")
                .hasArg()
                .desc("서버 모드로 동작, 입력 받은 포트로 listen")
                .build();
        options.addOption(server);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("l")) {
                int port = Integer.parseInt(args[1]);
                Server(port);
            } else {
                String ip = args[0];
                int port = Integer.valueOf(args[1]);
                Clint(ip, port);
            }

        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    public static void Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Socket socket = serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));


            while (!Thread.interrupted()) {
                String line = input.readLine();
                output.write(line);
                output.newLine();
                output.flush();

                log.write(line);
                log.newLine();
                log.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Clint(String ip, int port) {
        try (Socket socket = new Socket(ip, port)) {

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();

                String line2 = input.readLine();
                terminal.write(line2);
                terminal.newLine();
                terminal.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
