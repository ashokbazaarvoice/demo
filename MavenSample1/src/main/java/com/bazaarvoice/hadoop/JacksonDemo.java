package com.bazaarvoice.hadoop;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/27/14
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class JacksonDemo {
    public static void main(String[] args) throws Exception{
        List< String > list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            array.put(list.get(i));
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("result", array);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(obj.toString());
//        Writer writer = new StringWriter();
//
//        JsonFactory jfactory = new JsonFactory();
//
//        JsonGenerator jsonGenerator = jfactory.createGenerator(writer);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.writeValue(jsonGenerator, obj);
//
//        jsonGenerator.close();
//        System.out.println(writer.toString());
    }
}
