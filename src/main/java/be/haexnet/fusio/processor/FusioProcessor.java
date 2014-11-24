package be.haexnet.fusio.processor;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;
import be.haexnet.fusio.processor.exception.FusioAccessException;
import be.haexnet.fusio.processor.util.AnnotationUtil;
import be.haexnet.fusio.processor.util.ReflectionUtil;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FusioProcessor<O, T> {

    public T process(final O origin, final T target) {
        processFields(origin, target);
        processEmbeddeds(origin, target);
        return target;
    }

    private void processFields(final Object entity, final Object target) {
        for (final Field originField : getAnnotatedFieldsOf(entity, FusioField.class)) {
            final Object originValue = getFieldValue(entity, originField);

            if (fuseIfNullable(originField) || originValue != null) {
                final String fieldName = getFieldName(originField);
                final Field targetField = getFieldByFieldName(target, fieldName);

                setFieldValue(target, targetField, originValue);
            }
        }
    }

    private void processEmbeddeds(final Object entity, final Object target) {
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

    private Object createNewInstanceOfEmbedded(final Field targetField) {
        final Constructor<?> defaultConstructor = getDefaultConstructor(targetField);
        final boolean accessible = defaultConstructor.isAccessible();
        defaultConstructor.setAccessible(true);
        try {
            return defaultConstructor.newInstance();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new FusioAccessException("Cannot instantiate an embedded instance of: " + targetField.getType());
        } finally {
            defaultConstructor.setAccessible(accessible);
        }
    }

    private Constructor<?> getDefaultConstructor(final Field field) {
        return ReflectionUtil.getDefaultConstructor(field);
    }

    private ImmutableList<Field> getAnnotatedFieldsOf(final Object entity, Class<? extends Annotation> annotation) {
        return AnnotationUtil.getAnnotatedFieldsOf(entity, annotation);
    }

    private String getFieldName(final Field field) {
        final String overwrittenFieldName = getFusioField(field).name();
        return StringUtils.isNotBlank(overwrittenFieldName) ? overwrittenFieldName : field.getName();
    }

    private String getEmbbededFieldName(final Field field) {
        final String overwrittenFieldName = getFusioEmbedded(field).name();
        return StringUtils.isNotBlank(overwrittenFieldName) ? overwrittenFieldName : field.getName();
    }

    private boolean fuseIfNullable(final Field field) {
        return getFusioField(field).nullable();
    }

    private boolean fuseIfNullableEmbbed(final Field field) {
        return getFusioEmbedded(field).nullable();
    }

    private boolean embbededDecoupling(final Field field) {
        return getFusioEmbedded(field).decoupling();
    }

    private FusioField getFusioField(final Field field) {
        return (FusioField) AnnotationUtil.getAnnotatedField(field, FusioField.class);
    }

    private FusioEmbedded getFusioEmbedded(final Field field) {
        return (FusioEmbedded) AnnotationUtil.getAnnotatedField(field, FusioEmbedded.class);
    }

    private Object getFieldValue(final Object entity, final Field field) {
        return ReflectionUtil.getFieldValue(entity, field);
    }

    private Field getFieldByFieldName(final Object entity, final String fieldName) {
        return ReflectionUtil.getFieldByFieldName(entity, fieldName);
    }

    private void setFieldValue(final Object entity, final Field field, final Object value) {
        ReflectionUtil.setFieldValue(entity, field, value);
    }

}
