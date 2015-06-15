package com.abc.idea.excercise;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/19/14
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionSortDemo {

    public void print(int[] data){
        System.out.print("[");
        for(int i : data)
            System.out.print(i + ", ");
        System.out.println("]");
    }

    public static void main(String[] args){
        int[] data = {8, 5, 9, 3, 4, 2};
        SelectionSortDemo demo = new SelectionSortDemo();
        demo.print(data);
        demo.sort(data);
        int[] data1 = {8, 5, 9, 3, 4, 2};
        demo.selectionSort(data1);
    }

    // this is inplace sorting but using max pos
    public int[] sort(int[] data){
        if(data.length <= 1)
            return data;
        for(int i = data.length; i > 1; i--){ // decreasing
            int max = data[0]; // extra variable
            int maxPos = 0;
            for(int n = 1; n < i; n++ ){ // increasing
                if(data[n] > max) {
                    max = data[n];
                    maxPos = n;
                }
                print(data);
            }
            //swap the elements
            int temp = data[i-1];
            data[i-1] = max;
            data[maxPos] =  temp;
            //print(data);
        }
        return data;
    }

    // this is also inplace but using min pos
    int[] selectionSort(int[] input) {
        for (int i = 0; i < input.length; i++) { // increasing
            int minPos = i;
            for (int n = i + 1; n < input.length; n++) { // increasing
                // finding minimum here
                if (input[n] < input[minPos]) {
                    minPos = n;
                }
                print(input);
            }
            // swap the elements
            int temp = input[minPos];
            input[minPos] = input[i];
            input[i] = temp;
            //print(input);
        }
        return input;
    }
}
