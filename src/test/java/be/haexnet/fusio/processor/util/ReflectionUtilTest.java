package be.haexnet.fusio.processor.util;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;
import be.haexnet.fusio.processor.exception.FusioAccessException;
import be.haexnet.fusio.processor.exception.FusioConfigurationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.fest.assertions.api.Assertions.assertThat;

public class ReflectionUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    ReflectionTestData data;

    @Before
    public void setUp() throws Exception {
        data = new ReflectionTestData("Veldkant", "33a", "Kontich", "2550", "BE");
    }

    @Test
    public void getFieldValue() throws Exception {
        final Field field = ReflectionTestData.class.getDeclaredField("city");
        assertThat(ReflectionUtil.getFieldValue(data, field)).isEqualTo(data.getCity());
    }

    @Test
    public void getFieldValueThrowsFusioAccessException() throws Exception {
        expectedException.expect(FusioAccessException.class);
        expectedException.expectMessage("Cannot get value from field: weight on instance of: class be.haexnet.fusio.processor.util.ReflectionUtilTest$ReflectionTestData.");

        final Field field = WrongTestData.class.getDeclaredField("weight");
        ReflectionUtil.getFieldValue(data, field);
    }

    @Test
    public void getFieldByFieldName() throws Exception {
        final Field field = ReflectionTestData.class.getDeclaredField("postalCode");
        assertThat(ReflectionUtil.getFieldByFieldName(data, "postalCode")).isEqualTo(field);
    }

    @Test
    public void getFieldByFieldNameThrowsFusionConfigurationException() throws Exception {
        expectedException.expect(FusioConfigurationException.class);
        expectedException.expectMessage("Could not find field: length on instance of: class be.haexnet.fusio.processor.util.ReflectionUtilTest$ReflectionTestData.");

        ReflectionUtil.getFieldByFieldName(data, "length");
    }

    @Test
    public void setFieldValue() throws Exception {
        final Field field = ReflectionTestData.class.getDeclaredField("street");
        final String newStreetValue = "Bourgetlaan";

        ReflectionUtil.setFieldValue(data, field, newStreetValue);

        assertThat(data.getStreet()).isEqualTo(newStreetValue);
    }

    @Test
    public void setFieldValueThrowsFusionAccessException() throws Exception {
        expectedException.expect(FusioAccessException.class);
        expectedException.expectMessage("Cannot set value: 15.32 to field: weight on instance of: class be.haexnet.fusio.processor.util.ReflectionUtilTest$ReflectionTestData.");

        final Field field = WrongTestData.class.getDeclaredField("weight");

        ReflectionUtil.setFieldValue(data, field, 15.32);
    }

    @Test
    public void getDefaultConstructor() throws Exception {
        final Field field = ReflectionTestData.class.getDeclaredField("withDefaultConstructor");

        final Constructor<?> defaultConstructor = ReflectionUtil.getDefaultConstructor(field);
        assertThat(defaultConstructor).isNotNull();
    }

    @Test
    public void getDefaultConstructorThrowsFusioAccessException() throws Exception {
        expectedException.expect(FusioConfigurationException.class);
        expectedException.expectMessage("Cannot get default constructor for: class be.haexnet.fusio.processor.util.ReflectionUtilTest$WrongTestData.");

        final Field field = ReflectionTestData.class.getDeclaredField("withoutDefaultConstructor");
        ReflectionUtil.getDefaultConstructor(field);
    }

    private static class ReflectionTestData {
        @FusioField(name = "fullName", nullable = true)
        private String street;
        @FusioField
        private String streetNumber;
        @FusioField
        private String city;
        @FusioField
        private String postalCode;
        @FusioField
        private String country;
        @FusioEmbedded
        private WrongTestData withoutDefaultConstructor;
        @FusioEmbedded
        private CorrectTestData withDefaultConstructor;

        private ReflectionTestData(final String street, final String streetNumber, final String city, final String postalCode, final String country) {
            this.street = street;
            this.streetNumber = streetNumber;
            this.city = city;
            this.postalCode = postalCode;
            this.country = country;
        }

        public String getStreet() {
            return street;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public String getCity() {
            return city;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getCountry() {
            return country;
        }

        public WrongTestData getWithoutDefaultConstructor() {
            return withoutDefaultConstructor;
        }

        public CorrectTestData getWithDefaultConstructor() {
            return withDefaultConstructor;
        }
    }

    private static class WrongTestData {
        private Double length;
        private Double weight;

        private WrongTestData(Double length, Double weight) {
            this.length = length;
            this.weight = weight;
        }
    }

    private static class CorrectTestData {
        private Double length;
        private Double weight;
    }

}