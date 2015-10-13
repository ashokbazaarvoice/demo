package com.abc.experiment;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/5/15
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class SubStringTest {

    public static boolean isSubString(String str, String subString){
        System.out.println(str+ " "+subString);
        if(str.isEmpty() || subString.isEmpty() || str.length() < subString.length() ) {
            System.out.println("not valid");
            return false;
        }

        if(str.length() == subString.length() && !(str.charAt(0)==subString.charAt(0)) ) {
            System.out.println("valid");

            return false;
        }
 //       for(int i=0; i < str.length();i++){
            if(str.charAt(0)==subString.charAt(0)) {
                // start compare loop now
                System.out.println(str.charAt(0)+"=="+subString.charAt(0));
                int len = subString.length();
                if((len <= (str.length()))&&(len > 1)){
                    //System.out.println("in recursive as"+len+" < "+(str.length()));
                    return isSubString(str.substring(1, str.length()),subString.substring(1, subString.length()) );
                } else {
                    System.out.println("true"+len+" < "+(str.length()));
                    return true;
                }
            } else {

                return isSubString(str.substring(1, str.length()),subString);
            }
    //    }
//        return false;
    }

    public static void main(String[] args){
//        System.out.println(isSubString("Hello","el"));
//        System.out.println(isSubString("Hello","tr"));

        System.out.println("Hello".contains("1"));
        System.out.println(isSubStringV2("Hello", "eltr"));

    }


    public static boolean isSubStringV2(String str, String subString){
        System.out.println(str+ " "+subString);
//        if(str.isEmpty() || subString.isEmpty() || str.length() < subString.length() ) {
//            System.out.println("not valid");
//            return false;
//        }
//
//        if(str.length() == subString.length() && !(str.charAt(0)==subString.charAt(0)) ) {
//            System.out.println("valid");
//            return false;
//        }

        for(int i=0; i < str.length();i++) {
            System.out.println(i);
            /* Look for first character. */
            if (str.charAt(i) != subString.charAt(0)) {
                System.out.println(i+" "+str.charAt(i));
                while (++i <= str.length() && str.charAt(i) != subString.charAt(0)){
                    System.out.println(i+" "+str.charAt(i));
                }

            }

            /* Found first character, now look at the rest of v2 */
//            if (i <= max) {
//                int j = i + 1;
//                int end = j + targetCount - 1;
//                for (int k = targetOffset + 1; j < end && source[j]
//                        == target[k]; j++, k++);
//
//                if (j == end) {
//                    /* Found whole string. */
//                    return i - sourceOffset;
//                }
//            }
        }
//        boolean status = false;
//        for(int i=0; i < str.length();i++){
//            if(str.charAt(i)==subString.charAt(0)) {
//            // start compare loop now
//            System.out.println(str.charAt(0)+"=="+subString.charAt(0));
//            int len = subString.length();
//            if((len <= (str.length()))&&(len > 1)){
//                //System.out.println("in recursive as"+len+" < "+(str.length()));
//                return isSubString(str.substring(1, str.length()),subString.substring(1, subString.length()) );
//            } else {
//                System.out.println("true"+len+" < "+(str.length()));
//                return true;
//            }
//                status=true;
//            }
//        }
        return false;
    }

}
