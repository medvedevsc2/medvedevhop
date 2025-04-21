package medvedev.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import medvedev.services.LogService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Log controller", description = "API для работы с логами")
@RestController
@RequestMapping("/logs")
public class    LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * Формирует и возвращает лог-файл за указанную дату.
     *
     * @param date Дата в формате yyyy-MM-dd.
     * @return Лог-файл в виде ответа.
     * @throws IOException Если произошла ошибка при формировании лог-файла.
     */

    @GetMapping("/filter")
    public ResponseEntity<Resource> getLogsForDate(@RequestParam String date) throws IOException {
        Resource logFile = logService.getLogFileForDate(date);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + logFile.getFilename() + "\"")
                .body(logFile);
    }
}