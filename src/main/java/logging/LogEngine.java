package logging;

import configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum LogEngine {
    instance;
    private PrintWriter printWriter;

    public void init() {
        try {
            printWriter = new PrintWriter(new File(Configuration.instance.logFile));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
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