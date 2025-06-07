package com.demo.kafkamessagepublisher.controller;

import com.demo.kafkamessagepublisher.publisher.KafkaMessagePublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
@RequiredArgsConstructor
public class DataPublisherController {
  private final KafkaMessagePublisherService kafkaMessagePublisherService;

  @PostMapping("/test-message")
  public ResponseEntity<Boolean> publishMessage(@RequestParam("message") String message) {
    kafkaMessagePublisherService.sendMessage(message);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
