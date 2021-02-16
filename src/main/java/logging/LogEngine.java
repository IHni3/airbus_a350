package logging;

import configuration.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum LogEngine {
    instance;
    private PrintWriter printWriter;

    public void init() {

        try {
            createPathIfNotExists(Configuration.instance.logFileDirectory);

            printWriter = new PrintWriter(Configuration.instance.logFile);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void createPathIfNotExists(String path) throws IOException {
        var p = Paths.get(path);
        if(!Files.exists(p))
        {
            Files.createDirectory(p);
        }
    }

    public void write(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        printWriter.write(simpleDateFormat.format(currentDate) + " : " + message + Configuration.instance.lineSeparator);
    }

    public void close() {
        printWriter.close();
    }
}