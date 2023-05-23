package ru.nsu.ccfit.lopatkin.store.processor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.UploadFileDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.FileService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/processing/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        try {
            File file = fileService.getFile(id);
            Resource fileResource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(fileResource);
        } catch (IOException e) {
            throw new LogicException("Download file failed", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadFileDTO> uploadFile(@RequestBody MultipartFile file) {
        try {
            return ResponseEntity.ok().body(fileService.uploadFile(file));
        } catch (IOException e) {
            throw new LogicException("Upload file failed", e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }

}