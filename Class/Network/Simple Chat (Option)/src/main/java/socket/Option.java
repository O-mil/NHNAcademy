package socket;

import org.apache.commons.cli.*;
public class Option {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "print this message");
        options.addOption("projecthelp", false, "print project help information");
        options.addOption("version", false, "print the version information and exit");
        options.addOption("quiet", false, "be extra quiet");
        options.addOption("verbose", false, "be extra verbose");
        options.addOption("debug", false, "print debugging information");
        options.addOption("emacs", false, "produce logging information without adornments");

        org.apache.commons.cli.Option logfileOption = org.apache.commons.cli.Option.builder("logfile")
                                    .argName("file")
                                    .valueSeparator('=')
                                    .hasArg()
                                    .desc("use given file for log")
                                    .build();
        options.addOption(logfileOption);
        org.apache.commons.cli.Option loggerOption = org.apache.commons.cli.Option.builder("logger")
                                    .argName("classname")
                                    .valueSeparator('=')
                                    .hasArg()
                                    .desc("use given file for log")
                                    .build();
        options.addOption(loggerOption);
        org.apache.commons.cli.Option listenerOption = org.apache.commons.cli.Option.builder("listener")
                .argName("classname")
                .valueSeparator('=')
                .hasArg()
                .desc("add an instance of class as a project listener")
                .build();
        options.addOption(listenerOption);
        org.apache.commons.cli.Option buildfileOption = org.apache.commons.cli.Option.builder("buildfile")
                .argName("file")
                .valueSeparator('=')
                .hasArg()
                .desc("use given buildfile")
                .build();
        options.addOption(buildfileOption);
        org.apache.commons.cli.Option DOption = org.apache.commons.cli.Option.builder("D")
                .argName("property")
                .valueSeparator('=')
                .hasArg()
                .desc("use value for given property")
                .build();
        options.addOption(DOption);



        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {       //이 옵션이 있을 떄
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if (cmd.hasOption("projecthelp")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if (cmd.hasOption("version")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if ( cmd.hasOption("quiet")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if (cmd.hasOption("verbose")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if (cmd.hasOption("emacs")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else if ( cmd.hasOption("logfile")) {
                System.out.println("Log file: " + cmd.getOptionValue("logfile"));
            } else if (cmd.hasOption("logger")) {
                System.out.println("Logger: " + cmd.getOptionValue("logger"));
            }else if (cmd.hasOption("listener")) {
                System.out.println("Listener: " + cmd.getOptionValue("listener"));
            }else if (cmd.hasOption("buildfile")) {
                System.out.println("Buildfile: " + cmd.getOptionValue("buildfile"));
            }else if (cmd.hasOption("D")) {
                System.out.println("D: " + cmd.getOptionValue("D"));
            }

        } catch (ParseException ignore) {
            System.err.println("명령어 인수가 잘못되었습니다.");
            System.err.println(ignore.getMessage());
        }
    }
}

