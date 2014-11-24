package be.haexnet.fusio.data.simple;

import be.haexnet.fusio.annotation.FusioField;

import java.math.BigDecimal;

public class OriginData {

    @FusioField
    private String name;
    @FusioField
    private BigDecimal salary;
    @FusioField
    private Integer age;
    @FusioField
    private String occupation;

    private OriginData() {
    }

    private OriginData(final String name, final BigDecimal salary, final Integer age, final String occupation) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.occupation = occupation;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String name, final BigDecimal salary, final Integer age, final String occupation) {
        return new OriginData(name, salary, age, occupation);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

}
