package be.haexnet.fusio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FusioEmbedded {

    boolean decoupling() default false;

    String name() default "";

    boolean nullable() default false;

}
