package com.expenses.helper;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public static int generateId(){
        Random r=new Random();
        int Low = 10;
        int High = 100;
        return r.nextInt(High-Low) + Low;

    }
}
