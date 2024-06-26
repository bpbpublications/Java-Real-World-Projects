package dev.davivieira.service;

import dev.davivieira.entity.File;
import dev.davivieira.metrics.FileMetric;
import dev.davivieira.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final FileMetric fileMetric;

   @Autowired
    public FileService(FileRepository fileRepository, FileMetric fileMetric) {
        this.fileRepository = fileRepository;
        this.fileMetric = fileMetric;
    }

    public void uploadFile(File file) {
        fileMetric.fileUploadTimer(file.getName()).record(
                () -> {
                    try {
                        Thread.sleep(new Random().nextInt(5000 - 1000 + 1) + 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    fileRepository.save(file);
                }
        );
    }

    public Optional<File> downloadFile(String id) {
        return fileRepository.findById(id);
    }
}
