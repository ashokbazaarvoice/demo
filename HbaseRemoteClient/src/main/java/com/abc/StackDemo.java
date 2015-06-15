package com.abc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/14/14
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackDemo {

    public static Deque<Character> stack = new ArrayDeque<Character>();

    public static void main(String[] args){
        List listA = new ArrayList();
        List listB = new LinkedList();
        List listC = new Vector();
        List listD = new Stack();

        List<String> items = new ArrayList<String>();

        items.add("one");
        items.add("two");
        items.add("three");

       // Stream<String> stream = items.stream(); // JDK 8

        Stack stack = new Stack();
        stack.push(6);
        stack.push(new String("7"));
        System.out.println(stack.pop());

        Deque stackNew = new ArrayDeque();
        stackNew.push(6);
        stackNew.push(new String("7"));
        System.out.println(stackNew.pop());

        String str = "{((7+2)-3)/2}";
        System.out.println(Arrays.toString(str.toCharArray()));
        System.out.println(isSymbol('k'));
        evaluate(str.toCharArray());
        System.out.println(stack.size() == 0 ? "valid" : "not valid");
    }

    static void evaluate(char[] tokens){

        for(char ch : tokens){
            System.out.println(ch);
            if(!isSymbol(ch)) {
                System.out.println("skipped "+ch);
                continue;
            }
            if(stack.size() > 0 && stack.peek()==ch) {
                stack.pop();
                System.out.println("popped "+ch);
            } else {
                stack.push(ch);
                System.out.println("pushed "+ch);
            }
        }
    }

    static boolean isSymbol(char ch){
        char[] symbols = {'{','}','(',')'};
        List list = Arrays.asList(symbols);
        System.out.println("char based list : "+list.size());
        Character[] symbols1 = {'{','}','(',')'};
        list = Arrays.asList(symbols1);
        System.out.println("Character based list : "+list.size());
        list = new ArrayList();
        list.add(new Character('{'));
        list.add(new Character('}'));
        list.add(new Character('('));
        list.add(new Character(')'));
        //System.out.println(list.size());
        if(list.contains(new Character(ch)))
            return true;
        else
            return false;
    }
}
