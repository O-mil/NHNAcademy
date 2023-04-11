package org.example;

import java.io.*;
import java.net.Socket;
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
        this.userId = String.valueOf(++num);

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
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

                    /*if (line.startsWith("hello")) {
                        String[] strings = line.split(" ", 10);
                        if ( strings.length > 1) {
                            userId = strings[1].trim();
                        }
                    } else if (line.charAt(0) == '@') {
                        String[] strings = line.split(" ", 10);
                        directMessage(strings[0].substring(1), line.substring(strings[0].length()));
                    } else {
                        broadcast(line.trim());
                    }*/
                    broadcast(line.trim());
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException ignore) {}
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

    /*public void directMessage(String userId, String message) throws IOException {
        synchronized (Handler.handlerList) {
            for ( Handler handler: Handler.handlerList) {
                if (handler.getUserId().equals(userId)) {
                    handler.output.write(message);
                    handler.output.newLine();
                    handler.output.flush();
                    break;
                }
            }
        }
    }*/

    public void broadcast(String message) throws IOException {  // client 전체에 메시지 보내줌
        synchronized (Handler.handlerList) {
            for ( Handler handler: Handler.handlerList) {
                handler.output.write(message);
                handler.output.newLine();
                handler.output.flush();
            }
        }
    }

    public String getUserId() {
        return userId;
    }
}