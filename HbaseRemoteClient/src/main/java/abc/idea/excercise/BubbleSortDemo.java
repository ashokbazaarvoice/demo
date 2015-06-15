package abc.idea.excercise;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/19/14
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleSortDemo {

    public void print(int[] data){
        System.out.print("[");
        for(int i : data)
            System.out.print(i + ", ");
        System.out.println("]");
    }

    public static void main(String[] args){
        int[] data = {8, 5, 9, 3, 4, 2};
        BubbleSortDemo demo = new BubbleSortDemo();
        demo.print(data);
        demo.sort(data);
    }

    public int[] sort(int[] data){
        if(data.length <= 1)
            return data;
        for(int i = data.length; i > 1; i--){
            for(int n = 1; n < i; n++){
                if(data[n] < data[n-1]) {
                    int temp = data[n];
                    data[n] = data[n-1];
                    data[n-1] = temp;
                }
                print(data);
            }
        }
        return data;
    }
}
