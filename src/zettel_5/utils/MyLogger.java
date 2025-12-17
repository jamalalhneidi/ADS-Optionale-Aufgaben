package zettel_5.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLogger {
    private static MyLogger instance;
    private PrintWriter writer;
    private final String LOG_FILE = "debug.log";

    // Privater Konstruktor
    private MyLogger() {
        try {
            // FileWriter(file, false) -> Das 'false' sorgt dafür, dass die Datei
            // überschrieben wird
            writer = new PrintWriter(new FileWriter(LOG_FILE, false), true);
        } catch (IOException e) {
            // Nur im absoluten Notfall auf Konsole ausweichen
            System.err.println("Logger konnte nicht erstellt werden: " + e.getMessage());
        }
    }

    // Singleton-Zugriff
    public static MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    public void log(String message) {
        if (writer != null) {
            writer.print(message);
        }
    }

    public void logln(String message) {
        if (writer != null) {
            // String timestamp =
            // LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd
            // HH:mm:ss"));
            // writer.println(timestamp + " - " + message);
            writer.println(message);
        }
    }

    // Schließt den Stream (gute Praxis)
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}