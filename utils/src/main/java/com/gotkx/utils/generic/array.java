package com.gotkx.utils.generic;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class array {

    @Test
    public void test_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.getClass().getMethod("add", Object.class).invoke(arrayList,"abc");
//        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.println(arrayList.get(i));
//        }


        List<?>[]  list5 = new ArrayList <?>[10];
        for (int i = 0; i < list5.length; i++) {
            System.out.println(list5[i]);
        }

    }

}
