package com.abc.experiment;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class UnaryDemo {
    public static void main(String[] args) {

        int result = +1;
        // result is now 1
        System.out.println(result);

        System.out.println(result--); // prints 1 but the result is now 0
        System.out.println(result);

        result++;
        // result is now 1
        System.out.println(result);

        System.out.println(++result); // prints 2 as the increment happens before the value access

        result--;

        result = -result;
        // result is now -1
        System.out.println(result);

        boolean success = false;
        // false
        System.out.println(success);
        // true
        System.out.println(!success);
    }
}
