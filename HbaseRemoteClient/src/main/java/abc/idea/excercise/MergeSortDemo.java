package abc.idea.excercise;

import java.util.Arrays;
/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/19/14
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeSortDemo {

    public void print(int[] data){
        System.out.print("[");
        for(int i : data)
            System.out.print(i + ", ");
        System.out.println("]");
    }

    public static void main(String[] args){
        //int[] data = {8, 5, 9, 3, 4, 2, 7};
        int[] data = {8, 5, 9, 3};
        MergeSortDemo demo = new MergeSortDemo();
        System.out.println(Arrays.toString(data));
       // demo.print(data);
       //demo.sort(data);
        merge(data, 0, 1, 1);
        merge(data, 2, 3, 3);
        merge(data, 0, 2, 3);
        demo.print(data);
    }
    public int[] sort(int[] data){
        if(data.length <= 1)
            return data;
        mergeSort(data);
        return data;
    }

    public void mergeSort(int[] data){
        if(data.length > 2){
            int center = 0;
            //if(data.length%2==0){
                center = data.length/2;
                System.out.println("Even"+center);
//             } else {
//                center = (data.length+1)/2;
//                System.out.println("Odd"+center);
//            }
            int[] first = new int[center];
            int[] second = new int[data.length - first.length];
            System.arraycopy(data, 0, first, 0, first.length);
            mergeSort(first);
            print(first);
            System.arraycopy(data, first.length, second, 0, second.length);
            mergeSort(second);
            print(second);
            //merge(a, left, center + 1, right);

        }
    }

    private static void merge(int[ ] a, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        int[] tmp = new int[a.length];

        System.out.println(left+":"+leftEnd+":"+right+":"+rightEnd+":"+k);
        System.out.println("merge"+Arrays.toString(tmp));

        while(left <= leftEnd && right <= rightEnd)
            if(a[left]<=a[right]){
                tmp[k++] = a[left++];
                System.out.println(left+":"+leftEnd+":"+right+":"+rightEnd+":"+k);
                System.out.println("merge"+Arrays.toString(tmp));
            } else {
                tmp[k++] = a[right++];
                System.out.println(left+":"+leftEnd+":"+right+":"+rightEnd+":"+k);
                System.out.println("merge"+Arrays.toString(tmp));
            }
        System.out.println("merge"+Arrays.toString(a));
        System.out.println("merge"+Arrays.toString(tmp));

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];
        System.out.println("merge"+Arrays.toString(a));
        System.out.println("merge"+Arrays.toString(tmp));
        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
}
