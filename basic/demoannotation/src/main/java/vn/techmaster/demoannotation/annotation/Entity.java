package vn.techmaster.demoannotation.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // on class level
public @interface Entity {
  String name();
}