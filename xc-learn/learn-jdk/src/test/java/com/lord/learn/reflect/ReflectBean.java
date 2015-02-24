package com.lord.learn.reflect;

/**
 * Created by lord on 2015/2/6.
 */
public class ReflectBean {

    public ReflectBean() {
    }

    public ReflectBean(String string) {
        System.out.println(string);
    }

    public void showName() {
        System.out.println("showName invoke");
    }
}
