package com.demo.kafkamessagepublisher.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisherService {
  private final KafkaMessageSender kafkaMessageSender;

  @Value("${topic.test.topic}")
  private String topic;

  public void sendMessage(String message) {
    kafkaMessageSender.sendMessage(message, UUID.randomUUID().toString(), topic);
  }
}
