package mba.usp.distributed.architecture.notification_service.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import mba.usp.distributed.architecture.notification_service.model.Notification;
import mba.usp.distributed.architecture.notification_service.service.AlertNotificationProcessor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AlertNotificationListener {

    private final AlertNotificationProcessor processor;

    public AlertNotificationListener(ObjectMapper objectMapper, AlertNotificationProcessor processor) {
        this.processor = processor;
    }
    @Timed(value = "notification.handle.message", description = "Tempo para notificar alertas")
    @RabbitListener(queues = "notification.data.queue")
    public void handleSensorData(Notification message) {
        try {
            System.out.println(message);
            processor.process(message);
        } catch (Exception e) {
            System.err.println("Falha ao processar mensagem: " + e.getMessage());
        }
    }
}
