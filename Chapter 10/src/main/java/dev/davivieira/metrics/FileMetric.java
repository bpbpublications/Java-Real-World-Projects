package dev.davivieira.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileMetric {

    private final MeterRegistry meterRegistry;

    @Autowired
    public FileMetric(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public Counter requestCounter(String method, String path) {
        return Counter
                .builder("file.http.request")
                .description("HTTP request")
                .tags("method", method)
                .tags("path", path)
                .register(meterRegistry);
    }

    public Timer fileUploadTimer(String fileName) {
        return Timer
                .builder("file.upload.duration")
                .description("File Upload Duration")
                .tags("fileName", fileName)
                .register(meterRegistry);
    }

    public DistributionSummary fileDownloadSizeSummary(String fileName) {
        return DistributionSummary
                .builder("file.download.size")
                .baseUnit("bytes")
                .description("File Download Size")
                .tags("fileName", fileName)
                .register(meterRegistry);
    }
}
