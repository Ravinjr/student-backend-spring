package edu.icet.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//used for methods
@Target(ElementType.METHOD)
//logic should be detect at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditTime {
}
