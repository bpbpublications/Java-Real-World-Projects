package dev.davivieira.file.service;

import dev.davivieira.file.entity.ExcelRemoteFile;
import dev.davivieira.file.entity.PdfRemoteFile;
import dev.davivieira.file.vo.Type;
import dev.davivieira.file.entity.RemoteFile;
import dev.davivieira.file.entity.WordRemoteFile;
import dev.davivieira.file.exception.FileTypeException;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileConverterService {

    private static final Logger logger = Logger.getLogger(FileConverterService.class.getName());

    public void convertFile(String fileUrl, Type conversionType) throws Exception {
        logger.log(Level.INFO, "Downloading file from "+fileUrl);
        var file = downloadFileInAVirtualThread(fileUrl);
        logger.log(Level.INFO, "The "+file+" has been sucessfully downloaded");

        logger.log(Level.INFO, "Converting "+file+" to "+conversionType);
        var convertedFile = file.convertTo(conversionType);
        logger.log(Level.INFO, "Conversion to "+conversionType+" was successfull! \nThe converted file is called "+convertedFile+" \n");
    }

     private RemoteFile downloadFileInAVirtualThread(String url) throws Exception {
        final var factory = Thread.ofVirtual().factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            return executor.submit(() -> downloadFile(url)).get();
        }
    }

    private RemoteFile downloadFile(String url) throws Exception {
        var request = getRequest(url);
        var inputStream = getClient().send(request, HttpResponse.BodyHandlers.ofInputStream()).body();
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
            inputStream.transferTo(byteStream);
            var content = byteStream.toByteArray();
            var path = Path.of(url);
            var contentType = Files.probeContentType(path);
            return getFileByContentType(content, contentType, path.getFileName().toString());
        }
    }

    private java.net.http.HttpRequest getRequest(String fileUrl) throws URISyntaxException {
        return java.net.http.HttpRequest.newBuilder()
                .uri(new URI(fileUrl))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
    }

    private HttpClient getClient() {
        return HttpClient.newBuilder().build();
    }

    private RemoteFile getFileByContentType(byte[] content, String contentType, String name) {
        switch (contentType) {
            case String type when type.contains(Type.PDF.mimeTerm) -> {
                return new PdfRemoteFile(content, name);
            }
            case String type when type.contains(Type.WORD.mimeTerm) -> {
                return new WordRemoteFile(content, name);
            }
            case String type when type.contains(Type.EXCEL.mimeTerm) -> {
                return new ExcelRemoteFile(content, name);
            }
            default -> throw new FileTypeException("Not supported file type");
        }
    }
}
