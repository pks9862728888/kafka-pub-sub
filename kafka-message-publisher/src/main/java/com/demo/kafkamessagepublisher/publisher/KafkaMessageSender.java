package com.demo.kafkamessagepublisher.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSender {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message, String key, String topic) {
    log.info("Sending message to topic: {}, key: {}", topic, key);
    CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, key, message);
    sendResult.thenRun(() -> log.info("Sent message to topic: {}, key: {}", topic, key));
  }
}
