package com.demo.kafkamessagesubscriber.utils;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AvroUtils {

  public static <T extends SpecificRecord> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
    DatumReader<T> reader = new SpecificDatumReader<>(clazz);
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bis, null);
    return reader.read(null, binaryDecoder);
  }
}
