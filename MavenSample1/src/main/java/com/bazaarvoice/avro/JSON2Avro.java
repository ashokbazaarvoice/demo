package com.bazaarvoice.avro;

import java.io.*;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
/**
 * Created by ashok.agarwal on 5/29/15.
 */
public class JSON2Avro {
    public static void main(String[] args) throws IOException {
        String schema = "{\n" +
                "        \"type\":\"record\",\n" +
                "                \"namespace\":\"foo\",\n" +
                "                \"name\":\"Person\",\n" +
                "                \"fields\":[\n" +
                "        {\n" +
                "            \"name\":\"name\",\n" +
                "                \"type\":\"string\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"age\",\n" +
                "                \"type\":\"int\"\n" +
                "        }\n" +
                "        ]\n" +
                "    }";

        String json = "{" +
                "\"name\":\"Frank\"," +
                "\"age\":47" +
                "}";

        byte[] b = jsonToAvro(json,schema);
        System.out.println(avroToJson(b,schema));

    }

    public static byte[] jsonToAvro(String json, String schemaStr) throws IOException {
        InputStream input = null;
        GenericDatumWriter<GenericRecord> writer = null;
        Encoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            Schema schema = new Schema.Parser().parse(schemaStr);
            DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
            input = new ByteArrayInputStream(json.getBytes());
            output = new ByteArrayOutputStream();
            DataInputStream din = new DataInputStream(input);
            writer = new GenericDatumWriter<GenericRecord>(schema);
            Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
            encoder = EncoderFactory.get().binaryEncoder(output, null);
            GenericRecord datum;
            while (true) {
                try {
                    datum = reader.read(null, decoder);
                } catch (EOFException eofe) {
                    break;
                }
                writer.write(datum, encoder);
            }
            encoder.flush();
            return output.toByteArray();
        } finally {
            try { input.close(); } catch (Exception e) { }
        }
    }


    public static String avroToJson(byte[] avro, String schemaStr) throws IOException {
        boolean pretty = false;
        GenericDatumReader<GenericRecord> reader = null;
        JsonEncoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            Schema schema = new Schema.Parser().parse(schemaStr);
            reader = new GenericDatumReader<GenericRecord>(schema);
            InputStream input = new ByteArrayInputStream(avro);
            output = new ByteArrayOutputStream();
            DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
            encoder = EncoderFactory.get().jsonEncoder(schema, output);
            Decoder decoder = DecoderFactory.get().binaryDecoder(input, null);
            GenericRecord datum;
            while (true) {
                try {
                    datum = reader.read(null, decoder);
                } catch (EOFException eofe) {
                    break;
                }
                writer.write(datum, encoder);
            }
            encoder.flush();
            output.flush();
            return new String(output.toByteArray());
        } finally {
            try { if (output != null) output.close(); } catch (Exception e) { }
        }
    }

}
