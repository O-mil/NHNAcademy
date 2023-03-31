package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Handler extends Thread {

    static int num = 0;
    private Socket socket;
    private String userId;
    private BufferedReader input;
    private BufferedWriter output;
    static List<Handler> handlerList = new LinkedList<>();

    public Handler(Socket socket) {
        this.socket = socket;
        this.userId = "user" + String.valueOf(++num);

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        Handler.handlerList.add(this);

        try {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    String line = input.readLine();
                    if (line == null) {
                        throw new InterruptedException();
                    }
                    line = line.trim();

                    if (line.startsWith("@")) {
                        // Direct message
                        String[] strings = line.split(" ", 2);
                        if (strings.length == 2) {
                            String recipient = strings[0].substring(1);
                            String message = strings[1];
                            directMessage(recipient, message);
                        }
                        if (line.startsWith("@all")) {
                            String message = line.substring(4);
                            broadcast("#" + userId + " " + message);
                        }
                        if (line.startsWith("@@")) {
                            if (strings.length == 2) {
                                String request = strings[1];
                                if (request.equals("time")) {
                                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                    output.write("#@ time " + time);
                                    output.newLine();
                                    output.flush();
                                }
                                if (request.equals("userlist")) {
                                    StringBuilder sb = new StringBuilder();
                                    synchronized (Handler.handlerList) {
                                        for (Handler handler : Handler.handlerList) {
                                            sb.append(handler.getUserId()).append(" ");
                                        }
                                        output.write(sb.toString().trim());
                                        output.newLine();
                                        output.flush();
                                    }
                                }
                                if (request.equals("accesstime")) {
                                    StringBuilder sb = new StringBuilder();
                                    synchronized (Handler.handlerList) {
                                        for (Handler handler : Handler.handlerList) {
                                            sb.append(handler.getUserId()).append(" - ").append(handler.getAccessTime()).append("\n");
                                        }
                                        output.write(sb.toString().trim());
                                        output.newLine();
                                        output.flush();
                                    }
                                }

                            }
                        }
                    }
                    else {
                        // Regular message
                        broadcast("#" + userId + " " + line);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException e) { }
            }
        } finally {
            try {
                this.input.close();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }

            try {
                this.output.close();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
            Handler.handlerList.remove(this);
        }
    }

    public void broadcast(String message) throws IOException {  // client 전체에 메시지 보내줌
        synchronized (Handler.handlerList) {
            for ( Handler handler: Handler.handlerList) {
                handler.output.write(message);
                handler.output.newLine();
                handler.output.flush();
            }
        }
    }


    public void directMessage(String recipient, String message) throws IOException {
        synchronized (Handler.handlerList) {
            for (Handler handler: Handler.handlerList) {
                if (handler.getUserId().equals(recipient)) {
                    handler.output.write("#" + userId + " " + message);
                    handler.output.newLine();
                    handler.output.flush();
                    break;
                }
            }
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(String.valueOf(socket.getRemoteSocketAddress())));
    }

}
