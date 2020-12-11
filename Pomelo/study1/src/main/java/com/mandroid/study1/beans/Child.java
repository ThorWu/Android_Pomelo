package com.mandroid.study1.beans;

public class Child extends Father implements Cloneable {
    @Override
    public Object clone() {
        return super.clone();
    }
}
