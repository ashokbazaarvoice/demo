package com.abc.experiment.excercise2;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class UniqueCharChap1 {
    public static void main(String[] args){
        System.out.println(new UniqueCharChap1().isUnique("true"));
        System.out.println(new UniqueCharChap1().isUnique("banana"));
    }

    // by using a bit vector
    public boolean isUnique(String str){
        boolean status = true;
        char[] charArray = str.toCharArray();
        int flag = 0, index = 0; // flag which is 32 bit long, holds the present/not present character in bits.
        // set b0 to b31 if character a to z present
        for(char c : charArray) {
            if(c > 64 && c < 91) {
                index = c - 65;
                System.out.println(index+" : "+c);
            }else if(c > 96 && c < 123) {
                index = c - 97;
                System.out.println(index+" : "+c);
            }else {
                System.out.println("Invalid char");
            }
            if(isBitSet(flag,index)) {
                System.out.println("Duplicate char : " + c);
                status = false;
            } else
                flag = setBit(flag,index);
        }
        return status;
    }

    public int setBit(int flag, int index){
        int bitmask = 1 << index;
        flag |= bitmask;
        return flag;
    }

    public boolean isBitSet(int flag, int index){
        int bitmask = 1 << index;
        return (bitmask & flag) > 0;
    }

    // using array
    public void isUnique2(String str){
        boolean[] charset = new boolean[26]; // Here array for holding flags
        int index = 0;
        char[] charArray = str.toCharArray();
        for(char c : charArray){
            if(c > 64 && c < 91) {
                index = c - 65;
                System.out.println(index+" : "+c);
            }else if(c > 96 && c < 123) {
                index = c - 97;
                System.out.println(index+" : "+c);
            }else {
                System.out.println("Invalid char");
            }
            if(charset[index])
                System.out.println("Duplicate char : " + c);
            else {
                charset[index] = true;
                // if you want unique chars then print here OR you can return the value from charset also
            }
        }
    }

    public char[] getChar(boolean[] charset){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < charset.length; i++){
            if(charset[i])
                sb.append((char)i+65);
        }
        return sb.toString().toCharArray();
    }

    // using traversing the string from front and back to make  O(n/2)
    public void isUnique3(String str){
        boolean[] charset = new boolean[26]; // Here array for holding flags
        char[] charArray = str.toCharArray();
        int index = 0, start = 0, end = charArray.length, i = 0;
        int mid = start+(end - start)/2;  //  find mid of array start+(end - start)/2
        for(i = 0; i < mid; i++){
            char cFront = charArray[i];
            index = getIndex(cFront);
            if(charset[index])
                System.out.println("Duplicate char : " + cFront);
            else {
                charset[index] = true;
                // if you want unique chars then print here OR you can return the value from charset also
            }
            char cEnd = charArray[i+mid];
            index = getIndex(cEnd);
            if(charset[index])
                System.out.println("Duplicate char : " + cEnd);
            else {
                charset[index] = true;
                // if you want unique chars then print here OR you can return the value from charset also
            }
        }
        while (i+mid < end){
            char cEnd = charArray[i+mid];
            index = getIndex(cEnd);
            if(charset[index])
                System.out.println("Duplicate char : " + cEnd);
            else {
                charset[index] = true;
                // if you want unique chars then print here OR you can return the value from charset also
            }
            i++;
        }
    }

    public int getIndex(char c){
        int index = 0;
        if(c > 64 && c < 91) {
            index = c - 65;
            System.out.println(index+" : "+c);
        }else if(c > 96 && c < 123) {
            index = c - 97;
            System.out.println(index+" : "+c);
        }else {
            System.out.println("Invalid char");
            index = -1;
        }
        return index;
    }

    // by using traversing the string from front and back
    public void isUnique4(String str){
        // find mid
        // increment by 1 in left and right
        //
    }

}

// array.length = 6, start = 0, end = 5 => start+(end - start)/2 => 0+5/2 => 2 OR start+end/2 => 0 + 5/2 => 2
// (start+(end - start))/2 => (0+5)/2 => 2 OR (start+end)/2 => (0+5)/2 => 2
// loop for (int i = start; i <= end; i++ )

// if you see the problem is it says middle element as 2 starting with 0 position.

// array.length = 6, start = 0, end = 6 => start+(end - start)/2 => 0+6/2 => 3 OR start+end/2 => 0 + 6/2 => 3
// (start+(end - start))/2 => (0+6)/2 => 3 OR (start+end)/2 => (0+6)/2 => 6
// loop for (int i = start; i < end; i++ )

// if you see the problem is it says middle element as 3 starting with 0 position.

// array.length = 5, start = 0, end = 4 => start+(end - start)/2 => 0+4/2 => 2 OR start+end/2 => 0 + 4/2 => 2
// (start+(end - start))/2 => (0+4)/2 => 2 OR (start+end)/2 => (0+4)/2 => 2

// median is calculated using the following two formulas given here

// If n is odd then Median (M) = value of ((n + 1)/2)th item term.
// If n is even then Median (M) = value of [((n)/2)th item term + ((n)/2 + 1)th item term ]/2