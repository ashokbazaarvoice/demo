package com.jbksoft.hadoop;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/26/14
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class commaTest {
    public static void main(String[] args) throws Exception{
//        String[] names = {"A", "B", "c", "d", "e" };
//        for (String str : names){
//            System.out.println(str+",");
//        }
//        String last = null;
//        for (String str : names){
//            if(last==null){
//                last=str;
//                System.out.println(str);
//                continue;
//            }
//
//            System.out.println(","+str);
//
//        }

        String myCSV = "\"e select options\"\n\t\t\t,\tcs: ''\n\t\t\t,\tm: \"\"\n\t\t\t,\tp: '$*'\n\t\t\t,\tsp: ''\n\t\t\t,\tis: false\n\t\t\t,\tsm: \"\"\n\t\t\t|||\"seregularprice\">\n                                $*\\n                              </div>\\n      |||    \\n\\t\\t\\t\\t\\t\\t\\t\\tStainless / 30mm |  | In Warehouse | $*\\n\\t\\t\\t\\t\\t\\t\\t\\n";
//        StringTokenizer stringTokenizer = new StringTokenizer(myCSV, "(\\|\\|\\|)");
//        while(stringTokenizer.hasMoreTokens()){
//            System.out.println(stringTokenizer.nextToken());
//        }


        String[] tokens  = myCSV.split("\\|\\|\\|");
        System.out.println(tokens.length);
        for (String str : tokens){
            System.out.println(str+" ====> "+str.replaceAll("[^a-zA-Z_0-9\\$\\w]",""));
        }

//        String string = "var1[value1], var2[value2], var3[value3]";
//        Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
//
//        tokens  = string.split("(\\[)(.*?)(\\])");
//        System.out.println(tokens.length);
//        for (String str : tokens){
//            System.out.println(str);
//        }


    }
}
