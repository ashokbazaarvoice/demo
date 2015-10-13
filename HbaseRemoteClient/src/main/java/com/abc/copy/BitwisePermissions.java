package com.abc.copy;

/**
 * Created by ashok.agarwal on 7/3/15.
 */
public class BitwisePermissions {

    public static final int VIEW = 4;
    public static final int ADD = 16;
    public static final int EDIT = 64;
    public static final int DELETE = 256;

    public static final int NOTHING_ALLOWED = 0;
    public static final int VIEW_ALLOWED = VIEW;
    public static final int VIEW_ADD_ALLOWED = VIEW | ADD;
    public static final int VIEW_EDIT_ALLOWED = VIEW | EDIT;
    public static final int VIEW_ADD_EDIT_ALLOWED = VIEW | ADD | EDIT;
    public static final int ALL_ALLOWED = VIEW | ADD | EDIT | DELETE;
    // Or, alternately
    //public static final int ALL_ALLOWED = VIEW_ADD_EDIT_ALLOWED | DELETE;

    public static void main(String args[]) {
        System.out.println(Integer.toBinaryString(VIEW)+ " "+Integer.toBinaryString(ADD)+ " "+Integer.toBinaryString(EDIT)+ " "+Integer.toBinaryString(DELETE));
        System.out.println(Integer.toBinaryString(VIEW_ALLOWED)+ " "+Integer.toBinaryString(VIEW_ADD_ALLOWED)
                + " "+Integer.toBinaryString(VIEW_EDIT_ALLOWED)
                + " "+Integer.toBinaryString(VIEW_ADD_EDIT_ALLOWED)
                + " "+Integer.toBinaryString(ALL_ALLOWED));
    }
}
