package com.abc.idea;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/12/14
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {

    public static void main(String[] args){
        String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = "Hello";

        System.out.println(str1.intern());
        System.out.println(str2.intern());
        System.out.println(str3.intern());
        if(str1==str2)
            System.out.println(true);
        else
            System.out.println(false);

        if(str1==str3)
            System.out.println(true);
        else
            System.out.println(false);

        if(str3==str2)
            System.out.println(true);
        else
            System.out.println(false);

        if("Hello"=="Hello")
            System.out.println(true);
        else
            System.out.println(false);
    }

    boolean isPlaindrome(String str) {
        int n = str.length();
        char[] c = str.toCharArray();
        for(int i = 0, j = n - 1; i < j; ++i, --j) {
            if(c[i] != c[j]) return false;
        }
        return true;
    }

    public static boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;

        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    void spiral(){
        char Matrix[][]={{'c','i','p','e'},{'r','n','k','u'},{'u','o','w','o'},{'l','e','s','y'}};
        int rowtop=0;
        int rowbottom=3;
        int colleft=0;
        int colright=3;
        while(rowtop<rowtop || colleft<colright)
        {
            for (int i=colright;i>=colleft;i--)
            {
                System.out.println(Matrix[rowtop][i]);
            }
            rowtop++;
            for(int i=rowtop;i<=rowbottom;i++)
            {
                System.out.println(Matrix[i][colleft]);
            }
            colleft++;
            for (int i=colleft;i<=colright;i++)
            {
                System.out.println(Matrix[rowbottom][i]);
            }
            rowbottom--;
            for(int i=rowbottom;i>=rowtop;i--)
            {
                System.out.println(Matrix[i][colright]);
            }
            colright--;
        }
    }
}
