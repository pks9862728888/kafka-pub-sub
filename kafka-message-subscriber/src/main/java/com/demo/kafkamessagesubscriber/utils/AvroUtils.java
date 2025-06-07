package com.demo.kafkamessagesubscriber.utils;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public abstract class AvroUtils {

  public static <T extends SpecificRecord> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
    bytes = decompress(bytes);
    DatumReader<T> reader = new SpecificDatumReader<>(clazz);
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bis, null);
    return reader.read(null, binaryDecoder);
  }

  private static byte[] decompress(byte[] compressedBytes) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(compressedBytes);
    GZIPInputStream gzipInputStream = new GZIPInputStream(bis);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    IOUtils.copy(gzipInputStream, bos);
    gzipInputStream.close();
    return bos.toByteArray();
  }
}
