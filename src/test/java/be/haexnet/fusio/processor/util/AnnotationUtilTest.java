package be.haexnet.fusio.processor.util;

import be.haexnet.fusio.annotation.FusioField;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class AnnotationUtilTest {

    @Test
    public void getAnnotatedFieldsWillReturnAllAnnotatedFields() throws Exception {
        final ImmutableList<Field> annotatedFields = AnnotationUtil.getAnnotatedFieldsOf(new WithAnnotation(), FusioField.class);
        assertThat(annotatedFields).hasSize(2);
        assertThat(extractProperty("name").from(annotatedFields)).containsOnly("name", "function");
    }

    @Test
    public void getAnnotatedFieldsWillReturnsEmptyList() throws Exception {
        assertThat(AnnotationUtil.getAnnotatedFieldsOf(new WithoutAnnotations(), FusioField.class)).isEmpty();
    }

    @Test
    public void getAnnotatedField() throws Exception {
        final Field field = WithAnnotation.class.getDeclaredField("name");

        final FusioField annotatedField = (FusioField) AnnotationUtil.getAnnotatedField(field, FusioField.class);
        assertThat(annotatedField.name()).isEqualTo("fullName");
        assertThat(annotatedField.nullable()).isTrue();
    }

    private static class WithAnnotation {
        @FusioField(name = "fullName", nullable = true)
        private String name;
        @FusioField
        private String function;
    }

    private static class WithoutAnnotations {
        private Long id;
        private String name;
        private String description;
    }

}