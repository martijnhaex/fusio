package be.haexnet.fusio.processor;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FusioProcessor<O, T> {

    public T process(final O origin, final T target) {
        try {
            processFields(origin, target);
            processEmbeddeds(origin, target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    private void processFields(final Object entity, final Object target) throws NoSuchFieldException {
        for (final Field originField : getAnnotatedFieldsOf(entity, FusioField.class)) {
            final Object originValue = getFieldValue(entity, originField);

            if (fuseIfNullable(originField) || originValue != null) {
                final String fieldName = getFieldName(originField);
                final Field targetField = getFieldByFieldName(target, fieldName);

                setFieldValue(target, targetField, originValue);
            }
        }
    }

    private void processEmbeddeds(final Object entity, final Object target) throws NoSuchFieldException, NoSuchMethodException {
        for (final Field originField : getAnnotatedFieldsOf(entity, FusioEmbedded.class)) {
            final Object embeddedElement = getFieldValue(entity, originField);

            if (embeddedElement != null) {
                if (embbededDecoupling(originField)) {
                    processFields(embeddedElement, target);
                } else {
                    final String fieldName = getEmbbededFieldName(originField);
                    final Field targetField = getFieldByFieldName(target, fieldName);

                    Object targetEmbeddedElement = getFieldValue(target, targetField);
                    if (targetEmbeddedElement == null) {
                        targetEmbeddedElement = createNewInstanceOfEmbedded(targetField);
                        setFieldValue(target, targetField, targetEmbeddedElement);
                    }
                    processFields(embeddedElement, targetEmbeddedElement);
                }
            } else if (fuseIfNullableEmbbed(originField)) {
                final String fieldName = getEmbbededFieldName(originField);
                setFieldValue(target, getFieldByFieldName(target, fieldName), null);
            }
        }
    }

    private Object createNewInstanceOfEmbedded(final Field targetField) throws NoSuchMethodException {
        final Constructor<?> defaultConstructor = targetField.getType().getDeclaredConstructor();
        final boolean accessible = defaultConstructor.isAccessible();
        defaultConstructor.setAccessible(true);
        try {
            return defaultConstructor.newInstance();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            defaultConstructor.setAccessible(accessible);
        }
    }

    private ImmutableList<Field> getAnnotatedFieldsOf(final Object entity, Class<? extends Annotation> annotation) {
        return FluentIterable
                .of(getDeclaredFieldsOf(entity))
                .filter(isAnnotatedWith(annotation))
                .toList();
    }

    private String getFieldName(final Field field) {
        final String overwrittenFieldName = field.getAnnotation(FusioField.class).name();
        return StringUtils.isNotBlank(overwrittenFieldName) ? overwrittenFieldName : field.getName();
    }

    private String getEmbbededFieldName(final Field field) {
        final String overwrittenFieldName = field.getAnnotation(FusioEmbedded.class).name();
        return StringUtils.isNotBlank(overwrittenFieldName) ? overwrittenFieldName : field.getName();
    }

    private boolean fuseIfNullable(final Field field) {
        return field.getAnnotation(FusioField.class).nullable();
    }

    private boolean fuseIfNullableEmbbed(final Field field) { return field.getAnnotation(FusioEmbedded.class).nullable(); }

    private boolean embbededDecoupling(final Field field) {
        return field.getAnnotation(FusioEmbedded.class).decoupling();
    }

    private Field[] getDeclaredFieldsOf(final Object entity) {
        return entity.getClass().getDeclaredFields();
    }

    private Predicate<Field> isAnnotatedWith(final Class<? extends Annotation> annotation) {
        return new Predicate<Field>() {
            @Override
            public boolean apply(final Field field) {
                return field.isAnnotationPresent(annotation);
            }
        };
    }

    private Object getFieldValue(final Object entity, final Field field) {
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

    private Field getFieldByFieldName(final Object entity, final String fieldName) throws NoSuchFieldException {
        return entity.getClass().getDeclaredField(fieldName);
    }

    private void setFieldValue(final Object entity, final Field field, final Object value) {
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
