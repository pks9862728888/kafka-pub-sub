package com.demo.kafkamessagesubscriber.receiver;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  @KafkaListener(topics = "${topic.test.topic}")
  public void receiveMessage(String message) {
    System.out.println("Received message: " + message);
  }
}
