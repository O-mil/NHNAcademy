package org.Scrul;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

import org.json.JSONObject;

public class Scurl {

    private static Options options;
    private static CommandLine cmd;
    public static HttpURLConnection conn;
    private static JSONObject jsonObject;
    private static BufferedReader input;


    /**
     * 옵션
     */
    public static void OptionClass() {
        options = new Options();

        options.addOption("V", false, "verse, 요청, 응답 헤더를 출력합니다.");
        options.addOption(Option.builder("H").hasArg().argName("line").desc("임의의 헤더를 서버로 전송합니다.").build());
        options.addOption(Option.builder("d").hasArg().argName("data").desc("POST, PUT 등의 데이터를 전송합니다.").build());
        options.addOption(Option.builder("X").hasArg().argName("Command").desc("사용할 method를 지정합니다. 지정되지 않은 경우 기본값은 GET입니다.").build());
        options.addOption("L", false, "서버의 응딥이 30x 계열이면 다음 응답을 따라 갑니다.");
        options.addOption(Option.builder("F").hasArg().argName("name=Content").desc("multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.").build());
    }

    /**
     *
     * @param args argument
     * @param Method method
     */
    public static void HConn(String args, String Method) {
        try {
            input = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            URL url = new URL(args);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(Method);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            StringBuffer Sb = new StringBuffer();
            String lines;
            while ((lines = input.readLine()) != null) {
                Sb.append(lines);
                Sb.append('\n');
            }
            jsonObject = new JSONObject(Sb.toString());

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     *
     * @param args argument
     */
    public static void main(String[] args) {

        try {
            OptionClass();
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("scurl", options);
            } else if (cmd.hasOption("V")) {
                if (cmd.hasOption("H")) {
                    String[] line = args[3].split(":");
                    HConn(args[4], "GET");
                    jsonObject.put(line[0], line[1].trim());
                    System.out.println(jsonObject.toString(4));
                } else {
                    HConn(args[2], "GET");
                    conn.getHeaderFields().entrySet().stream().filter(entry -> entry.getKey() != null).forEach(entry -> {
                        System.out.printf("%s: %s%n", entry.getKey(), String.join(", ", entry.getValue()));
                    });
                }
            } else if (cmd.hasOption("d")) {
                HConn(args[1], "GET");
                System.out.println(jsonObject.toString(4));
            } else if (cmd.hasOption("X")) {
                if (cmd.hasOption("X"))
                    HConn(args[3], "GET");
                System.out.println(jsonObject.toString(4));
            } else if (cmd.hasOption("L")) {
                HConn(args[1], "GET");
                System.out.println(jsonObject.toString(4));
            } else if(cmd.hasOption("F")) {
                String file = cmd.getOptionValue("F").substring(cmd.getOptionValue("F").lastIndexOf("@")+1);
                conn.setRequestProperty("multipart/form-data",file);
                conn.connect();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }
}
