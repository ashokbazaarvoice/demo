package com.jbksoft.hadoop;

/**
 * Created by ashok.agarwal on 5/13/15.
 */
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.fs.Path;

import parquet.hadoop.ParquetWriter;
import parquet.hadoop.api.WriteSupport;
import parquet.hadoop.metadata.CompressionCodecName;
import parquet.schema.MessageType;

import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;

import parquet.column.ColumnDescriptor;
import parquet.hadoop.api.WriteSupport;
import parquet.io.ParquetEncodingException;
import parquet.io.api.Binary;
import parquet.io.api.RecordConsumer;
import parquet.schema.MessageType;

public class CsvParquetWriter extends ParquetWriter<List<String>> {

    public CsvParquetWriter(Path file, MessageType schema) throws IOException {
        this(file, schema, false);
    }

    public CsvParquetWriter(Path file, MessageType schema, boolean enableDictionary) throws IOException {
        this(file, schema, CompressionCodecName.UNCOMPRESSED, enableDictionary);
    }

    public CsvParquetWriter(Path file, MessageType schema, CompressionCodecName codecName, boolean enableDictionary) throws IOException {
        super(file, (WriteSupport<List<String>>) new CsvWriteSupport(schema), codecName, DEFAULT_BLOCK_SIZE, DEFAULT_PAGE_SIZE, enableDictionary, false);
    }
}
class CsvWriteSupport extends WriteSupport<List<String>> {
    MessageType schema;
    RecordConsumer recordConsumer;
    List<ColumnDescriptor> cols;

    // TODO: support specifying encodings and compression
    public CsvWriteSupport(MessageType schema) {
        this.schema = schema;
        this.cols = schema.getColumns();
    }

    @Override
    public WriteContext init(Configuration config) {
        return new WriteContext(schema, new HashMap<String, String>());
    }

    @Override
    public void prepareForWrite(RecordConsumer r) {
        recordConsumer = r;
    }

    @Override
    public void write(List<String> values) {
        if (values.size() != cols.size()) {
            throw new ParquetEncodingException("Invalid input data. Expecting " +
                    cols.size() + " columns. Input had " + values.size() + " columns (" + cols + ") : " + values);
        }

        recordConsumer.startMessage();
        for (int i = 0; i < cols.size(); ++i) {
            String val = values.get(i);
            // val.length() == 0 indicates a NULL value.
            if (val.length() > 0) {
                recordConsumer.startField(cols.get(i).getPath()[0], i);
                switch (cols.get(i).getType()) {
                    case BOOLEAN:
                        recordConsumer.addBoolean(Boolean.parseBoolean(val));
                        break;
                    case FLOAT:
                        recordConsumer.addFloat(Float.parseFloat(val));
                        break;
                    case DOUBLE:
                        recordConsumer.addDouble(Double.parseDouble(val));
                        break;
                    case INT32:
                        recordConsumer.addInteger(Integer.parseInt(val));
                        break;
                    case INT64:
                        recordConsumer.addLong(Long.parseLong(val));
                        break;
                    case BINARY:
                        recordConsumer.addBinary(stringToBinary(val));
                        break;
                    default:
                        throw new ParquetEncodingException(
                                "Unsupported column type: " + cols.get(i).getType());
                }
                recordConsumer.endField(cols.get(i).getPath()[0], i);
            }
        }
        recordConsumer.endMessage();
    }

    private Binary stringToBinary(Object value) {
        return Binary.fromString(value.toString());
    }
}