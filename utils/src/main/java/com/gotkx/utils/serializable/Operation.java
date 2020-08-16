package com.gotkx.utils.serializable;

import java.io.*;

public class Operation {
    public boolean save() throws IOException {
        State state = new State(27, "Demo");
        ObjectOutputStream oo = new ObjectOutputStream(
                                        new FileOutputStream(
                                        new File("./State.test")
                                    ));
        oo.writeObject(state);
        oo.close();
        return true;
    }

    public class State implements Serializable {
        public static final long serialVersionUID = 123L;

        public int age;
        public String name;

        public State(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        //调用
        try {
            Operation opt = new Operation();
            opt.save();
        } catch(Exception e) {
            //TODO
        }
    }
}

