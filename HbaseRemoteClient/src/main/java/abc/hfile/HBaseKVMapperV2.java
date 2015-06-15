package abc.hfile;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class HBaseKVMapperV2 extends TableMapper<ImmutableBytesWritable, KeyValue> {
    private static final Logger LOG = Logger.getLogger(HBaseKVMapperV2.class);

    ImmutableBytesWritable hKey = new ImmutableBytesWritable();
    KeyValue kv;

    public void setup(Context context) throws IOException, InterruptedException {

    }

    protected void cleanup(Context context) throws IOException {

    }

    public void map(ImmutableBytesWritable key, Result value, Context context)
            throws IOException, InterruptedException {
//        String clientProdId = keyToClientProdId(value);


//        byte[] clientId = value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.CLIENT_COL);
//        int hash = MurmurHash.getInstance().hash(clientId);
//        byte[] rowKey = Bytes.add(Bytes.toBytes(hash), Bytes.toBytes("/"), Bytes.toBytes(clientProdId));
//        // Put put = new Put(rowKey);
//
//        String sourceClientId = Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_CLIENT_COL));
//        String[] columns = {
//                sourceClientId,
//                Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_PRODUCT_ID_COL)),
//                Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.LEGACY_REVIEW_ID_COL)),
//                Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.CLIENT_COL)),
//                Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.PRODUCT_ID_COL)),
//                Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_EXTERNALID_COL))
//        };
//        //  put.add(Bytes.toBytes("cf"), Bytes.toBytes(columns[0]+"/"+columns[5]), Bytes.toBytes(columns[0]+"/"+columns[1]));
//
//        hKey.set(rowKey);
//
//        kv = new KeyValue(hKey.get(), SyndicatedReviewDAO.REVIEWS_CF,
//                Bytes.toBytes(columns[0]+"/"+columns[5]), Bytes.toBytes(columns[0]+"/"+columns[1]));

        context.write(hKey, kv);

    }

//    protected String keyToClientProdId(Result value) {
//        String destClientProdId = Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.CLIENT_COL))+"/"+Bytes.toString(value.getValue(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.EXTERNALID_COL));
//        return destClientProdId;    // use clientid as clientKey
//    }
}
