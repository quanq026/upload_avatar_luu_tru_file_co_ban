package com.rikkei.course141.ss1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    private final Path uploadDir = Paths.get("uploads");

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File rỗng"));
        }
        Files.createDirectories(uploadDir);
        Path target = uploadDir.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return ResponseEntity.ok(Map.of("message", "Upload thành công", "fileName", file.getOriginalFilename()));
    }
}
