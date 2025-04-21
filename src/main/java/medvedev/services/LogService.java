package medvedev.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;



@Service
public class LogService {

    private static final String LOG_DIRECTORY = "logs/";
    private static final String COMMON_LOG_FILE = "application.log";

    public File createLogFileForDate(String date) throws IOException {
        Path commonLogPath = Paths.get(LOG_DIRECTORY + COMMON_LOG_FILE);
        File commonLogFile = commonLogPath.toFile();

        // Проверяем, существует ли общий лог-файл
        if (!commonLogFile.exists()) {
            throw new IOException("Common log file not found: " + commonLogPath.toAbsolutePath());
        }

        // Имя нового лог-файла
        String newLogFileName = String.format("application.%s.log", date);
        File newLogFile = new File(LOG_DIRECTORY + newLogFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(commonLogFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newLogFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Фильтруем строки, содержащие указанную дату
                if (line.startsWith(date)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        return newLogFile;
    }

    public Resource getLogFileForDate(String date) throws IOException {
        File logFile = createLogFileForDate(date);

        if (!logFile.exists()) {
            throw new IOException("Log file not created for date: " + date);
        }

        return new UrlResource(logFile.toURI());
    }
}