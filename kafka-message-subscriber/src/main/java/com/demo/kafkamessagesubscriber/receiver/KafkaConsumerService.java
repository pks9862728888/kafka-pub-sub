package com.demo.kafkamessagesubscriber.receiver;

import com.demo.avro.StringMessage;
import com.demo.kafkamessagesubscriber.utils.AvroUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {

  @KafkaListener(topics = "${topic.test.topic}")
  public void receiveMessage(byte[] message) throws IOException {
    StringMessage stringMsg = AvroUtils.deserialize(message, StringMessage.class);
    System.out.println("Received message: " + stringMsg);
  }
}
