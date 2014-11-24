package be.haexnet.fusio.processor.util;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class AnnotationUtil {

    public static ImmutableList<Field> getAnnotatedFieldsOf(final Object entity, final Class<? extends Annotation> annotation) {
        return FluentIterable
                .of(getDeclaredFieldsOf(entity))
                .filter(isAnnotatedWith(annotation))
                .toList();
    }

    public static Annotation getAnnotatedField(final Field field, final Class<? extends Annotation> annotation) {
        return field.getAnnotation(annotation);
    }

    private static Field[] getDeclaredFieldsOf(final Object entity) {
        return entity.getClass().getDeclaredFields();
    }

    private static Predicate<Field> isAnnotatedWith(final Class<? extends Annotation> annotation) {
        return new Predicate<Field>() {
            @Override
            public boolean apply(final Field field) {
                return field.isAnnotationPresent(annotation);
            }
        };
    }

}
