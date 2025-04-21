package medvedev.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import medvedev.services.LogService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Log-controller", description = "Operations for accessing and filtering log files")
@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @Operation(summary = "Download filtered log file by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtered log file returned"),
            @ApiResponse(responseCode = "400", description = "Invalid date format or file error"),
            @ApiResponse(responseCode = "404", description = "Log file not found for the specified date")
    })
    @GetMapping("/filter")
    public ResponseEntity<Resource> getLogsForDate(
            @Parameter(description = "Date to filter log entries (format: yyyy-MM-dd)")
            @RequestParam String date) throws IOException {

        Resource logFile = logService.getLogFileForDate(date);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + logFile.getFilename() + "\"")
                .body(logFile);
    }
}
