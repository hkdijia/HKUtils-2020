package com.gotkx.utils.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserList {

    @Test
    public void test_1() {

//        List<String> list = new ArrayList<String>();
//        list.add("HK");

        List<String> list = new ArrayList<String>(Arrays.asList("tom", "cat", "Jane", "jerry"));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
