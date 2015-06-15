package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.Filter.ReturnCode;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/21/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockHTable {
    /**
     * This is all the data for a MockHTable instance
     */
    private NavigableMap<byte[], NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>>> data = new TreeMap<byte[], NavigableMap<byte[],NavigableMap<byte[],NavigableMap<Long,byte[]>>>>(Bytes.BYTES_COMPARATOR);

    /**
     * Helper method to convert some data into a list of KeyValue's
     *
     * @param row
     *          row value of the KeyValue's
     * @param rowdata
     *          data to decode
     * @param maxVersions
     *          number of versions to return
     * @return List of KeyValue's
     */
    private static List<KeyValue> toKeyValue(byte[] row, NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> rowdata, int maxVersions){
        return toKeyValue(row, rowdata, 0, Long.MAX_VALUE, maxVersions);
    }

    /**
     * Helper method to convert some data into a list of KeyValue's with timestamp
     * constraint
     *
     * @param row
     *          row value of the KeyValue's
     * @param rowdata
     *          data to decode
     * @param timestampStart
     *          start of the timestamp constraint
     * @param timestampEnd
     *          end of the timestamp constraint
     * @param maxVersions
     *          number of versions to return
     * @return List of KeyValue's
     */
    private static List<KeyValue> toKeyValue(byte[] row, NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> rowdata, long timestampStart, long timestampEnd, int maxVersions){
        List<KeyValue> ret = new ArrayList<KeyValue>();
        for (byte[] family : rowdata.keySet())
            for (byte[] qualifier : rowdata.get(family).keySet()) {
                int versionsAdded = 0;
                for (Entry<Long, byte[]> tsToVal : rowdata.get(family).get(qualifier).descendingMap().entrySet()){
                    if (versionsAdded++ == maxVersions)
                        break;
                    Long timestamp = tsToVal.getKey();
                    if (timestamp < timestampStart)
                        continue;
                    if (timestamp > timestampEnd)
                        continue;
                    byte[] value = tsToVal.getValue();
                    ret.add(new KeyValue(row, family, qualifier, timestamp, value));
                }
            }
        return ret;
    }

    /**
     * Clients should not rely on table names so this returns null.
     * @return null
     */

    public byte[] getTableName() { return null; }

    /**
     * No configuration needed to work so this returns null.
     * @return null
     */

    public Configuration getConfiguration() { return null; }

    /**
     * No table descriptor needed so this returns null.
     * @return null
     */

    public HTableDescriptor getTableDescriptor() { return null; }

    public boolean exists(Get get) throws IOException {
        if(get.getFamilyMap() == null || get.getFamilyMap().size() == 0) {
            return data.containsKey(get.getRow());
        } else {
            byte[] row = get.getRow();
            if(!data.containsKey(row)) {
                return false;
            }
            for(byte[] family : get.getFamilyMap().keySet()) {
                if(!data.get(row).containsKey(family)) {
                    return false;
                } else {
                    for(byte[] qualifier : get.getFamilyMap().get(family)) {
                        if(!data.get(row).get(family).containsKey(qualifier)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }


    public Result get(Get get) throws IOException {
        if (!data.containsKey(get.getRow()))
            return new Result();
        byte[] row = get.getRow();
        List<KeyValue> kvs = new ArrayList<KeyValue>();
        if (!get.hasFamilies()) {
            kvs = toKeyValue(row, data.get(row), get.getMaxVersions());
        } else {
            for (byte[] family : get.getFamilyMap().keySet()){
                if (data.get(row).get(family) == null)
                    continue;
                NavigableSet<byte[]> qualifiers = get.getFamilyMap().get(family);
                if (qualifiers == null || qualifiers.isEmpty())
                    qualifiers = data.get(row).get(family).navigableKeySet();
                for (byte[] qualifier : qualifiers){
                    if (qualifier == null)
                        qualifier = "".getBytes();
                    if (!data.get(row).containsKey(family) ||
                            !data.get(row).get(family).containsKey(qualifier) ||
                            data.get(row).get(family).get(qualifier).isEmpty())
                        continue;
                    Entry<Long, byte[]> timestampAndValue = data.get(row).get(family).get(qualifier).lastEntry();
                    kvs.add(new KeyValue(row,family, qualifier, timestampAndValue.getKey(), timestampAndValue.getValue()));
                }
            }
        }
        Filter filter = get.getFilter();
        if (filter != null) {
            filter.reset();
            List<KeyValue> nkvs = new ArrayList<KeyValue>(kvs.size());
            for (KeyValue kv : kvs) {
                if (filter.filterAllRemaining()) {
                    break;
                }
                if (filter.filterRowKey(kv.getBuffer(), kv.getRowOffset(), kv.getRowLength())) {
                    continue;
                }
                if (filter.filterKeyValue(kv) == ReturnCode.INCLUDE) {
                    nkvs.add(kv);
                }
                // ignoring next key hint which is a optimization to reduce file system IO
            }
            if (filter.hasFilterRow()) {
                filter.filterRow(nkvs);
            }
            kvs = nkvs;
        }

        return new Result(kvs);
    }


    public ResultScanner getScanner(Scan scan) throws IOException {
        final List<Result> ret = new ArrayList<Result>();
        byte[] st = scan.getStartRow();
        byte[] sp = scan.getStopRow();
        Filter filter = scan.getFilter();

        for (byte[] row : data.keySet()){
            // if row is equal to startRow emit it. When startRow (inclusive) and
            // stopRow (exclusive) is the same, it should not be excluded which would
            // happen w/o this control.
            if (st != null && st.length > 0 &&
                    Bytes.BYTES_COMPARATOR.compare(st, row) != 0) {
                // if row is before startRow do not emit, pass to next row
                if (st != null && st.length > 0 &&
                        Bytes.BYTES_COMPARATOR.compare(st, row) > 0)
                    continue;
                // if row is equal to stopRow or after it do not emit, stop iteration
                if (sp != null && sp.length > 0 &&
                        Bytes.BYTES_COMPARATOR.compare(sp, row) <= 0)
                    break;
            }

            List<KeyValue> kvs = null;
            if (!scan.hasFamilies()) {
                kvs = toKeyValue(row, data.get(row), scan.getTimeRange().getMin(), scan.getTimeRange().getMax(), scan.getMaxVersions());
            } else {
                kvs = new ArrayList<KeyValue>();
                for (byte[] family : scan.getFamilyMap().keySet()){
                    if (data.get(row).get(family) == null)
                        continue;
                    NavigableSet<byte[]> qualifiers = scan.getFamilyMap().get(family);
                    if (qualifiers == null || qualifiers.isEmpty())
                        qualifiers = data.get(row).get(family).navigableKeySet();
                    for (byte[] qualifier : qualifiers){
                        if (data.get(row).get(family).get(qualifier) == null)
                            continue;
                        for (Long timestamp : data.get(row).get(family).get(qualifier).descendingKeySet()){
                            if (timestamp < scan.getTimeRange().getMin())
                                continue;
                            if (timestamp > scan.getTimeRange().getMax())
                                continue;
                            byte[] value = data.get(row).get(family).get(qualifier).get(timestamp);
                            kvs.add(new KeyValue(row, family, qualifier, timestamp, value));
                            if(kvs.size() == scan.getMaxVersions()) {
                                break;
                            }
                        }
                    }
                }
            }
            if (filter != null) {
                filter.reset();
                List<KeyValue> nkvs = new ArrayList<KeyValue>(kvs.size());
                for (KeyValue kv : kvs) {
                    if (filter.filterAllRemaining()) {
                        break;
                    }
                    if (filter.filterRowKey(kv.getBuffer(), kv.getRowOffset(), kv.getRowLength())) {
                        continue;
                    }
                    ReturnCode filterResult = filter.filterKeyValue(kv);
                    if (filterResult == ReturnCode.INCLUDE) {
                        nkvs.add(kv);
                    } else if (filterResult == ReturnCode.NEXT_ROW) {
                        break;
                    }
                    // ignoring next key hint which is a optimization to reduce file system IO
                }
                if (filter.hasFilterRow()) {
                    filter.filterRow(nkvs);
                }
                kvs = nkvs;
            }
            if (!kvs.isEmpty()) {
                ret.add(new Result(kvs));
            }
        }

        return new ResultScanner() {
            private final Iterator<Result> iterator = ret.iterator();
            public Iterator<Result> iterator() {
                return iterator;
            }
            public Result[] next(int nbRows) throws IOException {
                ArrayList<Result> resultSets = new ArrayList<Result>(nbRows);
                for(int i = 0; i < nbRows; i++) {
                    Result next = next();
                    if (next != null) {
                        resultSets.add(next);
                    } else {
                        break;
                    }
                }
                return resultSets.toArray(new Result[resultSets.size()]);
            }
            public Result next() throws IOException {
                try {
                    return iterator().next();
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
            public void close() {}
        };
    }


    public void put(Put put) throws IOException {
        byte[] row = put.getRow();
        NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> rowData = forceFind(data, row, new TreeMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>>(Bytes.BYTES_COMPARATOR));
        for (byte[] family : put.getFamilyMap().keySet()){
            NavigableMap<byte[], NavigableMap<Long, byte[]>> familyData = forceFind(rowData, family, new TreeMap<byte[], NavigableMap<Long, byte[]>>(Bytes.BYTES_COMPARATOR));
            for (KeyValue kv : put.getFamilyMap().get(family)){
                kv.updateLatestStamp(Bytes.toBytes(System.currentTimeMillis()));
                byte[] qualifier = kv.getQualifier();
                NavigableMap<Long, byte[]> qualifierData = forceFind(familyData, qualifier, new TreeMap<Long, byte[]>());
                qualifierData.put(kv.getTimestamp(), kv.getValue());
            }
        }
    }

    /**
     * Helper method to find a key in a map. If key is not found, newObject is
     * added to map and returned
     *
     * @param map
     *          map to extract value from
     * @param key
     *          key to look for
     * @param newObject
     *          set key to this if not found
     * @return found value or newObject if not found
     */
    private <K, V> V forceFind(NavigableMap<K, V> map, K key, V newObject){
        V data = map.get(key);
        if (data == null){
            data = newObject;
            map.put(key, data);
        }
        return data;
    }

    public void put(List<Put> puts) throws IOException {
        for (Put put : puts)
            put(put);
    }

    /**
     * Checks if the value with given details exists in database, or is
     * non-existent in the case of value being null
     *
     * @param row
     *          row
     * @param family
     *          family
     * @param qualifier
     *          qualifier
     * @param value
     *          value
     * @return true if value is not null and exists in db, or value is null and
     *         not exists in db, false otherwise
     */
    private boolean check(byte[] row, byte[] family, byte[] qualifier, byte[] value){
        if (value == null || value.length == 0)
            return ! data.containsKey(row) ||
                    ! data.get(row).containsKey(family) ||
                    ! data.get(row).get(family).containsKey(qualifier);
        else
            return data.containsKey(row) &&
                    data.get(row).containsKey(family) &&
                    data.get(row).get(family).containsKey(qualifier) &&
                    ! data.get(row).get(family).get(qualifier).isEmpty() &&
                    Arrays.equals(data.get(row).get(family).get(qualifier).lastEntry().getValue(), value);
    }


    private MockHTable(){}

    /**
     * Default way of constructing a MockHTable
     * @return a new MockHTable
     */
    public static MockHTable create(){
        return new MockHTable();
    }

    /**
     * Create a MockHTable with some pre-loaded data. Parameter should be a map of
     * column-to-data mappings of rows. It can be created with a YAML like
     *
     * <pre>
     * rowid:
     *   family1:qualifier1: value1
     *   family2:qualifier2: value2
     * </pre>
     *
     * @param dump
     *          pre-loaded data
     * @return a new MockHTable loaded with given data
     */
    public static MockHTable with(Map<String, Map<String, String>> dump){
        MockHTable ret = new MockHTable();
        for (String row : dump.keySet()){
            for (String column : dump.get(row).keySet()){
                String val = dump.get(row).get(column);
                put(ret, row, column, val);
            }
        }
        return ret;
    }

    /**
     * Helper method of pre-loaders, adds parameters to data.
     *
     * @param ret
     *          data to load into
     * @param row
     *          rowid
     * @param column
     *          family:qualifier encoded value
     * @param val
     *          value
     */
    private static void put(MockHTable ret, String row, String column,
                            String val) {
        String[] fq = split(column);
        byte[] family = Bytes.toBytesBinary(fq[0]);
        byte[] qualifier = Bytes.toBytesBinary(fq[1]);
        NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> families = ret.forceFind(ret.data, Bytes.toBytesBinary(row), new TreeMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>>(Bytes.BYTES_COMPARATOR));
        NavigableMap<byte[], NavigableMap<Long, byte[]>> qualifiers = ret.forceFind(families, family, new TreeMap<byte[], NavigableMap<Long, byte[]>>(Bytes.BYTES_COMPARATOR));
        NavigableMap<Long, byte[]> values = ret.forceFind(qualifiers, qualifier, new TreeMap<Long, byte[]>());
        values.put(System.currentTimeMillis(), Bytes.toBytesBinary(val));
    }


    /**
     * Column identification helper
     *
     * @param column
     *          column name in the format family:qualifier
     * @return <code>{"family", "qualifier"}</code>
     */
    private static String[] split(String column){
        return new String[]{
                column.substring(0, column.indexOf(':')),
                column.substring(column.indexOf(':')+1)};
    }




    
}

