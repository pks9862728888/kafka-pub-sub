package com.demo.kafkamessagepublisher.publisher;

import com.demo.avro.StringMessage;
import com.demo.kafkamessagepublisher.utils.AvroUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisherService {
  private final KafkaMessageSender kafkaMessageSender;

  @Value("${topic.test.topic}")
  private String topic;

  public void sendMessage(String message) throws IOException {
    StringMessage stringMessage = buildStringMessage(message);
    byte[] compressedMsg = AvroUtils.getBytes(stringMessage);
    kafkaMessageSender.sendMessage(compressedMsg, UUID.randomUUID().toString(), topic);
  }

  private StringMessage buildStringMessage(String message) {
    return StringMessage.newBuilder()
        .setMessage(message)
        .build();
  }
}
