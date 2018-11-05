package com.life.test;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class AnnoDemo1 {
    @Test
    public void fun1(){
         /*
        1.得到作用目标
     */
        Class<D> c = D.class;
    /*
         2. 获取指定类型的注解
     */
        MyAnno1 MyAnno1 = c.getAnnotation(MyAnno1.class);
        System.out.println(MyAnno1.name() + " " + MyAnno1.sex() +
        " " + MyAnno1.age());
    }
    @Test
    public void fun2() throws NoSuchMethodException {
        Class c = D.class;
        Method method = c.getMethod("fun1");
        MyAnno1 myAnno1 = method.getAnnotation(MyAnno1.class);
        System.out.println(myAnno1.name() + " " +
        myAnno1.age() + " " + myAnno1.sex());
    }
}

@MyAnno1(name="D类",age=20,sex="男")
class D{
    @MyAnno1(name="方法",age=21,sex = "女")
    public void fun1(){

    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1{
    String name();
    int age();
    String sex();
}
