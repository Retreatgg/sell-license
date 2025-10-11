package com.axelor.apps.selllicenseplates2.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component
public class FileUtils {
    private static final String UPLOAD_DIR = "data";

    @SneakyThrows
    public String saveUploadedFile(MultipartFile file, String subDir) {
        String fileLink = "POST"+  "-" + LocalDate.now() +"-"+ file.getOriginalFilename();
        return saveFile(subDir, fileLink, file);
    }

    public ResponseEntity<?> getOutputFile(String fileName) {
        try {
            Path filePath = Paths.get("data/images/"+fileName);
            if (! Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(filePath));
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            log.error("No file found:", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @SneakyThrows
    private String saveFile(String subDir, String fileLink, MultipartFile file) {
        Path pathDir = Paths.get(UPLOAD_DIR + "/" + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + fileLink);
        if(!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try(OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e){
            log.error(e.getMessage());
        }
        return fileLink;
    }

    public String getTypeFile(MultipartFile file) {
        String type = file.getOriginalFilename();

        int index = type.lastIndexOf('.');
        int indexWithDot = index + 1;
        return index == -1? null : type.substring(indexWithDot);
    }
}
