package com.nhnacamemy.student.init;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//command class에 붙일 것이기 때문에 type으로 지정
@Target(value={ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    enum Method{
        POST,GET
    }

    String value();

    Method method() default Method.GET;
}