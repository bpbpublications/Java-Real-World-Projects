package dev.davivieira.service;

import dev.davivieira.entity.File;
import dev.davivieira.metrics.FileMetric;
import dev.davivieira.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                () -> fileRepository.save(file)
        );
    }

    public Optional<File> downloadFile(String id) {
        return fileRepository.findById(id);
    }
}
