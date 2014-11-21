package be.haexnet.fusio;

import be.haexnet.fusio.annotation.FusioField;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.lang.reflect.Field;

public class FusioProcessor<O, T> {

    public T fusio(final O origin, final T target) {
        try {
            for (final Field originField : getAnnotatedFieldsOf(origin)) {
                final String fieldName = originField.getName();
                final Object originValue = getFieldValue(origin, originField);
                final Field targetField = getFieldByFieldName(target, fieldName);

                setFieldValue(target, targetField, originValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    private ImmutableList<Field> getAnnotatedFieldsOf(final O entity) {
        return FluentIterable
                .of(getDeclaredFieldsOf(entity))
                .filter(isAnnotatedWith(FusioField.class))
                .toList();
    }

    private Field[] getDeclaredFieldsOf(final O entity) {
        return entity.getClass().getDeclaredFields();
    }

    private Predicate<Field> isAnnotatedWith(final Class<FusioField> annotation) {
        return new Predicate<Field>() {
            @Override
            public boolean apply(final Field field) {
                return field.isAnnotationPresent(annotation);
            }
        };
    }

    private Object getFieldValue(final O entity, final Field field) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(accessible);
        }
    }

    private Field getFieldByFieldName(final T entity, final String fieldName) throws NoSuchFieldException {
        return entity.getClass().getDeclaredField(fieldName);
    }

    private void setFieldValue(final T entity, final Field field, final Object value) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(accessible);
        }
    }

}
