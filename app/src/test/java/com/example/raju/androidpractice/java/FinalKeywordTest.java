package com.example.raju.androidpractice.java;

import org.junit.Test;

/**
 * Created by raju on 12/30/16.
 */

public class FinalKeywordTest {

    private static final String USER_NAME = "raju";

    @Test
    public static void main(String args[]){

        //System.out.println(USER_NAME);

        try {

            Subclass sc = new Subclass();
            sc.test();

        }catch (Exception e){

            e.printStackTrace();

        }finally {

            USER_NAME.equalsIgnoreCase("");
        }
    }

    private static class Subclass{

        private void test(){

            System.out.println("welcome to sub class");
        }
    }
}
