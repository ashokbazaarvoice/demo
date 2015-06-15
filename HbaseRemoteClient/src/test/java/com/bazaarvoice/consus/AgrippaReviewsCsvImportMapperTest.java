package com.bazaarvoice.consus;

//import com.bazaarvoice.consus.agrippaimport.AgrippaReviewsCsvImportMapper;

/**
 * Created by ashok.agarwal on 3/7/14.
 */
public class AgrippaReviewsCsvImportMapperTest {
//    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
//    HBaseTestingUtility utility;
//    HBasePersister persister;
//    HTablePool pool;
//    Configuration config;
//    HTableInterface reviewStatsTable;
//    HTableInterface reviewsTable;
//    HBaseSchema hBaseSchema;
//    HConnection connection;
//
//    @Before
//    public void setUp() throws Exception {
//        utility = new HBaseTestingUtility();
//        utility.startMiniCluster();
//        config = utility.getConfiguration();
//        pool = new HTablePool(config, 4);
//        config.set("reviewStatsTable","consus_reviewstats");
//        config.set("reviewsTable","consus_reviews");
//        config.set("exportStatsTable","consus_exportstats");
//        config.set("importStatsTable","consus_importstats");
//        connection = HConnectionManager.createConnection(config);
//        hBaseSchema = new HBaseSchema(config,connection);
//        assertEquals(config, hBaseSchema.config);
//        hBaseSchema.createTables();
//        persister = new HBasePersister(pool, config);
//        AgrippaReviewsCsvImportMapper mapper = new AgrippaReviewsCsvImportMapper();
//        mapper.setPersister(persister);
//        mapDriver = MapDriver.newMapDriver(mapper);
//        mapDriver.setConfiguration(config);
//    }
//
//    @Test
//    public void testMapperWithSingleKeyAndValueWithAssertion() throws Exception {
//        final LongWritable inputKey = new LongWritable(0);
//        String csvReviews2p2r1 = "\"2014-02-20\",\"avon-es\",\"catalog:avon-es:\",\"product::46869\",\"123\",\"Sweet Simplicity Slipper\",\"calzado en oferta\",\"avon\",\"456\",\"catalog:avon:\",\"product::96864\",\"27186534\",\"5\",\"review:avon\",\"bac7a321-f639-5cfc-bb03-325bc44e7ca1\"";
//        final Text inputValue = new Text();
//        inputValue.set(csvReviews2p2r1);
//        final Text outputKey = new Text("reviews/avon-es/2014-02-20");
//        final IntWritable outputValue = new IntWritable(1);
//
//        mapDriver.withInput(inputKey, inputValue);
//        final List<Pair<Text, IntWritable>> result = mapDriver.run();
//
//        reviewStatsTable = persister.reviewStatsTable;
//        reviewsTable = persister.reviewsTable;
//
//        reviewStatsTable.flushCommits();
//        reviewsTable.flushCommits();
//
//        Scan scan = persister.scanForClient("avon-es");
//        ResultScanner scanner = reviewStatsTable.getScanner(scan);
//        try {
//            for (Result scannerResult: scanner) {
//                for(KeyValue kv : scannerResult.raw()){
//                    System.out.print(new String(kv.getRow()) + " ");
//                    System.out.print(new String(kv.getFamily()) + ":");
//                    System.out.print(new String(kv.getQualifier()) + " ");
//                    System.out.print(kv.getTimestamp() + " ");
//                    System.out.println(new String(kv.getValue()));
//                }
//
//            }
//        } finally {
//            scanner.close();
//        }
//
//        Get get1 = new Get(Bytes.toBytes("avon-es/46869/2014-01-01"));
//        Result result1 = reviewsTable.get(get1);
//       assertEquals(Bytes.toString(result1.getRow()), "avon-es/46869/2014-01-01");
//
//        Scan scan2 = persister.scanForClient("avon-es");
//        ResultScanner scanner2 = reviewsTable.getScanner(scan2);
//        try {
//            for (Result scannerResult: scanner2) {
//                for(KeyValue kv : scannerResult.raw()){
//                    System.out.print(new String(kv.getRow()) + " ");
//                    System.out.print(new String(kv.getFamily()) + ":");
//                    System.out.print(new String(kv.getQualifier()) + " ");
//                    System.out.print(kv.getTimestamp() + " ");
//                    System.out.println(new String(kv.getValue()));
//                }
//
//            }
//        } finally {
//            scanner2.close();
//        }
//
//
//        assertEquals((String)result.get(0).getFirst().toString(), "reviews/avon-es/2014-02-20");
//    }

}
