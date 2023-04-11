package socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 10001)) {

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread inputThread = new Thread(() ->  {
               try {
                   while(!Thread.interrupted()) {
                       terminal.write(input.readLine());
                       terminal.flush();
                   }
               } catch ( IOException e) {
                   throw new RuntimeException(e);
               }
            });
            inputThread.start();

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();

                if ( line.equals("끝")) {
                    break;
                }
            }

            String value = input.readLine();
            System.out.println("Received: " + value);

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("연결을 실패하였습니다.");
        }
    }
}
//Client