package com.demo.kafkamessagepublisher.utils;

import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AvroUtils {

  public static byte[] getBytes(SpecificRecord message) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    SpecificDatumWriter<SpecificRecord> writer = new SpecificDatumWriter<>(message.getSchema());
    Encoder encoder = EncoderFactory.get().binaryEncoder(bos, null);
    writer.write(message, encoder);
    encoder.flush();
    bos.close();
    return bos.toByteArray();
  }
}
