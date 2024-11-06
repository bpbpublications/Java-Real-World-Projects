package dev.davivieira.controller;

import dev.davivieira.entity.File;
import dev.davivieira.metrics.FileMetric;
import dev.davivieira.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileController {

    private final FileService fileService;
    private final FileMetric fileMetric;

    @Autowired
    FileController(FileService fileService, FileMetric fileMetric) {
        this.fileService = fileService;
        this.fileMetric = fileMetric;
    }

    @PostMapping("/file")
    private String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        incrementRequestCounter(HttpMethod.POST.name(), "/file");

        var fileToUpload = new File(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getBytes());
        fileService.uploadFile(fileToUpload);
        return fileToUpload.getId();
    }

    @GetMapping("/file/{id}")
    private ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        incrementRequestCounter(HttpMethod.GET.name(), "/file/"+id);

        var file = fileService.downloadFile(id);

        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        recordDownloadSizeSummary(file.get());

        var resource = new ByteArrayResource(file.get().getContent());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(file.get().getName())
                                .build().toString())
                .body(resource);
    }

    private void incrementRequestCounter(String method, String path) {
        fileMetric.requestCounter(method, path).increment();
    }

    private void recordDownloadSizeSummary(File file) {
        fileMetric.fileDownloadSizeSummary(file.getName()).record(file.getContent().length);
    }
}
