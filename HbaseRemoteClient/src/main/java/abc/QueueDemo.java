package abc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/14/14
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue queueA = new LinkedList();
        Queue queueB = new PriorityQueue();

        queueA.add("element 1");
        queueA.add("element 2");
        queueA.add("element 3");



        //access via Iterator
        Iterator iterator = queueA.iterator();
        while(iterator.hasNext()){
            String element = (String) iterator.next();
        }

        //access via new for-loop
        for(Object object : queueA) {
            String element = (String) object;
        }

    }
}
