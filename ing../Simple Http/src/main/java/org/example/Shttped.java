package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;


public class Shttped {

    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(
                    new InetSocketAddress("localhost", 8080), 0);

            System.out.println("This server context: " + System.getProperty("user.dir"));

            httpServer.createContext("/" , new MyHandler());
            httpServer.setExecutor(null);
            httpServer.start();
            
        } catch (IOException ig) {
            ig.printStackTrace();
            System.err.println("IO EXCEPTION");
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {

            long exchangeTime = System.currentTimeMillis();
            String method = exchange.getRequestMethod();
            String userDir = System.getProperty("user.dir");

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody()))) {

                String requestPath = exchange.getRequestURI().getPath();

                switch (method) {
                    case "GET":
                        File getFile = new File(userDir + requestPath);

                        if (!getFile.exists()) {
                            exchange.sendResponseHeaders(403, -1);
                            exchangeTime = System.currentTimeMillis() - exchangeTime;
                            printLog(exchange, exchangeTime);
                        }

                        if (!getFile.isFile()) {
                            StringBuilder htmlBuilder = htmlBuilder(getFile);
                            exchange.getResponseHeaders().set("content-type", "text/html");
                            exchange.sendResponseHeaders(200, htmlBuilder.length());

                            bufferedWriter.write(htmlBuilder.toString());
                            bufferedWriter.flush();

                        } else {
                            BufferedReader fileBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(getFile)));
                            StringBuilder fileBuilder = readBuffer(fileBuffer);

                            String contentType = "text" + "/" + getFile.getName().substring(getFile.getName().indexOf(".") + 1);
                            contentType = contentType.replace("txt", "plain");

                            if (!getFile.getName().equals("dontRead.html")) {
                                exchange.getResponseHeaders().set("content-type", contentType + "; charset=utf-8");
                                exchange.sendResponseHeaders(200, fileBuilder.toString().getBytes().length);
                                bufferedWriter.write(fileBuilder.toString());
                                bufferedWriter.flush();
                            }

                            else
                                exchange.sendResponseHeaders(403, -1);

                            fileBuffer.close();
                        }

                        break;

                    case "POST": // Authorization: Basic ZGFuYmk= (base64 "danbi" encoding 값) 헤더 포함해 POST 요청해야 함
                        boolean isFilePost = exchange.getRequestHeaders().get("content-type")
                                .get(0).contains("multipart/form-data");

                        String encodingBase64 = exchange.getRequestHeaders().get("authorization").get(0);

                        if (isFilePost) {
                            if (!decodeBASE64(encodingBase64).equals("danbi")) {
                                exchange.sendResponseHeaders(403, -1);

                            } else {
                                StringBuilder readFileBuilder = readBuffer(bufferedReader);
                                File f = new File(System.getProperty("user.dir") + "/post.txt");
                                if (!f.exists()) {

                                    if (f.createNewFile()) {
                                        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)))) {

                                            fileWriter.write(readFileBuilder.toString());
                                            fileWriter.flush();
                                        }

                                        exchange.sendResponseHeaders(200, -1);

                                    } else {
                                        exchange.sendResponseHeaders(202, -1);
                                    }

                                } else {
                                    exchange.sendResponseHeaders(409, -1);
                                }

                            }

                        } else {
                            exchange.sendResponseHeaders(405, -1);
                        }

                        break;
                    case "DELETE":
                        File deleteFile = new File(userDir + requestPath);

                        if (deleteFile.exists()) {
                            Files.delete(deleteFile.toPath());
                            exchange.sendResponseHeaders(200, -1);

                        } else {
                            exchange.sendResponseHeaders(403, -1);
                        }

                        break;

                    default:
                        break;
                }
                exchangeTime = System.currentTimeMillis() - exchangeTime;
                printLog(exchange, exchangeTime);
            } catch (IOException ig) {
                ig.printStackTrace();
                System.out.println("content-length or file EXCEPTION");
            }

        }

        public void printLog(HttpExchange exchange, Long exchangeTime) {
            System.out.printf("%s %s %s %s, %s, %s%n",
                    exchange.getResponseHeaders().get("Date"),
                    exchange.getRequestMethod(),
                    exchange.getRequestURI(),
                    exchange.getResponseCode(),
                    "length: " + exchange.getResponseHeaders().get("Content-length").get(0),
                    "millisecond: " + exchangeTime);
        }

        public StringBuilder readBuffer(BufferedReader bufferedReader) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
            } catch (IOException ig) {
                ig.printStackTrace();
                System.err.println("입력버퍼 오류");
            }

            return stringBuilder;
        }

        public StringBuilder htmlBuilder(File fileDir) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html>\n <head></head>\n  <body>\n\n");
            Arrays.stream(Objects.requireNonNull(fileDir.list()))
                    .forEach(h -> {
                        stringBuilder.append("   <p>\n    ");
                        stringBuilder.append(Objects.requireNonNullElse(h, ""));
                        stringBuilder.append("\n   </p>\n\n");
                    });

            stringBuilder.append("  </body>\n</html>");

            return stringBuilder;
        }

        public String decodeBASE64 (String encodingString) {
            byte[] decodingBytes = Base64.getDecoder().decode(encodingString.replace("Basic ", ""));

            return new String(decodingBytes);
        }

    }

}