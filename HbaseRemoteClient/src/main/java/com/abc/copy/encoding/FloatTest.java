package com.abc.copy.encoding;

import java.math.BigDecimal;

/**
 * Created by ashok.agarwal on 9/15/15.
 */
public class FloatTest {

        public static void main(String[] args)
        {
            double dollar = 1.00;
            double dime = 0.10;
            int number = 7;
            System.out.println(
                    "A dollar less " + number + " dimes is $" + (dollar - number * dime)
            );

            BigDecimal dollar1 = new BigDecimal("1.0");
            BigDecimal dime1 = new BigDecimal("0.1");
            int number1 = 7;
            System.out.println ("A dollar less " + number1 + " dimes is $" +
                    (dollar1.subtract(new BigDecimal(number1).multiply(dime1) )) );

//            Float number1 = 1.89f;
//
//            for(int i = 11; i < 800; i*=2)
//            {
//                System.out.println("loop value: " + i);
//                System.out.println(i*number1);
//                System.out.println("");
//            }
//
//            double n1 = 1.89;
//            for(int i = 11; i < 80; i*=2)
//            {
//                System.out.println("loop value: " + i);
//                System.out.println(i*n1);
//                System.out.println("");
//            }

        }
}
