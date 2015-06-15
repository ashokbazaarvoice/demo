package abc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/20/15
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatcherDemo {

    public static void main(String[] args) {

        String s = "true";

        Pattern pattern = Pattern.compile("\\d{3}");
        Matcher matcher = pattern.matcher(s);

        System.out.println(matcher.find());

        s = "29 Kasdkf 2300 Kdsdf";
        matcher = pattern.matcher(s);
        System.out.println(matcher.find());

        s = "292300";
        matcher = pattern.matcher(s);
        System.out.println(matcher.find());
    }
}
