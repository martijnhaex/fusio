package be.haexnet.fusio.data;

import be.haexnet.fusio.annotation.FusioField;

import java.math.BigDecimal;

public class SimpleOriginData {

    @FusioField
    private String name;
    @FusioField
    private BigDecimal salary;
    @FusioField
    private int age;
    @FusioField
    private String occupation;

    private SimpleOriginData() {
    }

    private SimpleOriginData(final String name, final BigDecimal salary, final int age, final String occupation) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.occupation = occupation;
    }

    public static SimpleOriginData empty() {
        return new SimpleOriginData();
    }

    public static SimpleOriginData of(final String name, final BigDecimal salary, final int age, final String occupation) {
        return new SimpleOriginData(name, salary, age, occupation);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

}
