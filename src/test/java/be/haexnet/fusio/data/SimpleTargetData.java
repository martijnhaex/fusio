package be.haexnet.fusio.data;

import java.math.BigDecimal;

public class SimpleTargetData {

    private String name;
    private BigDecimal salary;
    private int age;
    private String occupation;

    private SimpleTargetData() {
    }

    private SimpleTargetData(final String name, final BigDecimal salary, final int age, final String occupation) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.occupation = occupation;
    }

    public static SimpleTargetData empty() {
        return new SimpleTargetData();
    }

    public static SimpleTargetData of(final String name, final BigDecimal salary, final int age, final String occupation) {
         return new SimpleTargetData(name, salary, age, occupation);
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
