package org.example;

import org.apache.commons.cli.*;

public class Option {

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "도움말");
        options.addOption(org.apache.commons.cli.Option.builder("H")
                .argName("host")
                .valueSeparator('=')
                .hasArg()
                .desc("접속할 서버의 호스트 네임 또는 IP를 지정합니다.")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("I")
                .argName("user id")
                .valueSeparator('=')
                .hasArg()
                .desc("채팅에서 사용할 사용자 아이디를 지정합니다.")
                .build());
        options.addOption(org.apache.commons.cli.Option.builder("p")
                .argName("port")
                .valueSeparator('=')
                .hasArg()
                .desc("접속할 서버의 서비스 포트를 지정합니다.")
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if (cmd.hasOption("H")) {
                System.out.println("H: " + cmd.getOptionValue("H"));
            } else if (cmd.hasOption("I")) {
                System.out.println("I: " + cmd.getOptionValue("I"));
            } else if (cmd.hasOption("p")) {
                System.out.println("p: " + cmd.getOptionValue("p"));
            }

        } catch (ParseException ignore) {
            System.err.println("명령어 인수가 잘못되었습니다.");
            System.err.println(ignore.getMessage());
        }
    }
}
