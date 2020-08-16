package com.gotkx.utils.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserMap {

    @Test
    public void test_1() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 10);
        map.put(2, 20);

        // Iterating entries using a For Each loop
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }
}
