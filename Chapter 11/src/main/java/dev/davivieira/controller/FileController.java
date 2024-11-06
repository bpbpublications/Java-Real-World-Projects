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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
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
    private ResponseEntity<Resource> downloadFile(@PathVariable String id) throws NoSuchAlgorithmException, IOException {
        incrementRequestCounter(HttpMethod.GET.name(), "/file/"+id);

        var file = fileService.downloadFile(id);

        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var filename = Long.toHexString(Double.doubleToLongBits(Math.random()))+"-"+file.get().getName();
        var content = addRandomByteContent(file.get().getContent());
        var resource = new ByteArrayResource(content);

        recordDownloadSizeSummary(filename, content);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(filename)
                                .build().toString())
                .body(resource);
    }

    private void incrementRequestCounter(String method, String path) {
        fileMetric.requestCounter(method, path).increment();
    }

    private void recordDownloadSizeSummary(String fileName, byte[] content) {
        fileMetric.fileDownloadSizeSummary(fileName).record(content.length);
    }

    private byte[] addRandomByteContent(byte[] content) throws IOException, NoSuchAlgorithmException {
        var min = 500000;
        var max = 9000000;
        var randomSize = new Random().nextInt(max - min + 1) + min;
        byte[] randomContent = new byte[randomSize];
        SecureRandom.getInstanceStrong().nextBytes(randomContent);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        output.write(content);
        output.write(randomContent);

        return output.toByteArray();
    }
}
