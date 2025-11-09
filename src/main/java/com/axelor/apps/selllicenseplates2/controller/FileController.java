package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    private final FileUtils fileUtils;

    @GetMapping()
    public ResponseEntity<?> getFileByLink(@RequestParam("path") String path) {
        return fileUtils.getOutputFile(path);
    }
}
