package com.abc.copy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/17/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonTest {

    public static void main(String[] args){
        String json1p2r ="{\"displayClient\": \"avon-es\",\n" +
                "  \"products\": [\n" +
                "  {\"displayProduct\": {\"externalId\":\"46869\",\"~id\":\"product::46869\",\"~table\":\"catalog:avon-es:\",\"attribute.NAME\":\"Sweet Simplicity Slipper\",\"category.attribute.NAME\":\"calzado en oferta\"},\n" +
                "   \"reviews\": [\n" +
                "      {\"sourceProduct\":{\"client\":\"avon\",\"externalId\":\"46869\",\"~table\":\"catalog:avon:\",\"~id\":\"product::46869\"},\"~table\":\"review:avon\",\"~id\":\"bac7a321-f639-5cfc-bb03-325bc44e7ca1\",\"rating\":5,\"reviewId\":27186534},\n" +
                "      {\"sourceProduct\":{\"client\":\"avon\",\"externalId\":\"46869\",\"~table\":\"catalog:avon:\",\"~id\":\"product::46869\"},\"~table\":\"review:avon\",\"~id\":\"25dff638-26b4-5201-8217-0f692af7742b\",\"rating\":3,\"reviewId\":27107186}\n" +
                "   ],\n" +
                "   \"stats\": {\"organicReviewCount\":0,\"organicReviewRating\":\"NaN\",\"syndicatedReviewCount\":2,\"syndicatedReviewRating\":4.0},\n" +
                "   \"matches\": [{\"client\":\"avon\",\"externalId\":\"46869\",\"~id\":\"product::46869\",\"~table\":\"catalog:avon:\"}]\n" +
                "  }\n" +
                "  ]\n" +
                "}";
        System.out.println(json1p2r);
        System.out.println(json1p2r.length());
        System.out.println(json1p2r.substring(908));
    }
}
