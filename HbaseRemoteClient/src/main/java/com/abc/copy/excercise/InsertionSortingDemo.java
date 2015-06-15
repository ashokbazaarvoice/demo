package com.abc.copy.excercise;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/19/14
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertionSortingDemo {

//    second if can be replaced by while or breaks
//    public int[] sort(int[] data){
//        int citer = 0;
//        if(data.length <= 1)
//            return data;
//        for(int i = 1; i < data.length; i++){
//            for(int n = i; n > 0; n--){
//                if(data[n] < data[n-1]) {
//                    //swap(data[n], data[n-1]); Wrong approach
//                    int temp = data[n];
//                    data[n] = data[n-1];
//                    data[n-1] = temp;
//                }
//                else
//                    break;
//                print(data);
//                citer++;
//            }
//            System.out.println(citer);
//            //print(data);
//        }
//        return data;
//    }

    public int[] sort(int[] data){
        if(data.length <= 1)
            return data;
        for(int i = 1; i < data.length; i++){
            int temp = data[i];
            //for(int n = i; n > 0; n--){
            int n = i;
            while(n>=0) {
                if(n > 0 && temp < data[n-1]) {
                    data[n] = data[n-1];
                    n--;
                }
                else{
                    data[n] = temp;
                    break;
                }
            }
            print(data);
        }
        return data;
    }

    // here we create space
//    public int[] sort(int[] number){
//        int citer = 0;
//        if(number.length <= 1)
//            return number;
//        for(int i=1;i<number.length;i++){
//            int position =i;
//            int currval=number[i];
//            while(position>0 && number[position-1]>currval){
//                number[position]=number[position-1];
//                position--;
//                citer++;
//                print(number);
//            }
//            number[position]=currval;
//            //print(number);
//        }
//        System.out.println(citer);
//        return number;
//    }

    public void swap(int x, int y){
        int temp = x;
        x = y;
        y = temp;
    }

    public void print(int[] data){
        System.out.print("[");
        for(int i : data)
            System.out.print(i + ", ");
        System.out.println("]");
    }

    public static void main(String[] args){
        int[] data = {8, 5, 9, 3, 4, 2};
        InsertionSortingDemo demo = new InsertionSortingDemo();
        demo.print(data);
        demo.sort(data);
    }
}
