package com.gotkx.utils.serializable;

import java.util.HashMap;
import java.util.Map;

class Demo {


    //大括号非常重要，相当于匿名内部类
    Map<String, Integer> map = new HashMap<String, Integer>() {

    };

    Map<String, Integer> map_2 = new HashMap<String, Integer>();

}


