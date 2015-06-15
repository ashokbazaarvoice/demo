package abc;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 8/6/14
 * Time: 5:46 PM
 *
 * The mapper below is used for finding frequency of first name.
 */
public class MyTableMapper extends TableMapper<Text, IntWritable> {

    public static final byte[] COL_FAMILY = "CF".getBytes();
    public static final byte[] FIRST_NAME_COL_QUALIFIER = "fn".getBytes();
    public static final byte[] MIDDLE_NAME_COL_QUALIFIER = "mi".getBytes();
    public static final byte[] LAST_NAME_COL_QUALIFIER = "ln".getBytes();

    public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {

        String rowKey = new String(row.get());
        String[] keyParts = rowKey.split("/");

        String firstName = Bytes.toString(value.getValue(COL_FAMILY, FIRST_NAME_COL_QUALIFIER));
        String middleName = Bytes.toString(value.getValue(COL_FAMILY, MIDDLE_NAME_COL_QUALIFIER));
        String lastName = Bytes.toString(value.getValue(COL_FAMILY, LAST_NAME_COL_QUALIFIER));

        context.write(new Text(firstName), new IntWritable(1));
    }
}
