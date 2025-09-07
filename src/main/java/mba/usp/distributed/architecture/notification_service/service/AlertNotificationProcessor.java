package mba.usp.distributed.architecture.notification_service.service;

import mba.usp.distributed.architecture.notification_service.model.Notification;
import org.springframework.stereotype.Service;

@Service
public class AlertNotificationProcessor {

    public void process(Notification alert) {
        System.out.printf(
                "🔔 [Alert] Sensor %s @ %s: %s%n",
                alert.getSensorId(),
                alert.getTimestamp(),
                alert.getMessage()
        );
    }

}
