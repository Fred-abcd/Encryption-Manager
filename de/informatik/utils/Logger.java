package de.informatik.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GRAY = "\u001B[90m";


    public void logInfo(String logMessage) {
        log(logMessage, LogLevel.INFO);
    }

    public void logSuccess(String logMessage) {
        log(logMessage, LogLevel.SUCCESS);
    }

    public void logError(String logMessage) {
        log(logMessage, LogLevel.ERROR);
    }


    private void log(String logMessage, LogLevel logLevel) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        String formattedLogMessage = String.format("[%s] %s%s: %s%s", formattedTime, logLevel.getColor(), logLevel, logMessage, ANSI_RESET);

        System.out.println(formattedLogMessage);
    }

    public enum LogLevel {
        INFO(ANSI_YELLOW),
        SUCCESS(ANSI_GREEN),
        ERROR(ANSI_RED);

        private final String color;

        LogLevel(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }
}