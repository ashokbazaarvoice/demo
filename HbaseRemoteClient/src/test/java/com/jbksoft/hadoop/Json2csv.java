package com.jbksoft.hadoop;

/**
 * Created by ashok.agarwal on 3/7/14.
 */
public class Json2csv {
    public static void main(String[] args) {
        String json2p2r = "{\"displayClient\": \"avon-es\",\n" +
                "  \"products\": [\n" +
                "  {\"displayProduct\": {\"externalId\":\"123\",\"~id\":\"product::46869\",\"~table\":\"catalog:avon-es:\",\"attribute.NAME\":\"Sweet Simplicity Slipper\",\"category.attribute.NAME\":\"calzado en oferta\"},\n" +
                "   \"reviews\": [\n" +
                "      {\"sourceProduct\":{\"client\":\"avon\",\"externalId\":\"456\",\"~table\":\"catalog:avon:\",\"~id\":\"product::96864\"},\"~table\":\"review:avon\",\"~id\":\"bac7a321-f639-5cfc-bb03-325bc44e7ca1\",\"rating\":5,\"reviewId\":27186534}\n" +
                "   ],\n" +
                "   \"stats\": {\"organicReviewCount\":0,\"organicReviewRating\":\"NaN\",\"syndicatedReviewCount\":1,\"syndicatedReviewRating\":5.0}\n" +
                ",  \"matches\": [{\"client\":\"avon\",\"externalId\":\"46869\",\"~id\":\"product::46869\",\"~table\":\"catalog:avon:\"}]" +
                "  }\n" +
                ", {\"displayProduct\": {\"externalId\":\"456\",\"~id\":\"product::456\",\"~table\":\"catalog:avon-es:\",\"attribute.NAME\":\"Sour Simplicity Slipper\",\"category.attribute.NAME\":\"calzado en oferta\"},\n" +
                "   \"reviews\": [\n" +
                "      {\"sourceProduct\":{\"client\":\"avon-es\",\"externalId\":\"456\",\"~table\":\"catalog:avon-es:\",\"~id\":\"product::456\"},\"~table\":\"review:avon-es\",\"~id\":\"bac7a321-f639-5cfc-bb03-325bc44e7456\",\"rating\":3,\"reviewId\":27186456}\n" +
                "   ],\n" +
                "   \"stats\": {\"organicReviewCount\":1,\"organicReviewRating\":3.0,\"syndicatedReviewCount\":0,\"syndicatedReviewRating\":\"NaN\"}\n" +
                ",  \"matches\": []" +
                "  }\n" +
                "]}";


        String csvReviews2p2r1 = "\"2014-02-20\",\"avon-es\",\"catalog:avon-es:\",\"product::46869\",\"123\",\"Sweet Simplicity Slipper\",\"calzado en oferta\",\"avon\",\"456\",\"catalog:avon:\",\"product::96864\",\"27186534\",\"5\",\"review:avon\",\"bac7a321-f639-5cfc-bb03-325bc44e7ca1\"";
        String csvReviews2p2r2 = "\"2014-02-20\",\"avon-es\",\"catalog:avon-es:\",\"product::456\",\"456\",\"Sour Simplicity Slipper\",\"calzado en oferta\",\"avon-es\",\"456\",\"catalog:avon-es:\",\"product::456\",\"27186456\",\"3\",\"review:avon-es\",\"bac7a321-f639-5cfc-bb03-325bc44e7456\"\n";

        String csvStats2p2r =
                "\"2014-02-20\",\"avon-es\",\"catalog:avon-es:\",\"product::46869\",\"123\",\"Sweet Simplicity Slipper\",\"calzado en oferta\",\"0\",\"NaN\",\"1\",\"5.0\"\n" +
                        "\"2014-02-20\",\"avon-es\",\"catalog:avon-es:\",\"product::456\",\"456\",\"Sour Simplicity Slipper\",\"calzado en oferta\",\"1\",\"3.0\",\"0\",\"NaN\"\n";

        System.out.println(json2p2r);
        System.out.println(csvReviews2p2r1);
        System.out.println(csvStats2p2r);
    }
}
