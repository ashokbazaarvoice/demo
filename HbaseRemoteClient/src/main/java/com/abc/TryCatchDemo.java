package com.abc;

/**
 * Created by ashok.agarwal on 6/9/15.
 */
public class TryCatchDemo {
    public static void hello(){
        try{
            System.out.println("hi");
            System.exit(1);
            //return;

        }catch(RuntimeException e)
        {      // return;
        }
        finally{
            System.out.println("finally is still executed at last");
        }
    }
    public static void main(String[] args){
       // hello();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("bye");
            }
        });
        hello();
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("bye from finalize");
        super.finalize();
    }
}
