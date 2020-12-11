package com.mandroid.study1.beans;

public class Father implements Cloneable {
    public String name;
    public int age;
    public Child child;

    @Override
    public Object clone() {
        try {
            Father fatherClone = (Father) super.clone();
            if (this.child != null) {
                fatherClone.child = (Child) this.child.clone();
            }
            return fatherClone;
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }
}
