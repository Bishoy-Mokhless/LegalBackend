package com.kazyonplus.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import static java.lang.Long.parseLong;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/init")
    public ResponseEntity init(){
        attachmentService.init();
        return ok(null);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("files")MultipartFile[] files,
            @RequestParam("id") String id,
            @RequestParam("type") SystemType type) throws IOException {
        return ok(attachmentService.save(files, parseLong(id), type));
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable(name = "fileName") String fileName,
            @RequestParam("type") SystemType type) throws MalformedURLException {
        Resource resource = attachmentService.loadFileAsResource(fileName, type);
        String contentType = "application/zip";
        return ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @PostMapping("/append")
    public ResponseEntity<String> append(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("id") String id,
            @RequestParam("type") SystemType type) throws IOException {
        return ok(attachmentService.appendFileToZip(files, parseLong(id), type));
    }
}
