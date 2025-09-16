package mba.usp.distributed.architecture.notification_service.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import mba.usp.distributed.architecture.notification_service.model.Notification;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AlertNotificationProcessor {

    private final MeterRegistry registry;

    public AlertNotificationProcessor(MeterRegistry meterRegistry) {
        this.registry = meterRegistry;

    }

    public void process(Notification alert) {
        System.out.printf(
                "🔔 [Alert] Sensor %s @ %s: %s%n",
                alert.getSensorId(),
                alert.getTimestamp(),
                alert.getMessage()
        );

        long startMillis = (long) alert.getStartProcessingTimestamp();
        long nowMillis = System.currentTimeMillis();
        long durationMillis = nowMillis - startMillis;

        Timer.builder("processing_time")
                .description("Total time taken from ingestion to completion")
                .tags("service", "microservices")
                .register(registry)
                .record(durationMillis, TimeUnit.MILLISECONDS);
    }

}
