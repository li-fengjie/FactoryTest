package com.life.test;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;

/**
 * 注解
 */

@MyAnno(value = 120, name = "name")//名字为value的可以省略 'value='
public class AnnotationDemmo {
    @Test
    public void fun1(){
        new C();
    }
}

@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.TYPE})//注解的作用目标
@Retention(RetentionPolicy.RUNTIME)
//保留策略
// 1.SOURCE 源代码中，编译时忽略
// 2.CLASS 源代码中和class文件中，JVM 加载类时忽略
// 3.会把注解加载到JVM内存中，唯一可反射类型
@interface MyAnno {
    int value();

    String name();
}


abstract class A<T>{
    public A(){
//        Class c = this.getClass();
//        Type type = c.getGenericSuperclass();
//        ParameterizedType parameterizedType = (ParameterizedType)type;
//        Type[] arguments = parameterizedType.getActualTypeArguments();
//        Class clzz = (Class) arguments[0];
//
        Class clzz = (Class) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(clzz);
    }
}

class B extends A<String>{

}

class C extends A<Integer>{

}