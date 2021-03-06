package com.mysema.query.domain;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Generic15Test extends AbstractTest {

    @MappedSuperclass
    public static abstract class Compound<T extends Containable> {

        private Set<T> containables = new HashSet<T>();
    }

    @MappedSuperclass
    public static abstract class Containable<T extends Compound> {

        private T compound;
    }

    @Entity
    public static class MyCompound extends Compound<MyContainable>{
    }

    @Entity
    public static class MyContainable extends Containable<MyCompound> {

        private String additionalField;
    }

    @Test
    public void test() throws IllegalAccessException, NoSuchFieldException {
        start(QGeneric15Test_MyContainable.class, QGeneric15Test_MyContainable.myContainable);
        match(QGeneric15Test_MyCompound.class, "compound");
        matchType(MyCompound.class, "compound");
    }
}
