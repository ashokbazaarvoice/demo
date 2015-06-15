package com.bazaarvoice.bytelevel;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ashok.agarwal on 6/5/15.
 */
public class RawComparatorDemo {
    public static void main(String[] args) throws Exception {
        RawComparator<IntWritable> comparator = WritableComparator.get(IntWritable.class);
        IntWritable w1 = new IntWritable(163);
        IntWritable w2 = new IntWritable(67);
        assertEquals(comparator.compare(w1, w2), 1); // 1 means w1 > w2

        byte[] b1 = HadoopSerializationHelper.serialize(w1);
        byte[] b2 = HadoopSerializationHelper.serialize(w2);
        assertEquals(comparator.compare(b1, 0, b1.length, b2, 0, b2.length), 1);

        String str = new String("hadoop");
        System.out.println(str.charAt(2)); // Note charAt returns char

        Text t = new Text("hadoop");
        System.out.println(t.charAt(2)); // Note the difference the charAt returns int
    }
}
// WritableComparator is a general-purpose implementation of RawComparator for WritableComparable classes.